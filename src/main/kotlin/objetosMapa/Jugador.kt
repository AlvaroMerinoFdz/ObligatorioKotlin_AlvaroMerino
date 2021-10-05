package objetosMapa

class Jugador(var distrito:String? = null, var vida:Int? = null, var fuerza:Int? = null) {

    fun addVida(nivel: Int){
        this.vida = this.vida?.plus(nivel)
    }
    fun addFuerza(nivel:Int){
        this.fuerza = this.fuerza?.plus(nivel)
    }

    fun pelea(luchador:Jugador):Boolean{
        var resultado:Boolean
        if(this.fuerza!! > luchador.fuerza!!)resultado = true
        else if(this.fuerza!! < luchador.fuerza!!) resultado = false
        else{
            resultado = this.vida!! >= luchador.vida!!
        }
        return resultado
    }

    /**
     * Le pasamos por parámetro un item y un jugador
     * Si el item es de tipo medicina o arma se lo sumamos al jugador
     * si es de tipo trampa, lo eliminamos.
     */
    fun recogerItem(item: Item) {
        if (item.tipo.equals(Tipo.MEDICINA)) this.addVida(item.nivel)
        else if (item.tipo.equals(Tipo.ARMAS)) this.addFuerza(item.nivel)
    }

    override fun toString(): String {
        return "Jugador( Distrito: $distrito, Vida: $vida, Fuerza: $fuerza \n"
    }

    class Builder(var distrito: String? = null, var vida: Int? = null, var fuerza: Int? = null){
        fun distrito(distrito:String):Builder{
            this.distrito = distrito
            return this
        }
        fun vida(vida:Int):Builder{
            this.vida = vida
            return this
        }
        fun fuerza(fuerza:Int):Builder{
            this.fuerza = fuerza
            return this
        }

        fun build():Jugador{
            return Jugador(distrito,fuerza,vida)
        }
    }
}