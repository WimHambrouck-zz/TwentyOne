package be.hambrouck.wim.twentyone

class Spel {
    val geld = 500
    val totaalPc = 0
    var totaalSpeler = 0
    val deck = Deck()

    fun hitMe() : Kaart {
        return deck.geefKaart()
    }
}