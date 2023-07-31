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

class VistaDebates : AppCompatActivity() {
    var idSeleccionado = 0
    var accion = Crud.Crear
    val cambio = Navegacion(this)
    lateinit var adaptador: ArrayAdapter<Debate>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrardebates)

        cambio.callback = {
            intent ->
            intent.putExtra("idDebate",idSeleccionado)
            intent.putExtra("modo", accion)
        }

        val listView = findViewById<ListView>(R.id.lv_debates)
        adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, BaseDeDatos.debates
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()

        val botonCrear = findViewById<Button>(R.id.btn_crear_debate)
        botonCrear.setOnClickListener {
            accion = Crud.Crear
            cambio.cambiarActividad(crearEditarDebate::class.java)
        }
        registerForContextMenu(listView)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val CMenu = menuInflater
        CMenu.inflate(R.menu.menu_context_debate, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idSeleccionado = adaptador.getItem(id)?.id!!
    }

    fun confirmarEliminar() {
        val window = AlertDialog.Builder(this)
        window.setTitle("¿Eliminar Debate?")
        window.setPositiveButton("Si") { dialog, which ->
            if(BaseDeDatos.eliminarDebate(idSeleccionado)){
                adaptador.notifyDataSetChanged()
            }
        }
        window.setNegativeButton("No", null)

        val dialog = window.create()
        dialog.show()
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.menu_opc_editar -> { accion = Crud.Editar
                cambio.cambiarActividad(crearEditarDebate::class.java)
                true
            }
            R.id.menu_item_eliminar -> { confirmarEliminar()
                true
            }
            R.id.menu_ver_participante -> { cambio.cambiarActividad(VistaParticipantes::class.java)
                true
            }
            else -> {
                super.onContextItemSelected(item)
            }
        }
    }

    override fun onRestart() {
        super.onRestart()
        adaptador.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        adaptador.notifyDataSetChanged()
    }
}