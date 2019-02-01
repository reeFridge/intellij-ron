package org.ronrs.ron.editor.highlight

import com.intellij.codeInsight.daemon.impl.HighlightInfo
import com.intellij.codeInsight.daemon.impl.HighlightInfoType
import com.intellij.codeInsight.daemon.impl.HighlightVisitor
import com.intellij.codeInsight.daemon.impl.analysis.HighlightInfoHolder
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import org.ronrs.ron.lang.psi.*

class RONHighlighterVisitor : RONVisitor(), HighlightVisitor {
    private var infoHolder: HighlightInfoHolder? = null

    override fun visitNamedField(o: RONNamedField) {
        highlight(o.ident, RONHighlighter.PROPERTY)
        super.visitNamedField(o)
    }

    private fun highlight(element: PsiElement, attrKey: TextAttributesKey) {
        val builder = HighlightInfo.newHighlightInfo(HighlightInfoType.INFORMATION)
        builder.textAttributes(attrKey)
        builder.range(element)

        infoHolder?.add(builder.create())
    }

    override fun analyze(file: PsiFile, updateWholeFile: Boolean, holder: HighlightInfoHolder, action: Runnable): Boolean {
        infoHolder = holder
        action.run()

        return true
    }

    override fun clone(): HighlightVisitor = RONHighlighterVisitor()

    override fun suitableForFile(file: PsiFile): Boolean = file is RONFile

    override fun order(): Int = 0

    override fun visit(element: PsiElement) = element.accept(this)
}