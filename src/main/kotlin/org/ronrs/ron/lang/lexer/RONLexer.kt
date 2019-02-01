package org.ronrs.ron.lang.lexer

import com.intellij.lexer.FlexAdapter
import com.intellij.lexer.MergingLexerAdapter
import com.intellij.psi.tree.TokenSet

class RONLexer : MergingLexerAdapter(FlexAdapter(_RONLexer()), TokenSet.EMPTY)