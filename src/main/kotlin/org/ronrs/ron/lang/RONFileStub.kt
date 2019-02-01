package org.ronrs.ron.lang

import com.intellij.psi.stubs.PsiFileStubImpl
import org.ronrs.ron.lang.psi.RONFile

class RONFileStub(file: RONFile) : PsiFileStubImpl<RONFile>(file)