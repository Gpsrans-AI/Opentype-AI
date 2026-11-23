package org.opentype.keyboard

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.inputmethod.InputMethodManager
import android.content.Context
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(32, 48, 32, 32)
        }

        val title = TextView(this).apply {
            text = "OpenType AI Keyboard"
            textSize = 24f
        }
        val info = TextView(this).apply {
            text = "Starter build 0.1.0\n\nStep 1: enable the keyboard.\nStep 2: select it as your default keyboard.\n\nAI grammar correction is not included in this starter build yet."
            textSize = 16f
            setPadding(0, 24, 0, 24)
        }
        val settingsButton = Button(this).apply {
            text = "Open keyboard settings"
            setOnClickListener { startActivity(Intent(Settings.ACTION_INPUT_METHOD_SETTINGS)) }
        }
        val pickerButton = Button(this).apply {
            text = "Choose OpenType keyboard"
            setOnClickListener {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showInputMethodPicker()
            }
        }
        layout.addView(title)
        layout.addView(info)
        layout.addView(settingsButton)
        layout.addView(pickerButton)
        setContentView(layout)
    }
}
