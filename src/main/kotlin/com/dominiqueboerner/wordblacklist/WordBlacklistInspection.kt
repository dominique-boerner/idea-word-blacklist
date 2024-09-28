package com.dominiqueboerner.wordblacklist

import com.intellij.codeInspection.AbstractBaseJavaLocalInspectionTool
import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.psi.*
import java.util.*

/**
 * This class is responsible for inspecting code and identifying variables, classes, methods, and fields
 * that contain blacklisted words.
 */
class WordBlacklistInspection : AbstractBaseJavaLocalInspectionTool() {

    override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitor {
        val project = holder.project

        val wordBlacklistPersistence = WordBlacklistSettingsPersistence.getInstance(project)
        val blacklistedWords = wordBlacklistPersistence.words.map { it.lowercase(Locale.getDefault()) }

        if (blacklistedWords.isEmpty()) {
            return PsiElementVisitor.EMPTY_VISITOR
        }

        return object : JavaElementVisitor() {

            override fun visitVariable(variable: PsiVariable) {
                super.visitVariable(variable)

                val containsNameAndNameValuePair = containsName(variable);
                val containsName = containsNameAndNameValuePair.first;
                val name = containsNameAndNameValuePair.second;

                if (containsName) {
                    holder.registerProblem(
                        variable.nameIdentifier ?: variable,
                        "[Word Blacklist] Variable contains forbidden word '$name'",
                        ProblemHighlightType.WARNING
                    )
                }
            }

            override fun visitClass(aClass: PsiClass) {
                super.visitClass(aClass)

                val containsNameAndNameValuePair = containsName(aClass);
                val containsName = containsNameAndNameValuePair.first;
                val name = containsNameAndNameValuePair.second;

                if (containsName) {
                    holder.registerProblem(
                        aClass.nameIdentifier ?: aClass,
                        "[Word Blacklist] Class name contains forbidden word '$name'",
                        ProblemHighlightType.WARNING
                    )
                }
            }

            override fun visitMethod(method: PsiMethod) {
                super.visitMethod(method)

                val containsNameAndNameValuePair = containsName(method);
                val containsName = containsNameAndNameValuePair.first;
                val name = containsNameAndNameValuePair.second;

                if (containsName) {
                    holder.registerProblem(
                        method.nameIdentifier ?: method,
                        "[Word Blacklist] Method name contains forbidden word '$name'",
                        ProblemHighlightType.WARNING
                    )
                }
            }

            override fun visitField(field: PsiField) {
                super.visitField(field)

                val containsNameAndNameValuePair = containsName(field);
                val containsName = containsNameAndNameValuePair.first;
                val name = containsNameAndNameValuePair.second;

                if (containsName) {
                    holder.registerProblem(
                        field.nameIdentifier,
                        "[Word Blacklist] Field Name contains forbidden word '$name'",
                        ProblemHighlightType.WARNING
                    )
                }
            }

            /**
             * Checks if the name of a given PsiNamedElement contains a blacklisted word.
             *
             * @param target the PsiNamedElement to check the name for
             * @return a Pair indicating if the name contains a blacklisted word (Boolean) and the blacklisted word
             *         it matches. itself (String?)
             */
            private fun containsName(target: PsiNamedElement): Pair<Boolean, String?> {
                val name = target.name?.lowercase(Locale.getDefault()) ?: return Pair(false, null)

                for (word in blacklistedWords) {
                    if (name.contains(word)) {
                        return Pair(true, word)
                    }
                }

                return Pair(false, null)
            }
        }
    }

}
