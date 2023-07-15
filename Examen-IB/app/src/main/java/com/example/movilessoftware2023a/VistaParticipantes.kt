package com.example.movilessoftware2023a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AlertDialog

class VistaParticipantes : AppCompatActivity() {


    private var idParticipante = -1
    private var idDebate = -1
    lateinit var adaptador: ArrayAdapter<Participante>
    lateinit var debate: Debate
    val cambio: Navegacion = Navegacion(this)
    var accion: Crud = Crud.Crear
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrarparticipantes)
        idDebate = intent.getIntExtra("idDebate", -1)
        cambio.callback = {
                intent ->
            intent.putExtra("modo", accion)
            intent.putExtra("idParticipante", idParticipante)
            intent.putExtra("idDebate", idDebate)
        }

        if(idDebate != -1) {
            val debate = BaseDeDatos.buscarDebate(idDebate)
            if (debate != null) {
                this.debate = debate
            }
        }

        val listView = findViewById<ListView>(R.id.lv_participantes)
        adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            debate.participantes
        )

        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()
        registerForContextMenu(listView)
        val botonCrear = findViewById<Button>(R.id.btn_crear_participante)

        botonCrear.setOnClickListener {
            accion = Crud.Crear
            cambio.cambiarActividad(crearEditarParticipante::class.java)
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val cmenu = menuInflater
        cmenu.inflate(R.menu.menu_context_participante, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idParticipante = adaptador.getItem(id)?.id!!
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.menu_opc_editar -> { accion = Crud.Editar
                cambio.cambiarActividad(crearEditarParticipante::class.java)
                true
            }
            R.id.menu_item_eliminar -> { confirmarEliminar()
                true
            }
            else -> { super.onContextItemSelected(item)
            }
        }
    }

    fun confirmarEliminar() {
        var window = AlertDialog.Builder(this)
        window.setTitle("¿Eliminar Participante?")
        window.setPositiveButton("Si") { dialog, which ->
            val debate = BaseDeDatos.buscarDebate(idDebate)
            if(debate != null){
                debate.participantes.removeIf { it.id == idParticipante }
                adaptador.notifyDataSetChanged()
            }
        }
        window.setNegativeButton("No", null)
        val dialog = window.create()
        dialog.show()
    }

    override fun onResume() {
        super.onResume()
        adaptador.notifyDataSetChanged()
    }

}