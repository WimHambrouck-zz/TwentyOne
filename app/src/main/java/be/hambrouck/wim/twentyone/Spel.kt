package be.hambrouck.wim.twentyone

class Spel {
    val score = 500
    val deck = Deck()

    fun hitMe() : Kaart {
        return deck.geefKaart()
    }
}