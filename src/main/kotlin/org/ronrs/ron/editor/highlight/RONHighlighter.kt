package org.ronrs.ron.editor.highlight

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.ide.highlighter.JavaHighlightingColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType
import org.ronrs.ron.lang.lexer.RONLexer
import org.ronrs.ron.lang.psi.RONTypes.*

class RONHighlighter : SyntaxHighlighterBase() {
    override fun getTokenHighlights(type: IElementType?): Array<TextAttributesKey> {
        return when (type) {
            T_SOME,
            T_TRUE,
            T_FALSE -> KEYWORD_KEYS

            T_DIGIT,
            T_FLOAT -> NUMBER_KEYS

            T_ENABLE,
            T_IMPLICIT_SOME,
            T_UNWRAP_NEWTYPES,
            T_EXCLAM,
            T_SHARP -> META_KEYS

            T_IDENT -> IDENTIFIER_KEYS
            T_COMMA -> COMMA_KEYS
            T_COLON -> COLON_KEYS
            T_COMMENT -> COMMENT_KEYS
            T_STRING_LITERAL -> STRING_KEYS
            else -> EMPTY
        }
    }

    override fun getHighlightingLexer(): Lexer = RONLexer()

    companion object {
        val KEYWORD = TextAttributesKey.createTextAttributesKey(
                "RON.KEYWORD",
                JavaHighlightingColors.KEYWORD
        )
        val COMMA = TextAttributesKey.createTextAttributesKey(
                "RON.COMMA",
                DefaultLanguageHighlighterColors.COMMA
        )
        val IDENTIFIER = TextAttributesKey.createTextAttributesKey(
                "RON.IDENTIFIER",
                DefaultLanguageHighlighterColors.IDENTIFIER
        )
        val COMMENT = TextAttributesKey.createTextAttributesKey(
                "RON.COMMENT",
                DefaultLanguageHighlighterColors.LINE_COMMENT
        )
        val STRING = TextAttributesKey.createTextAttributesKey(
                "RON.STRING",
                DefaultLanguageHighlighterColors.STRING
        )
        val CONSTANT = TextAttributesKey.createTextAttributesKey(
                "RON.CONSTANT",
                DefaultLanguageHighlighterColors.CONSTANT
        )
        val NUMBER = TextAttributesKey.createTextAttributesKey(
                "RON.NUMBER",
                DefaultLanguageHighlighterColors.NUMBER
        )
        val META = TextAttributesKey.createTextAttributesKey(
                "RON.META",
                DefaultLanguageHighlighterColors.METADATA
        )
        val PROPERTY = TextAttributesKey.createTextAttributesKey(
                "RON.PROPERTY",
                DefaultLanguageHighlighterColors.INSTANCE_FIELD
        )
        val COLON = TextAttributesKey.createTextAttributesKey(
                "RON.COLON",
                DefaultLanguageHighlighterColors.COMMA
        )

        private val KEYWORD_KEYS = arrayOf(KEYWORD)
        private val COMMENT_KEYS = arrayOf(COMMENT)
        private val COMMA_KEYS = arrayOf(COMMA)
        private val COLON_KEYS = arrayOf(COLON)
        private val IDENTIFIER_KEYS = arrayOf(IDENTIFIER)
        private val STRING_KEYS = arrayOf(STRING)
        private val NUMBER_KEYS = arrayOf(NUMBER)
        private val META_KEYS = arrayOf(META)

        private val EMPTY = arrayOf<TextAttributesKey>()
    }
}