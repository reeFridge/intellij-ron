package org.ronrs.ron.editor.folding

import com.intellij.lang.ASTNode
import com.intellij.lang.folding.FoldingBuilderEx
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import org.ronrs.ron.lang.psi.*
import org.ronrs.ron.lang.psi.impl.RONListImpl
import org.ronrs.ron.lang.psi.impl.RONMapImpl

class RONFoldingBuilder : FoldingBuilderEx(), DumbAware {
    override fun getPlaceholderText(node: ASTNode): String? {
        val psi = node.psi
        return when (psi) {
            is RONListImpl -> "[...]"
            is RONMapImpl -> "{...}"
            is RONTuple -> "(...)"
            is RONNamedStruct -> {
                if (psi.ident != null) {
                    return "${psi.ident!!.text} (...)"
                }

                "(...)"
            }
            is RONTupleStruct -> {
                if (psi.ident != null) {
                    return "${psi.ident!!.text} (...)"
                }

                "(...)"
            }
            is RONEnumVariantNamed -> "${psi.ident.text} (...)"
            is RONEnumVariantTuple -> "${psi.ident.text} (...)"
            else -> "..."
        }
    }

    override fun buildFoldRegions(root: PsiElement, doc: Document, quick: Boolean): Array<FoldingDescriptor> {
        if (root !is RONFile) return emptyArray()

        val descriptors: MutableList<FoldingDescriptor> = ArrayList()
        val visitor = FoldingVisitor(descriptors)
        PsiTreeUtil.processElements(root) { it.accept(visitor); true }

        return descriptors.toTypedArray()
    }

    override fun isCollapsedByDefault(node: ASTNode): Boolean = false

    private class FoldingVisitor(
        private val descriptors: MutableList<FoldingDescriptor>
    ) : RONVisitor() {
        override fun visitEnumVariantNamed(o: RONEnumVariantNamed) {
            val items = o.namedFieldList
            if (items.isNotEmpty()) {
                fold(o, o.textRange)
            }
        }

        override fun visitEnumVariantTuple(o: RONEnumVariantTuple) {
            val items = o.tuple.valueList
            if (items.isNotEmpty()) {
                fold(o, o.textRange)
            }
        }

        override fun visitTupleStruct(o: RONTupleStruct) {
            val items = o.tuple.valueList
            if (items.isNotEmpty()) {
                fold(o, o.textRange)
            }
        }

        override fun visitNamedStruct(o: RONNamedStruct) {
            val items = o.namedFieldList
            if (items.isNotEmpty()) {
                fold(o, o.textRange)
            }
        }

        override fun visitTuple(o: RONTuple) {
            val items = o.valueList
            if (items.isNotEmpty()) {
                fold(o, o.textRange)
            }
        }

        override fun visitMap(o: RONMap) {
            val items = o.mapEntryList
            if (items.isNotEmpty()) {
                fold(o, o.textRange)
            }
        }

        override fun visitList(o: RONList) {
            val items = o.valueList
            if (items.isNotEmpty()) {
                fold(o, o.textRange)
            }
        }

        private fun fold(element: PsiElement, range: TextRange) {
            descriptors += FoldingDescriptor(element.node, range)
        }
    }
}