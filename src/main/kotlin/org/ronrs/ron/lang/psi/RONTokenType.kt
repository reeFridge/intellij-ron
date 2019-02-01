package org.ronrs.ron.lang.psi

import com.intellij.psi.tree.IElementType
import org.ronrs.ron.lang.RONLanguage

class RONTokenType(debugName: String) : IElementType(debugName, RONLanguage.INSTANCE) {
    override fun toString(): String = "RON:${super.toString()}"
}