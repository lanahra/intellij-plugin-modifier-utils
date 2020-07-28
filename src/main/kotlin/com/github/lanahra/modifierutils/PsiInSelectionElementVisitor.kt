package com.github.lanahra.modifierutils

import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiRecursiveElementVisitor

open class PsiInSelectionElementVisitor<T : Any>(
        private val klass: Class<T>,
        private val startOffset: Int,
        private val endOffset: Int) : PsiRecursiveElementVisitor() {
    companion object {
        inline operator fun <reified T : Any> invoke(startOffset: Int, endOffset: Int) =
                PsiInSelectionElementVisitor(T::class.java, startOffset, endOffset)
    }

    internal val visitedElements: MutableList<PsiElement> = mutableListOf()

    override fun visitElement(element: PsiElement?) {
        if (element != null && klass.isAssignableFrom(element.javaClass) && isInSelection(element)) {
            visitedElements.add(element)
        } else {
            super.visitElement(element)
        }
    }

    private fun isInSelection(element: PsiElement): Boolean {
        val range: TextRange = element.textRange
        return range.startOffset <= endOffset && startOffset <= range.endOffset
    }
}