package com.example.movilessoftware2023a

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class crearEditarDebate : AppCompatActivity() {

    var accion: Crud = Crud.Editar
    var debate: Debate? = null
    val movimiento = Navegacion(this)
    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creareditar_debate)
        val botonGuardar = findViewById<Button>(R.id.btn_guardar_debate)
        val textViewModo = findViewById<TextView>(R.id.txt_modo_debate)

        accion = intent.getSerializableExtra("modo") as Crud
        textViewModo.text = accion.tipo

        if (accion == Crud.Editar) {
            val id = intent.getIntExtra("idDebate", -1)
            debate = BaseDeDatos.buscarDebate(id)
            debate?.let { listarDebate(debate!!)}
        }
        botonGuardar.setOnClickListener { crearEditar()
            movimiento.cambiarActividad(VistaDebates::class.java)
        }
    }

    fun crearEditar() {
        val tema = findViewById<EditText>(R.id.te_modelo_avion)
        val quorum = findViewById<EditText>(R.id.te_capacidad_avion)
        val duracion = findViewById<EditText>(R.id.te_numserie_avion)
        val lugar = findViewById<EditText>(R.id.te_estado_avion)

        if(accion == Crud.Crear){
                BaseDeDatos.aniadirDebate(
                    tema.text.toString(),
                    quorum.text.toString().toInt(),
                    duracion.text.toString().toInt(),
                    lugar.text.toString()
                )
                finish()

        }else if (accion == Crud.Editar) {
                var id: Int = if (debate?.id != null) debate?.id!! else -1
                BaseDeDatos.actualizarAvion(
                    tema.text.toString(),
                    quorum.text.toString().toInt(),
                    duracion.text.toString().toInt(),
                    lugar.text.toString(),
                    id = id
                )
                finish()
            }
    }

    fun listarDebate(debate: Debate) {
        val tema = findViewById<EditText>(R.id.te_modelo_avion)
        val quorum = findViewById<EditText>(R.id.te_capacidad_avion)
        val duracion = findViewById<EditText>(R.id.te_numserie_avion)
        val lugar = findViewById<EditText>(R.id.te_estado_avion)
        tema.setText(debate.tema)
        quorum.setText(debate.quorum.toString())
        duracion.setText(debate.duracion.toString())
        lugar.setText(debate.lugar)
    }
}

