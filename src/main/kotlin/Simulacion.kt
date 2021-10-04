import objetosMapa.*
import factoria.*


fun main(){
    var numeroJugadores = 10
    var jugadores: ArrayList<Jugador> = Factoria.factoriaJugadores()
    var mapa: Mapa = Factoria.factoriaMapa(5,5,jugadores)
    println(mapa)
    var capitolio:Capitolio = Factoria.factoriaCapitolio(100,mapa)
    var tiempo = 1
    while(numeroJugadores != 1){
        //Thread.sleep(1000)
        if(tiempo %2 == 0){
           numeroJugadores = mapa.moverJugadores(numeroJugadores)
        }
        if(tiempo %5 == 0){
            capitolio.enviarItems(4)
            println(mapa)
        }
        tiempo ++
    }
    println("El ganador es: " + jugadores)
    println(mapa)
}