package org.opentype.keyboard

import android.inputmethodservice.InputMethodService
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.LinearLayout

/**
 * Minimal proof-of-concept IME.
 * This deliberately starts with a small English layout.
 * Hindi/Telugu transliteration and AI are later milestones.
 */
class OpenTypeInputMethodService : InputMethodService() {

    override fun onCreateInputView(): View {
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(4, 6, 4, 6)
            setBackgroundColor(Color.rgb(30, 32, 38))
        }

        val toolbar = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER
        }
        listOf("Fix", "Rewrite", "Translate").forEach { label ->
            toolbar.addView(makeKey(label, isAction = true) {
                // AI actions will be implemented in a later milestone.
            }, LinearLayout.LayoutParams(0, dp(42), 1f))
        }
        root.addView(toolbar)

        listOf("qwertyuiop", "asdfghjkl", "zxcvbnm").forEach { rowText ->
            val row = LinearLayout(this).apply {
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.CENTER
            }
            rowText.forEach { ch ->
                row.addView(makeKey(ch.toString()) {
                    currentInputConnection?.commitText(ch.toString(), 1)
                }, LinearLayout.LayoutParams(0, dp(48), 1f).apply {
                    setMargins(2, 3, 2, 3)
                })
            }
            root.addView(row)
        }

        val bottom = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER
        }
        bottom.addView(makeKey("⇧") { }, LinearLayout.LayoutParams(0, dp(48), 1f))
        bottom.addView(makeKey("⌫") {
            currentInputConnection?.deleteSurroundingText(1, 0)
        }, LinearLayout.LayoutParams(0, dp(48), 1f))
        bottom.addView(makeKey("?123") { }, LinearLayout.LayoutParams(0, dp(48), 1f))
        bottom.addView(makeKey("English") { }, LinearLayout.LayoutParams(0, dp(48), 1f))
        bottom.addView(makeKey("Space") {
            currentInputConnection?.commitText(" ", 1)
        }, LinearLayout.LayoutParams(0, dp(48), 2f))
        bottom.addView(makeKey("↵") {
            currentInputConnection?.commitText("\n", 1)
        }, LinearLayout.LayoutParams(0, dp(48), 1f))
        root.addView(bottom)

        return root
    }

    private fun makeKey(
        label: String,
        isAction: Boolean = false,
        action: () -> Unit
    ): Button = Button(this).apply {
        text = label
        isAllCaps = false
        textSize = if (isAction) 12f else 16f
        typeface = Typeface.DEFAULT_BOLD
        setTextColor(Color.WHITE)
        setBackgroundColor(if (isAction) Color.rgb(65, 83, 110) else Color.rgb(70, 73, 82))
        gravity = Gravity.CENTER
        setPadding(0, 0, 0, 0)
        setOnClickListener { action() }
    }

    private fun dp(value: Int): Int =
        (value * resources.displayMetrics.density).toInt()

    override fun onStartInput(attribute: EditorInfo?, restarting: Boolean) {
        super.onStartInput(attribute, restarting)
        // Future: detect password and sensitive fields and disable suggestions/AI.
    }
}
