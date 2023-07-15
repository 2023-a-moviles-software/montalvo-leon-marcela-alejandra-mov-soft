package com.example.movilessoftware2023a

import java.time.LocalDate

class Participante {
    var nickname: String
    var equipo: String
    var temasInteres: String
    var edad: Int
    val id: Int?
    var debate: Debate?

    constructor(nickname: String, equipo: String,  temasInteres: String, edad: Int, id: Int? = null, debate: Debate?) {
        this.nickname = nickname
        this.equipo = equipo
        this.debate = debate
        this.temasInteres = temasInteres
        this.edad = edad
        this.id = id
        this.debate?.participantes?.add(this)
    }


    override fun toString(): String {
        return "${this.nickname}" +
                "\n       Equipo: ${this.equipo}" +
                "\n       Temas de interés: ${this.temasInteres}" +
                "\n       Edad: ${this.edad} años\n"
    }
}