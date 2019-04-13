package it.unicampania.lsadm.scacchiera

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Applicazione che realizza un semplice gioco su scacchiere
 * LSADM 2019
 * @author Pasquale Cantiello
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Test del funzionamento base della scacchiera
        viewScacchiera.nuovoGioco(4)
    }
}
