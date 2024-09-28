package com.dominiqueboerner.wordblacklist

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.project.Project
import org.jetbrains.annotations.NonNls
import org.jetbrains.annotations.Nullable

/**
 * WordBlacklistSettingsPersistence is a class that provides persistence capabilities for word blacklist settings.
 * This class stores the list of words in a mutable list and allows loading and saving of the state using XML serialization.
 */
@Service(Service.Level.PROJECT)
@State(
    name = "WordBlacklistSettings",
    storages = [Storage("\$PROJECT_CONFIG_DIR$/WordBlacklistSettings.xml")]
)
class WordBlacklistSettingsPersistence : PersistentStateComponent<WordBlacklistSettingsPersistence.State> {

    class State {
        var words: @NonNls MutableList<String> = mutableListOf()
    }

    private var myState = State()

    @Nullable
    override fun getState(): State {
        println("[Word Blacklist] (WordBlacklistSettingsPersistence#getState) => called")
        return myState
    }

    override fun loadState(state: State) {
        println("[Word Blacklist] (WordBlacklistSettingsPersistence#loadState) => called")
        myState = state
    }

    var words: MutableList<String>
        get() = myState.words
        set(value) {
            myState.words = value
        }

    companion object {
        fun getInstance(project: Project): WordBlacklistSettingsPersistence {
            println("[Word Blacklist] (WordBlacklistSettingsPersistence#getInstance) => called")
            return project.getService(WordBlacklistSettingsPersistence::class.java)
        }
    }
}
