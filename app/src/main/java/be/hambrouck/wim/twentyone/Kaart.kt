package be.hambrouck.wim.twentyone

class Kaart(val naam: String, val kleur: Kleur) {

    override fun toString(): String {
        return "Kaart(naam='$naam', kleur=$kleur)"
    }
}