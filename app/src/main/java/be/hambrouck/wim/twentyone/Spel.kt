package be.hambrouck.wim.twentyone

class Spel {
    val geld = 500

    private val deck = Deck()
    lateinit var kaartenPc: MutableList<Kaart>
    lateinit var kaartenSpeler: MutableList<Kaart>

    var totaalPc = 0
        private set

    var totaalSpeler = 0
        private set

    init {
        reset()
    }

    fun reset() {
        kaartenPc = mutableListOf()
        kaartenSpeler = mutableListOf()
        deck.schudden()

        totaalPc = 0
        totaalSpeler = 0

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