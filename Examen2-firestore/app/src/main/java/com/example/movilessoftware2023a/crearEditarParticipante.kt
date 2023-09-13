package com.example.movilessoftware2023a

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

@SuppressLint("NewApi")
class crearEditarParticipante : AppCompatActivity() {

    var accion = Crud.Crear
    lateinit var participante: Participante
    val cambio = Navegacion(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creareditar_participante)

        cambio.callback = { intent -> intent.putExtra("idDebate", this.intent.getIntExtra("idDebate", -1))
        }

        accion = intent.getSerializableExtra("modo") as Crud
        val modoText = findViewById<TextView>(R.id.te_modo_ruta)
        modoText.text = accion.tipo
        val idDebate = intent.getIntExtra("idDebate", -1)
        val idParticipante = intent.getIntExtra("idParticipante", -1)
        val debate = BaseDeDatos.buscarDebate(idDebate)

        debate?.let {it.participantes.find {
                    participante ->  participante.id == idParticipante }?.let { self -> participante = self }
        }

        if (accion == Crud.Editar) {
            listarParticipantes(participante)
        }

        val botonGuardar = findViewById<Button>(R.id.btn_guardarParticipante)
        botonGuardar.setOnClickListener {
            guardar()
        }
    }

    override fun onRestart() {
        super.onRestart()
    }

    fun listarParticipantes(participante: Participante) {
        val nickname = findViewById<EditText>(R.id.tv_nickname)
        val equipo = findViewById<EditText>(R.id.tv_equipo)
        val temasInteres = findViewById<EditText>(R.id.tv_temas_interes)
        val edad = findViewById<EditText>(R.id.tv_edad)
        nickname.setText(participante.nickname)
        equipo.setText(participante.equipo)
        temasInteres.setText(participante.temasInteres)
        edad.setText(participante.edad.toString())
    }

    fun guardar() {
        val nickname = findViewById<EditText>(R.id.tv_nickname)
        val equipo = findViewById<EditText>(R.id.tv_equipo)
        val temasInteres = findViewById<EditText>(R.id.tv_temas_interes)
        val edad = findViewById<EditText>(R.id.tv_edad)
        if (accion == Crud.Crear) {
            val idDebate = intent.getIntExtra("idDebate", -1)
            val debate = BaseDeDatos.buscarDebate(idDebate)
            debate?.let {
                Participante(
                    nickname.text.toString(),
                    equipo.text.toString(),
                    temasInteres.text.toString(),
                    edad.text.toString().toInt(),
                    debate = it,
                    id = it.participantes.size + 1
                )

                finish()
            }
        }else if (accion == Crud.Editar) {
            participante.nickname = nickname.text.toString()
            participante.equipo = equipo.text.toString()
            participante.temasInteres = temasInteres.text.toString()
            participante.edad = edad.text.toString().toInt()
            finish()
        }
    }

}