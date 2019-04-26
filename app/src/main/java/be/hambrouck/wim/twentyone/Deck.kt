package be.hambrouck.wim.twentyone

class Deck(geschud: Boolean = true) {
    private val kaarten = listOf(
        Kaart("A", Kleur.SCHOPPEN),
        Kaart("2", Kleur.SCHOPPEN),
        Kaart("3", Kleur.SCHOPPEN),
        Kaart("4", Kleur.SCHOPPEN),
        Kaart("5", Kleur.SCHOPPEN),
        Kaart("6", Kleur.SCHOPPEN),
        Kaart("7", Kleur.SCHOPPEN),
        Kaart("8", Kleur.SCHOPPEN),
        Kaart("9", Kleur.SCHOPPEN),
        Kaart("10", Kleur.SCHOPPEN),
        Kaart("B", Kleur.SCHOPPEN),
        Kaart("D", Kleur.SCHOPPEN),
        Kaart("H", Kleur.SCHOPPEN),
        Kaart("A", Kleur.HARTEN),
        Kaart("2", Kleur.HARTEN),
        Kaart("3", Kleur.HARTEN),
        Kaart("4", Kleur.HARTEN),
        Kaart("5", Kleur.HARTEN),
        Kaart("6", Kleur.HARTEN),
        Kaart("7", Kleur.HARTEN),
        Kaart("8", Kleur.HARTEN),
        Kaart("9", Kleur.HARTEN),
        Kaart("10", Kleur.HARTEN),
        Kaart("B", Kleur.HARTEN),
        Kaart("D", Kleur.HARTEN),
        Kaart("H", Kleur.HARTEN),
        Kaart("A", Kleur.KLAVEREN),
        Kaart("2", Kleur.KLAVEREN),
        Kaart("3", Kleur.KLAVEREN),
        Kaart("4", Kleur.KLAVEREN),
        Kaart("5", Kleur.KLAVEREN),
        Kaart("6", Kleur.KLAVEREN),
        Kaart("7", Kleur.KLAVEREN),
        Kaart("8", Kleur.KLAVEREN),
        Kaart("9", Kleur.KLAVEREN),
        Kaart("10", Kleur.KLAVEREN),
        Kaart("B", Kleur.KLAVEREN),
        Kaart("D", Kleur.KLAVEREN),
        Kaart("H", Kleur.KLAVEREN),
        Kaart("A", Kleur.RUITEN),
        Kaart("2", Kleur.RUITEN),
        Kaart("3", Kleur.RUITEN),
        Kaart("4", Kleur.RUITEN),
        Kaart("5", Kleur.RUITEN),
        Kaart("6", Kleur.RUITEN),
        Kaart("7", Kleur.RUITEN),
        Kaart("8", Kleur.RUITEN),
        Kaart("9", Kleur.RUITEN),
        Kaart("10", Kleur.RUITEN),
        Kaart("B", Kleur.RUITEN),
        Kaart("D", Kleur.RUITEN),
        Kaart("H", Kleur.RUITEN)
    )

    private var deck = mutableListOf<Kaart>()

    init {
        if(geschud)
        {
            schudden()
        } else {
            sorteren()
        }
    }

    fun schudden() {
        deck = kaarten.shuffled().toMutableList()
    }

    fun sorteren() {
        deck = kaarten.toMutableList()
    }

    fun geefKaart(): Kaart {
        if(deck.isEmpty())
            throw DeckIsEmptyException()
        val kaart = deck.random()
        deck.remove(kaart)
        return kaart
    }
}