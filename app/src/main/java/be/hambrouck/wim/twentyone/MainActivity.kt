package be.hambrouck.wim.twentyone

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val spel = Spel()
    private var spelerBeurt = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        updateFields()
        btn_wachten.tag = 0

        btn_hit_me.setOnClickListener {
            hitMe()
        }

        btn_wachten.setOnClickListener { view ->
            when(view.tag) {
                0 -> pcBeurt()
                1 -> reset()
            }
        }
    }

    private fun reset() {
        spel.reset()
        btn_hit_me.isEnabled = true
        btn_wachten.text = getString(R.string.wachten)
        btn_wachten.tag = 0
        updateFields()
    }

    private fun pcBeurt() {
        Log.d("pcBeurt", "Nog niet geïmplementeerd, meneer Hambrouck is lui geweest!")
    }

    private fun hitMe() {
        spel.geefKaartAanSpeler()
        updateTotalen()
        if (spel.totaalSpeler == 21) {
            txt_kaarten_speler.text = getString(R.string.gewonnen)
        } else if (spel.totaalSpeler > 21) {
            txt_kaarten_speler.text = getString(R.string.verloren)
            btn_hit_me.isEnabled = false
            btn_wachten.text = getString(R.string.opnieuw)
            btn_wachten.tag = 1
        } else {
            updateKaartenrij()
        }
    }

    private fun updateFields()
    {
        updateTotalen()
        updateKaartenrij()
    }

    private fun updateKaartenrij() {
        for (i in spel.kaartenPc.indices) {
            if (i == 0 && spelerBeurt) {
                txt_kaarten_pc.text = "(X)"
            } else {
                txt_kaarten_pc.text = getString(R.string.kaartenrij, txt_kaarten_pc.text, spel.kaartenPc[i].naam)
            }
        }

        for (i in spel.kaartenSpeler.indices) {
            if (i == 0) {
                txt_kaarten_speler.text = spel.kaartenSpeler[i].naam
            } else {
                txt_kaarten_speler.text = getString(R.string.kaartenrij, txt_kaarten_speler.text, spel.kaartenSpeler[i].naam)
            }
        }
    }

    private fun updateTotalen() {
        txt_totaal_speler.text = getString(R.string.totaal, spel.totaalSpeler)

        if (spelerBeurt) {
            txt_totaal_pc.text = ""

        } else {
            txt_totaal_pc.text = getString(R.string.totaal, spel.totaalPc)
        }
    }


}
