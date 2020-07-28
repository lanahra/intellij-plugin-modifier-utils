package com.github.lanahra.modifierutils.actions

import com.github.lanahra.modifierutils.PsiInSelectionElementVisitor
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Caret
import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiModifierList
import com.intellij.psi.PsiModifierListOwner

abstract class ToggleElementModifierAction<T : PsiElement> : AnAction() {
    abstract fun modifier(): String

    abstract fun visitor(caret: Caret): PsiInSelectionElementVisitor<T>

    override fun actionPerformed(e: AnActionEvent) {
        val psiFile: PsiFile = e.getData(CommonDataKeys.PSI_FILE) ?: return
        val editor: Editor = e.getData(CommonDataKeys.EDITOR) ?: return
        val psiModifierListOwners: MutableList<PsiModifierListOwner> = mutableListOf()

        editor.caretModel.runForEachCaret {
            val visitor = visitor(it)
            psiFile.accept(visitor)
            psiModifierListOwners.addAll(visitor.visitedElements.filterIsInstance<PsiModifierListOwner>())
        }

        WriteCommandAction.runWriteCommandAction(e.project) {
            psiModifierListOwners.forEach {
                val modifierList: PsiModifierList? = it.modifierList
                modifierList?.setModifierProperty(modifier(), !modifierList.hasModifierProperty(modifier()))
            }
        }
    }
}
