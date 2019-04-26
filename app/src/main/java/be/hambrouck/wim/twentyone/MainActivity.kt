package be.hambrouck.wim.twentyone

import android.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val spel = Spel()
    private var spelerBeurt = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        updateLayout()

        btn_hit_me.setOnClickListener { hitMe() }

        btn_wachten.setOnClickListener {
            spelerBeurt = false
            pcBeurt()
        }

        btn_opnieuw.setOnClickListener { reset() }
    }


    private fun reset() {
        spel.reset()
        spelerBeurt = true
        updateLayout()
    }

    private fun pcBeurt() {
        val runMe = Runnable { pcBeurt() }
        if (spel.totaalSpeler < 21) {
            if (spel.totaalPc < spel.totaalSpeler) {
                spel.geefKaartAanPc()
                updateLayout()
                Handler().postDelayed(runMe, 1000)
            } else {
                updateLayout()
                when {
                    spel.totaalPc == 21 -> eindeSpelDialog(getString(R.string.computer_wint))
                    spel.totaalPc > 21 -> eindeSpelDialog(getString(R.string.speler_wint))
                    spel.totaalPc > spel.totaalSpeler -> eindeSpelDialog(getString(R.string.computer_wint))
                    spel.totaalSpeler > spel.totaalPc -> eindeSpelDialog(getString(R.string.speler_wint))
                    spel.totaalSpeler == spel.totaalPc -> eindeSpelDialog(getString(R.string.gelijkstand))
                }
            }
        } else {
            if (spel.totaalPc < 18) {
                spel.geefKaartAanPc()
                updateLayout()
                Handler().postDelayed(runMe, 1000)
            }
        }
    }

    private fun eindeSpelDialog(bericht: String) {
        val builder: AlertDialog.Builder? = this.let {
            AlertDialog.Builder(it)
        }
        builder?.setMessage(bericht)
            ?.setTitle(getString(R.string.einde_spel))
            ?.setPositiveButton(android.R.string.ok) { dialog, _ -> dialog.dismiss() }

        val dialog: AlertDialog? = builder?.create()
        dialog?.show()
    }

    private fun hitMe() {
        spel.geefKaartAanSpeler()
        when {
            spel.totaalSpeler == 21 -> {
                spelerBeurt = false
                eindeSpelDialog(getString(R.string.speler_wint))
            }
            spel.totaalSpeler > 21 -> {
                spelerBeurt = false
                eindeSpelDialog(getString(R.string.speler_verliest))
            }
        }
        updateLayout()
    }

    private fun updateLayout() {
        updateTotalen()
        updateKaartenrij()
        updateButtons()
    }

    private fun updateButtons() {
        if (spelerBeurt) {
            if (!btn_hit_me.isEnabled) btn_hit_me.isEnabled = true
            if (btn_opnieuw.visibility != View.GONE) btn_opnieuw.visibility = View.GONE
            if (btn_wachten.visibility != View.VISIBLE) btn_wachten.visibility = View.VISIBLE
        } else {
            if (btn_hit_me.isEnabled) btn_hit_me.isEnabled = false
            if (btn_opnieuw.visibility != View.VISIBLE) btn_opnieuw.visibility = View.VISIBLE
            if (btn_wachten.visibility != View.GONE) btn_wachten.visibility = View.GONE
        }
    }

    private fun updateKaartenrij() {
        for (i in spel.kaartenPc.indices) {
            if (i == 0) {
                if (spelerBeurt) {
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
