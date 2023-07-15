package com.example.movilessoftware2023a

class Debate {
    var tema: String
    var quorum: Int
    var duracion: Int
    var lugar: String
    val participantes: ArrayList<Participante>
    val id: Int?

    constructor(tema: String, quorum: Int, duracion: Int, lugar: String, participantes: ArrayList<Participante> = ArrayList(), id: Int? = null) {
        this.tema = tema
        this.quorum = quorum
        this.duracion = duracion
        this.lugar = lugar
        this.id = id
        this.participantes = participantes
    }

    override fun toString(): String {
        return "${id}.- ${this.tema}  "+
                "\n       Quorum: ${this.quorum} pasajeros" +
                "\n       Duración: ${this.duracion} min" +
                "\n       Lugar: ${this.lugar}\n"
    }
}