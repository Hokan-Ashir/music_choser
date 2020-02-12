package ru.hokan.music_choser.ui.main

import android.os.Handler
import android.widget.NumberPicker
import kotlin.math.abs


class NumberPickerIncrementer(private val picker: NumberPicker, val incrementValue: Int) {

    companion object {
        const val SELECTING_BY_ONE_METHOD_NAME = "changeValueByOne"
        const val NUMBER_PICKER_INCREMENTER_MS = 30L
    }

    private var counter = 0
    private val handler = Handler()

    fun execute() {
        handler.postDelayed(object : Runnable {
            override fun run() {
                ++counter
                if (counter > abs(incrementValue)) {
                    return
                }

                val method = picker.javaClass.getDeclaredMethod(
                    SELECTING_BY_ONE_METHOD_NAME,
                    Boolean::class.javaPrimitiveType
                )
                method.isAccessible = true
                val increment = incrementValue > 0
                method.invoke(picker, increment)

                execute()
            }
        }, NUMBER_PICKER_INCREMENTER_MS)
    }
}