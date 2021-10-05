package objetosMapa

import objetosMapa.*

class Capitolio(var items:ArrayList<Item>, var mapa: Mapa) {
    var tributosMuertos = ArrayList<Jugador>(0)
    init{
        mapa.capitolio = this
        enviarItems(10)
    }

    /**
     * Envía items al mapa, comprobando que hay items suficientes
     */
    fun enviarItems(i: Int) {
        if(items.size >=i){
            for(item in 1..i){
                var item:Item = items[i]
                mapa.addItem(item)
                this.items.remove(item)
            }
        }

    }

    /**
     * Recibe un tributo muerto y lo almacena.
     */
    fun recogerTributo(jugador: Jugador) {
        tributosMuertos.add(jugador)
    }

    override fun toString(): String {
        return "CAPITOLIO" +
                "\n Items: " + this.items + "\n" +
                "Tributos Muertos: " + this.tributosMuertos
    }
}