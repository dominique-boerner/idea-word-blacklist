package com.dominiqueboerner.wordblacklist

import com.intellij.openapi.options.Configurable
import com.intellij.openapi.project.Project
import javax.swing.JComponent

/**
 * The WordBlacklistConfigurable class implements the Configurable interface and provides a UI component for
 * configuring a word blacklist. This allows the user to specify a list of words that should be blacklisted.
 */
class WordBlacklistConfigurable(private val project: Project) : Configurable {
    private var settingsComponent: WordBlacklistSettingsComponent? = null

    override fun getDisplayName(): String {
        return "Word Blacklist"
    }

    override fun createComponent(): JComponent? {
        settingsComponent = WordBlacklistSettingsComponent(project)
        return settingsComponent?.panel
    }

    override fun isModified(): Boolean {
        return settingsComponent!!.isModified
    }

    override fun apply() {
        WordBlacklistSettingsPersistence.getInstance(project).words = settingsComponent?.words ?: mutableListOf()
    }

    override fun reset() {
        settingsComponent?.words = WordBlacklistSettingsPersistence.getInstance(project).words
    }

    override fun disposeUIResources() {
        settingsComponent = null
    }
}
