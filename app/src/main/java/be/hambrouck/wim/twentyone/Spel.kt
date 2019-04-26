package be.hambrouck.wim.twentyone

class Spel {
    val geld = 500

    private val deck = Deck()
    var kaartenPc: MutableList<Kaart> = mutableListOf()
    var kaartenSpeler: MutableList<Kaart> = mutableListOf()

    var totaalPc = 0
        private set

    var totaalSpeler = 0
        private set

    init {
        reset()
    }

    fun reset() {
        // lijsten ontvanen kaarten leegmaken en scores op 0 zetten
        kaartenPc = mutableListOf()
        kaartenSpeler = mutableListOf()
        totaalPc = 0
        totaalSpeler = 0

        deck.schudden()

        // speler en PC elke twee kaarten geven om te beginnen
        geefKaartAanSpeler()
        geefKaartAanSpeler()

        geefKaartAanPc()
        geefKaartAanPc()
    }

    fun geefKaartAanSpeler() {
        val temp = deck.geefKaart()
        kaartenSpeler.add(temp)
        totaalSpeler += waardeVan(temp)
    }

    fun geefKaartAanPc() {
        val temp = deck.geefKaart()
        kaartenPc.add(temp)
        totaalPc += waardeVan(temp)
    }

    fun selecteerWinnaar(): String {
        if(totaalPc > totaalSpeler)
        {
            return "pc"
        } else {
            return "speler"
        }
    }

    private fun waardeVan(kaart: Kaart): Int {
        return when(kaart.naam) {
            "2", "3", "4", "5", "6", "7", "8", "9", "10" -> kaart.naam.toInt()
            "A" -> 1
            "B" -> 10
            "D" -> 10
            "H" -> 10
            else -> 0
        }
    }



}