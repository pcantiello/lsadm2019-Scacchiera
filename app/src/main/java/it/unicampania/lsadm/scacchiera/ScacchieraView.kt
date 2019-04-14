package it.unicampania.lsadm.scacchiera

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * Classe per la custom view relativa alla scacchiera
 */
class ScacchieraView : View {

    // Costruttori
    constructor(context: Context) : super(context, null)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs, 0)

    // Attributi privati
    private var numDivisioni = 1            // Default iniziale
    private var schermo = Rect()            // Area utilizzabile per la scacchiera
                                            // inizializzare dopo l'invocazione del costruttore
    private var dx = 0                      // Larghezza e altezza delle caselle
    private var dy = 0

    private var statoCaselle = Array(numDivisioni) { Array(numDivisioni) { false } }    // Stato delle caselle
                                            // Array bidimensionale di boolean

    private var casella = Rect()            // Generica casella
    private var paint = Paint()             // Oggetto paint per disegnare

    private var numeroMosse = 0             // Contatore delle mosse effettuate

    // Attributi pubblici
    var coloreA : Int = Color.GREEN         // I due colori utilizzati per la scacchiera
    var coloreB : Int = Color.RED

    /**
     * Override del metodo onDraw invocato ogni volta che è richiesto di ridisegnare la scacchiera
     */
    override fun onDraw(canvas: Canvas?) {
        // super.onDraw(canvas)
        canvas?.let {   // Blocco eseguito solo in presenza di un canvas valido

            // Determino dimensione schermo e caselle
            it.getClipBounds(schermo)
            dx = (schermo.right - schermo.left) / numDivisioni
            dy = (schermo.bottom - schermo.top) / numDivisioni

            // Disegno le caselle
            for (i in 0..numDivisioni - 1) {
                for (j in 0..numDivisioni - 1) {

                    // Generica casella
                    casella.left = schermo.left + i * dx
                    casella.right = schermo.left + (i + 1) * dx - 1
                    casella.top = schermo.top + j * dy
                    casella.bottom = schermo.top + (j + 1) * dy - 1

                    // Colore
                    paint.color = if (statoCaselle[i][j]) coloreA else coloreB

                    // Disegno la casella
                    it.drawRect(casella, paint)     // it rappresenta il canvas
                }
            }
        }
    }

    /**
     * Invocato ogni volta he l'utente tocca la scacchiera
     * il valore di ritorno: true indica che l'evento è stato processato
     * L'annotazione elimina il warning dovuto alla mancanza del performClick() per i non vedenti
     */
    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {

        if (event?.action == MotionEvent.ACTION_UP) {   // Ci interessa solo questo tipo di movimento

            val x = event.x.toInt() - schermo.left
            val y = event.y.toInt() - schermo.top

            // Calcolo gli indici corrispondenti, effettuo l'inversione e aggiorno la view
            val i = x / dx
            val j = y / dy
            invertiCaselle(i, j)
            this.invalidate()
        }
        return true
    }

    /**
     * Metodo per avviare un nuovo gioco
     */
    fun nuovoGioco(divisioni: Int) {

        // Inizializzazione della scacchiera
        numDivisioni = divisioni
        statoCaselle = Array(numDivisioni) { Array(numDivisioni) { false } }
        for (i in 0..numDivisioni - 1) {
            for (j in 0..numDivisioni - 1) {
                statoCaselle[i][j] = ((i + j) % 2 == 0)
            }
        }
        numeroMosse = 0     // Azzero il contatore
        this.invalidate()   // Dichiaro che la view non è più valida e questo genera una chiamata a onDraw()
    }

    /**
     * Effettua l'inversione delle celle nella riga e nella colonna
     */
    private fun invertiCaselle(riga: Int, colonna: Int) {

        for (i in 0..numDivisioni - 1) {
            statoCaselle[riga][i] = !statoCaselle[riga][i]
            statoCaselle[i][colonna] = !statoCaselle[i][colonna]
        }
    }

}