package com.example.movilessoftware2023a

import android.annotation.SuppressLint

class BaseDeDatos {
    @SuppressLint("NewApi")
    companion object {
        val debates = ArrayList<Debate>()
        private var size = 0

        init {
            debates.add(
                Debate("Cambio climático", 50, 120, "Salón de conferencias A", id=1)
            )

            debates.add(
                Debate("Educación pública", 80, 90, "Aula magna", id=2)
            )

            debates.add(
                Debate("Inteligencia artificial", 40, 150, "Auditorio principal", id=3)
            )

            debates.add(
                        Debate("Desigualdad económica", 60, 180, "Salón de actos B", id=4)
            )

            Participante("User1", "Equipo A", "Política, Economía", 25, 1, debates[0])
            Participante("Player2", "Equipo B", "Ciencia, Tecnología", 30,2, debates[0])
            Participante("Gamer3", "Equipo C", "Deportes, Entretenimiento", 22, 1, debates[2])
            Participante("Champion4", "Equipo A", "Salud, Medio ambiente", 28,2, debates[2])
            Participante("ProDebater", "Equipo B", "Educación, Derechos humanos", 32, 3, debates[2])
            size = debates.size
        }

        fun buscarDebate(id: Int): Debate? {
            return debates.find { it.id == id }
        }

        fun eliminarDebate(id: Int): Boolean {
            val avion = buscarDebate(id) ?: return false
            return debates.remove(avion)
        }

        fun aniadirDebate(tema: String, quorum: Int, duracion: Int, lugar: String) {
            debates.add(Debate(tema, quorum, duracion, lugar))
            size += 1
        }

        fun actualizarAvion(tema: String, quorum: Int, duracion: Int, lugar: String, id: Int) {
            val debate = buscarDebate(id)
            debate?.tema = tema
            debate?.quorum = quorum
            debate?.duracion = duracion
            debate?.lugar = lugar
        }
    }


}