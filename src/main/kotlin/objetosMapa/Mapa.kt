package objetosMapa

class Mapa(var sector: ArrayList<Array<Any?>>) {
    var capitolio: Capitolio? = null
    var jugadores: ArrayList<Jugador> = ArrayList(0)


    /**
     * * Comprueba que el sector al que va a añadir el ítem está vacío y lo añade
     */
    fun addItem(item: Item) {
        var fila: Int = 0
        var columna: Int = 0
        var continuar = true
        while (continuar) {
            fila = (sector.indices).random()
            columna = (sector.indices).random()
            if (sector[fila][columna] == null) {
                sector[fila][columna] = item
                continuar = false
            }
        }
    }

    /**
     * Comprueba que el sector al que va a añadir el jugador está vacío y lo añade
     */
    fun addJugador(jugador: Jugador) {
        var fila: Int = 0
        var columna: Int = 0
        var continuar = true
        while (continuar) {
            fila = (sector.indices).random()
            columna = (sector.indices).random()
            if (sector[fila][columna] == null) {
                sector[fila][columna] = jugador
                continuar = false
            }
        }
    }

    /**
     * Pone la vida del jugador a 0, lo elimina de su lista, y lo manda a la lista del capitolio
     */
    fun eliminarTributo(jugador: Jugador) {
        jugador.vida = 0
        jugadores.remove(jugador)
        capitolio?.recogerTributo(jugador)
    }

    /**
     * Función que simula como se mueven los jugadores por el mapa.
     */
    fun moverJugadores(jugadores: Int): Int {
        var jugadoresRestantes = jugadores
        var jugadoresMovidos: ArrayList<Jugador> = ArrayList(0)
        //Recorremos las filas y las columnas obteniendo el índice
        var fila = 0
        var columna = 0
        for (i in sector.indices) {
            for (j in sector[0].indices) {
                var jugador: Any? = sector[i][j]
                //Comprobamos si es jugador, y si es jugador se intenta mover, y que no se salga de los límites del tablero
                if (jugador is Jugador) {
                    //Repetimos hasta que la posición adyacente sea correcta, es decir que no se salga de los límites de la matriz
                    do {
                        fila = (0..1).random()
                        columna = (0..1).random()
                    } while (fila + i < 0 || fila + i >= sector[0].size || columna + j < 0 || columna + j >= sector.size)

                    var filaNueva = fila + i
                    var columnaNueva = columna + j
                    var objetoNuevo: Any? = sector[filaNueva][columnaNueva]
                    sector[fila][columna] = null
                    if (objetoNuevo is Item) {

                        //Si el item es una trampa eliminamos al jugador, y ese item ya ha sido consumido por tanto esa posición también es nula.
                        if (objetoNuevo.tipo == Tipo.TRAMPA) {
                            eliminarTributo(jugador)
                            sector[filaNueva][columnaNueva] = null
                            jugadoresRestantes--

                        } else {
                            jugador.recogerItem(objetoNuevo)
                            sector[filaNueva][columnaNueva] = jugador
                            jugadoresMovidos.add(jugador)
                        }
                    } else if (objetoNuevo is Jugador) {
                        if (jugador.pelea(objetoNuevo)) {
                            sector[filaNueva][columnaNueva] = jugador
                            jugadoresMovidos.add(jugador)
                            eliminarTributo(objetoNuevo)
                        } else {
                            eliminarTributo(jugador)
                        }
                        jugadoresRestantes--
                    }
                }
            }

        }
        return jugadoresRestantes
    }

    /**
     * Método toString del objeto Mapa.
     */
    override fun toString(): String {
        var mensaje = ""
        for (fila in sector) {
            mensaje += "|"
            for (col in fila) {
                mensaje += if (col is Item) {
                    " ${col.tipo} |"
                } else if (col is Jugador) {
                    " ${col.distrito} |"
                } else {
                    " - |"
                }
            }
            mensaje += "\n"
        }
        return mensaje
    }
}
