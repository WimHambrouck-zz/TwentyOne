package be.hambrouck.wim.twentyone

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val spel = Spel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        updateFields()

    }

    fun updateFields()
    {
        txt_totaal_pc.setText(getString(R.string.totaal, spel.totaalPc))
        txt_totaal_speler.setText(getString(R.string.totaal, spel.totaalSpeler))
    }
}
