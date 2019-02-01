package org.ronrs.ron.lang.psi

import com.intellij.psi.tree.IElementType
import org.ronrs.ron.lang.RONLanguage

class RONCompositeElementType(expr: String) : IElementType(expr, RONLanguage.INSTANCE)