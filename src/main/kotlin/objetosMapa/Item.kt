package objetosMapa

data class Item(var tipo:Tipo, var nivel:Int) {
    override fun toString(): String {
        return "ITEM( TIPO: " + this.tipo + " , NIVEL: " + this.nivel + ")\n"
    }
}