import ..

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    val botonCicloVida = findViewById<Button>(R.id.botonCicloVida)
    botonCicloVida.setOnClickListener {
        val intent = Intent(this, CicloVidaActivity::class.java)
        startActivity(intent)
    } 

    val botonListView = findViewById<Button>(R.id.botonListView)

    botonListView.setOnClickListener {
        val intent = Intent(this, ListViewActivity::class.java)
        startActivity(intent)
    }

}


class BEntrenador (
    var id:Int,
    var nombre:String?,
    var descripcion:String?,
)


class BBaseDatosMemoria {
    companion object {
        val arregloEntrenadores = arrayListOf<BEntrenador>()
        init {
            arregloEntrenadores
                .add(BEntrenador(1, "Adrian", "a@a.com"))
            arregloEntrenadores
            .add(BEntrenador(2, "Vicente", "b@b.com"))
            arregloEntrenadores
            .add(BEntrenador(3, "Wendy", "c@c.com"))
}

val listView = findViewById<ListView>(R.id.list_view_entrenador)
val adaptador = ArrayAdapter(
    this, // Contexto
    android.R.layout.simple_list_item_1, // Nombre Layout
    arregloEntrenadores // arreglo
)


listView.adapter = adaptador
adaptador.notifyDataSetChanged() // Notifidcar al adaptador que los datos cambiaron

val botonAnadir = findViewById<Button>(R.id.btn_anadir)
botonAnadir.setOnClickListener {
    anadirEntrenador(adaptador)
}

override fun onCreateContexMenu (
    menu: ContextMenu?,
    v: View?,
    menuInfo: ContextMenuInfo?
) {
    super.onCreateContextMenu(menu, v, menuInfo)
    val inflater = menuInflater
    inflater.inflate(R.menu.menu, menu)

    val info = menuInfo as AdapterView.AdapterContextMenuInfo
    val id = info.position
    idItemSeleccionado = id


}
)


fun abrirActividadConParametros (clase:class<*>){
    val intentExplicito = Intent(
        this,
        clase
    ) 
    intentExplicito.putExtra(
        "nombre",
        "Adrian"
    )
    intentExplicito.putExtra(
        "apellido",
        "Eguez"
    )
    intentExplicito.putExtra(
        "edad",
        31
    )
    callbackContendioIntent.launch(intentExplicito)
} 

fun devolverRespuesta(){
    val intentDevolverParametros= Intent()
    intentDevolverParametros.putExtra(
        "nombreModificado",
        "Adrian"
    )
    intentDevolverParametros.putExtra(
        "apellidoModificado",
        "Eguez"
    )
    intentDevolverParametros.putExtra(
        "edadModificado",
        31
    )
    setResult(
        RESULT_OK, // RESULT_OK / RESULT_CANCELED
        intentDevolverParametros
    )
    finish()
}


class BEntrenador (
    var id:Int,
    var nombre:String?,
    var descripcion:String?,
) 

override fun toString(): String {
    return "${id} - ${nombre}"
}

override fun onContextItemSelected(item: MenuItem): Boolean {
    return when(item?.itemId){
        // Editar
        R.id.mi_editar -> {
            Log.i("list-view", "Editar ${adaptador.getItem(idItemSeleccionado)}")
            return true
        }
        // Eliminar
        R.id.mi_eliminar -> {
            Log.i("list-view", "Eliminar ${adaptador.getItem(idItemSeleccionado)}")
            return true
        }
        else -> super.onContextItemSelected(item)
    }
    return super.onContextItemSelected(item)
}

fun abrirDialogo(){
    val builder = AlertDialog.Builder(this)
    val inflater = layoutInflater
    builder.setTitle("Desea Eliminar")
    
    builder.setPositiveButton(
        "Si"
    ) { dialog, which ->
        Log.i("dialogo", "Si")
    }
    builder.setNegativeButton(
        "No"
    ) { dialog, which ->
        Log.i("dialogo", "No")
    }
    val dialogo = builder.create()
    dialogo.show()
}

val botonIntentImplicito = findViewById<Button>(R.id.btn_intent_implicito)
botonIntentImplicito.setOnClickListener {
    val intentConRespuesta = Intent(
        Intent.ACTION_PICK,
        ContactsContract.CommonDataKinds.Phone.CONTENT_URI
    ) 
}

val botonIntentExplicito = findViewById<Button>(R.id.btn_intent_explicito)
botonIntentExplicito.setOnClickListener {
    abrirActividadConParametros(
        CicloVidaActivity::class.java
    )
}