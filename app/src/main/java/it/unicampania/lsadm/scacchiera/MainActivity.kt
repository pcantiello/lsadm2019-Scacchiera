package it.unicampania.lsadm.scacchiera

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Applicazione che realizza un semplice gioco su scacchiere
 * LSADM 2019
 * @author Pasquale Cantiello
 */
class MainActivity : AppCompatActivity() {

    // Limiti min e max della scacchiera
    private val minDivisioni = 4
    private val maxDivisioni = 10

    // Contatori
    private var numeroPartite = 0
    private var numeroVittorie = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mostraContatori()

        // Imposta la seekbar
        // il range di valori possibili è 0..max
        seekDivisioni.max = maxDivisioni - minDivisioni
        seekDivisioni.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {

            override fun onStopTrackingTouch(seekBar: SeekBar) { }

            override fun onStartTrackingTouch(seekBar: SeekBar?) { }
            // Ogni volta che l'utente sposta il cursore della seekbar viene aggiornata l'etichetta
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                textDivisioni.text = (minDivisioni + progress).toString()
            }

        })
        seekDivisioni.progress = 0

        // Listener del pulsante per l'avvio di una nuova partita
        btnRestart.setOnClickListener {
            ++numeroPartite
            textPartite.text = numeroPartite.toString()
            viewScacchiera.nuovoGioco(seekDivisioni.progress + minDivisioni,
                object: ScacchieraView.FineGiocoListener {
                    override fun giocoTerminato(numeroMosse: Int) {
                        Toast.makeText(applicationContext, String.format(getString(R.string.vittoria), numeroMosse), Toast.LENGTH_SHORT).show()
                        numeroVittorie++
                        textVittorie.text = numeroVittorie.toString()
                    }
                })
        }
    }

    /**
     * Visualizza i contatori di partite e vittorie
     */
    private fun mostraContatori() {
        textPartite.text = numeroPartite.toString()
        textVittorie.text = numeroVittorie.toString()
    }
}
