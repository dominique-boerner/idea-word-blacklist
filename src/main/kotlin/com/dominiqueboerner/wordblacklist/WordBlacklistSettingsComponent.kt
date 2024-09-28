package com.dominiqueboerner.wordblacklist

import com.intellij.openapi.project.Project
import java.awt.BorderLayout
import javax.swing.JPanel
import javax.swing.JScrollPane
import javax.swing.JTextArea

/**
 * The WordBlacklistSettingsComponent class represents a UI component for configuring a word blacklist.
 * It provides a text area where the user can enter a list of words that should be blacklisted.
 * The component also includes a panel that contains the text area and handles the layout.
 */
class WordBlacklistSettingsComponent(private val project: Project) {
    private var wordsTextArea: JTextArea = JTextArea()
    var panel: JPanel = JPanel().apply {
        layout = BorderLayout()
        add(JScrollPane(wordsTextArea), BorderLayout.CENTER)
    }

    var words: MutableList<String>
        get() {
            return wordsTextArea.text.lines()
                .map { it.trim() }
                .filter { it.isNotEmpty() }
                .toMutableList()
        }
        set(words) {
            wordsTextArea.text = java.lang.String.join("\n", words)
        }

    val isModified: Boolean
        get() {
            val savedWords: List<String?> = WordBlacklistSettingsPersistence.getInstance(project).words
            return savedWords != words
        }
}
