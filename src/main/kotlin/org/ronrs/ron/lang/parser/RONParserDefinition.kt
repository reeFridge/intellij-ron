package org.ronrs.ron.lang.parser

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import org.ronrs.ron.file.RONFileElementType
import org.ronrs.ron.lang.lexer.RONLexer
import org.ronrs.ron.lang.psi.RONFile

import org.ronrs.ron.lang.psi.RONTypes.*

class RONParserDefinition : ParserDefinition {
    private val tsWHITESPACES = TokenSet.create(TokenType.WHITE_SPACE)

    override fun createParser(project: Project): PsiParser = RONParser()

    override fun createFile(viewProvider: FileViewProvider): PsiFile = RONFile(viewProvider)

    override fun getStringLiteralElements(): TokenSet = TokenSet.create(T_STRING_LITERAL)

    override fun getFileNodeType(): IFileElementType = RONFileElementType.INSTANCE

    override fun getWhitespaceTokens(): TokenSet = tsWHITESPACES

    override fun createLexer(project: Project): Lexer = RONLexer()

    override fun createElement(node: ASTNode): PsiElement = Factory.createElement(node)

    override fun getCommentTokens(): TokenSet = TokenSet.create(T_COMMENT)
}