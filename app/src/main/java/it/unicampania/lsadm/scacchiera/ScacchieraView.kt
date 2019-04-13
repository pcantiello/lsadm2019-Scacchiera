package it.unicampania.lsadm.scacchiera

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View

/**
 * Classe per la custom view relativa alla scacchiera
 */
class ScacchieraView : View {

    // Costruttori
    constructor(context: Context) : super(context, null)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs, 0)

    /**
     * Override del metodo onDraw invocato ogni volta che è richiesto di ridisegnare la scacchiera
     */
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }
}