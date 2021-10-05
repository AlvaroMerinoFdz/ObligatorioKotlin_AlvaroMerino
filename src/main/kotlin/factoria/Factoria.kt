package factoria

import objetosMapa.*
import kotlin.random.Random

object Factoria {
    /**
     * Función que me crea un mapa, pasandole por parametro unas filas , unas columnas,
     * y una lista de jugadores
     */
    fun factoriaMapa(fila:Int, columna:Int, jugadores:ArrayList<Jugador>): Mapa {
        var sector: ArrayList<Array<Any?>> = ArrayList<Array<Any?>>()
        for (i in 1..fila){
            sector.add(Array<Any?>(columna) { null })
        }
        var mapa : Mapa = Mapa(sector)

        for(i in 0..jugadores.size-1){
            var jugador:Jugador = jugadores[i]
            mapa.addJugador(jugador)
        }
        return mapa
    }

    /**
     * Función que crea 10 tributos, 2 por cada distrito
     */
    fun factoriaJugadores(): ArrayList<Jugador> {
        var jugadores: ArrayList<Jugador> = ArrayList(10)
        for (i in 1..(5)) {

            var vida = Random.nextInt(0, 100)
            var fuerza = Random.nextInt(0, 100)
            var distrito: String = "Distritito " + i
            var tributo1: Jugador = Jugador.Builder().distrito(distrito).vida(vida).fuerza(fuerza).build()

            vida = Random.nextInt(1, 100)
            fuerza = Random.nextInt(1, 100)
            var tributo2: Jugador = Jugador.Builder().distrito(distrito).vida(vida).fuerza(fuerza).build()

            jugadores.add(tributo1)
            jugadores.add(tributo2)
        }
        return jugadores
    }

    /**
     * Función que crea un capitolio, metidos un numeros de items, y un mapa.
     */
    fun factoriaCapitolio(items:Int, mapa: Mapa): Capitolio {
        return Capitolio(factoriaItems(items),mapa)

    }

    /**
     * Factoría que crea un numero items introducidos por parámetro
     */
    fun factoriaItems(numItems:Int):ArrayList<Item>{
        var items = ArrayList<Item>(0)
        for (item in 1..numItems){
            var tipo = Random.nextInt(1,4)
            when(tipo){
                1-> items.add(Item(Tipo.ARMAS,Random.nextInt(50,100)))
                2-> items.add(Item(Tipo.MEDICINA,Random.nextInt(50,100)))
                3-> items.add(Item(Tipo.TRAMPA,0))
            }
        }
        return items
    }


}