package be.hambrouck.wim.twentyone

import android.app.AlertDialog
import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
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
            when (view.tag) {
                0 -> {
                    spelerBeurt = false
                    pcBeurt()
                }
                1 -> reset()
            }
        }
    }

    private fun reset() {
        spel.reset()
        spelerBeurt = true
        btn_hit_me.isEnabled = true
        btn_wachten.text = getString(R.string.wachten)
        btn_wachten.tag = 0
        updateFields()
    }

    private fun pcBeurt() {
        val runMe = Runnable { pcBeurt() }
        if (spel.totaalSpeler < 21) {
            if (spel.totaalPc < spel.totaalSpeler) {
                spel.geefKaartAanPc()
                updateFields()
                Handler().postDelayed(runMe, 1000)
            } else {
                updateFields()
                when {
                    spel.totaalPc == 21 -> toonDialog("Computer wint")
                    spel.totaalPc > 21 -> toonDialog("Speler wint")
                    spel.totaalPc > spel.totaalSpeler -> toonDialog("Computer wint")
                    spel.totaalSpeler > spel.totaalPc -> toonDialog("Speler wint")
                    spel.totaalSpeler == spel.totaalPc -> toonDialog("Gelijkstand")
                }
                eindeSpel()
            }
        } else {
            if (spel.totaalPc < 18) {
                spel.geefKaartAanPc()
                updateFields()
                Handler().postDelayed(runMe, 1000)
            }
        }
    }

    private fun toonDialog(bericht: String)
    {
        val builder: AlertDialog.Builder? = this.let {
            AlertDialog.Builder(it)
        }
        builder?.setMessage(bericht)
            ?.setTitle("Einde spel")
            ?.setPositiveButton(android.R.string.ok) { dialog, _ -> dialog.dismiss()}

        val dialog: AlertDialog? = builder?.create()
        dialog?.show()
    }

    private fun hitMe() {
        spel.geefKaartAanSpeler()
        updateTotalen()
        when {
            spel.totaalSpeler == 21 -> {
                toonDialog("Speler wint")
                eindeSpel()
            }
            spel.totaalSpeler > 21 -> {
                toonDialog("Speler verliest")
                eindeSpel()
            }
            else -> updateKaartenrij()
        }
    }

    private fun eindeSpel() {
        btn_hit_me.isEnabled = false
        btn_wachten.text = getString(R.string.opnieuw)
        btn_wachten.tag = 1
    }

    private fun updateFields() {
        updateTotalen()
        updateKaartenrij()
    }

    private fun updateKaartenrij() {
        for (i in spel.kaartenPc.indices) {
            if (i == 0) {
                if(spelerBeurt)
                {
                    txt_kaarten_pc.text = "(X)"
                } else {
                    txt_kaarten_pc.text = spel.kaartenSpeler[i].naam
                }
            } else {
                txt_kaarten_pc.text = getString(R.string.kaartenrij, txt_kaarten_pc.text, spel.kaartenPc[i].naam)
            }
        }

        for (i in spel.kaartenSpeler.indices) {
            if (i == 0) {
                txt_kaarten_speler.text = spel.kaartenSpeler[i].naam
            } else {
                txt_kaarten_speler.text =
                    getString(R.string.kaartenrij, txt_kaarten_speler.text, spel.kaartenSpeler[i].naam)
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
