package APPMOV

//Importarlas librerias a utilizar
import java.io.File
import java.text.SimpleDateFormat 
import java.util.Date

//Creamos las clases Debate y Participante con sus atributos
data class Debate(val id: Int, val mocion: String, val fecha: Date, val esAbierto: Boolean, val puntajeSpeakers: Double)
data class Participante(val id: Int, val nombre: String, val debateId: Int , val esJuez: Boolean, val puntajeSpeaker: Double )
val formatoFecha = SimpleDateFormat("dd/MM/yyyy")

//Creamos un manejador de objetos de clase Debate para las operaciones CRUD
object DebateManager {
    private val debates: MutableList<Debate> = mutableListOf()

    init {
        cargarDebates()
    }

    fun agregarDebate(debate: Debate) {
        debates.add(debate)
        guardarDebates()
    }

    fun obtenerDebates(): List<Debate> {
        return debates.toList()
    }

    fun buscarDebatePorId(id: Int): Debate? {
        return debates.find { it.id == id }
    }

    fun eliminarDebate(id: Int) {
        debates.removeIf { it.id == id }
        guardarDebates()
    }

    //Cargamos debate al file   
    private fun cargarDebates() {
        val file = File("debates.txt")
        if (file.exists()) {
            file.forEachLine {
                val datos = it.split(",")
                val debate = Debate(
                    datos[0].toInt(),                   //ID
                    datos[1],                           //Mocion
                    formatoFecha.parse(datos[2]),       //Fecha
                    datos[3].toBoolean(),               //Es Abierto   
                    datos[4].toDouble()                //puntajeSpekaers
                )
                debates.add(debate)
            }
        }
    }

    //Guardamos debate en el file

    private fun guardarDebates() {
        val file = File("debates.txt")
        file.printWriter().use { writer ->
            debates.forEach { debate ->
                writer.println("${debate.id},${debate.mocion},${debate.fecha},${debate.esAbierto},${debate.puntajeSpeakers}")
            }
        }
    }

    //ACTUALIZAR DEBATE

    fun actualizarDebate(debates: MutableList<Debate>) {
        println("=== Actualizar Debate ===")
        print("ID del debate a actualizar: ")
        val id = readLine()?.toIntOrNull()
    
        val debate = debates.find { it.id == id }
    
        if (debate == null) {
            println("No se encontró un debate con el ID proporcionado.")
            return
        }

        println("Debate actual:")
        println(debate) 
        //Crear nuevo debate
        val debateActualizado = crearDebate()
        debates.add(debateActualizado)
        debates.remove(debate)
        eliminarDebate(id!!)
        cargarDebates()
        guardarDebates()
        agregarDebate(debateActualizado)
        println("El debate ha sido actualizado correctamente.")
    }
    



}

object ParticipanteManager {
    private val participantes: MutableList<Participante> = mutableListOf()

    init {
        cargarParticipantes()
    }

    fun agregarParticipante(participante: Participante) {
        participantes.add(participante)
        guardarParticipantes()
    }

    fun obtenerParticipantesPorDebateId(debateId: Int): List<Participante> {
        return participantes.filter { it.debateId == debateId }
    }

    fun buscarParticipantePorId(id: Int): Participante? {
        return participantes.find { it.id == id }
    }

    fun eliminarParticipante(id: Int) {
        participantes.removeIf { it.id == id }
        guardarParticipantes()
    }

    private fun cargarParticipantes() {
        val file = File("participantes.txt")
        if (file.exists()) {
            file.forEachLine {
                val datos = it.split(",")
                val participante = Participante(
                    datos[0].toInt(), //Id de Participante
                    datos[1],        //Nombre de Participante
                    datos[2].toInt(),   // Id de Debate
                    datos[3].toBoolean(),  //Es Juez
                    datos[4].toDouble()  //Puntaje Speaker
                )
                participantes.add(participante)
            }
        }
    }

    private fun guardarParticipantes() {
        val file = File("participantes.txt")
        file.printWriter().use { writer ->
            participantes.forEach { participante ->
                writer.println("${participante.id},${participante.nombre},${participante.debateId},${participante.esJuez},${participante.puntajeSpeaker}")
            }
        }
    }
}

fun main() {
    var continuar = true
    
    while (continuar) {
        println("=== Menú Principal ===")
        println("1. Agregar debate")
        println("2. Agregar participante")
        println("3. Ver debates")
        println("4. Ver participantes de un debate")
        println("5. Eliminar debate")
        println("6. Eliminar participante")
        println("7. Actualizar Debate")
        println("8. Salir")
        print("Ingrese una opción: ")
        val opcion = readLine()?.toIntOrNull()

        when (opcion) {
            1 -> {
                val debate = crearDebate()
                DebateManager.agregarDebate(debate)
                println("Debate agregado exitosamente.")
            }
            2 -> {
                val participante = crearParticipante()
                ParticipanteManager.agregarParticipante(participante)
                println("Participante agregado exitosamente.")
            }
            3 -> {
                val debates = DebateManager.obtenerDebates()
                println("=== Debates ===")
                debates.forEach { println(it) }
            }
            4 -> {
                print("Ingrese el ID del debate: ")
                val debateId = readLine()?.toIntOrNull()
                if (debateId != null) {
                    val participantes = ParticipanteManager.obtenerParticipantesPorDebateId(debateId)
                    println("=== Participantes del Debate ID $debateId ===")
                    participantes.forEach { println(it) }
                } else {
                    println("ID de debate inválido.")
                }
            }
            5 -> {
                print("Ingrese el ID del debate a eliminar: ")
                val debateId = readLine()?.toIntOrNull()
                if (debateId != null) {
                    DebateManager.eliminarDebate(debateId)
                    println("Debate eliminado exitosamente.")
                } else {
                    println("ID de debate inválido.")
                }
            }
            6 -> {
                print("Ingrese el ID del participante a eliminar: ")
                val participanteId = readLine()?.toIntOrNull()
                if (participanteId != null) {
                    ParticipanteManager.eliminarParticipante(participanteId)
                    println("Participante eliminado exitosamente.")
                } else {
                    println("ID de participante inválido.")
                }
            }
            7 -> {

                val debates = DebateManager.obtenerDebates()
                DebateManager.actualizarDebate(debates.toMutableList())
            }
            
            8 -> {
                continuar = false
                println("Saliendo del programa...")
            }
            else -> {
                println("Opción inválida.")
            }
        }
    }
}

fun crearDebate(): Debate {

    println("=== Agregar Debate ===")
    print("ID del debate: ")
    val id = readLine()?.toIntOrNull() ?: 0
    print("Tema: ")
    val mocion = readLine() ?: ""
    print("Fecha: ")
    val fecha = formatoFecha.parse(readLine())
    print("Puntaje Speakers: ")
    val puntajeSpeakers=readLine()?.toDoubleOrNull() ?: 0.0
    println("El valor ingresado es: $puntajeSpeakers")
    print("¿Es público? true/false: ")
    val esAbierto = readLine()?.toBoolean() ?: false
    return Debate(id, mocion, fecha, esAbierto, puntajeSpeakers)
}

fun crearParticipante(): Participante {
    println("=== Agregar Participante ===")
    print("ID del participante: ")
    val id = readLine()?.toIntOrNull() ?: 0
    print("Nombre: ")
    val nombre = readLine() ?: ""
    print("ID del debate al que pertenece: ")
    val debateId = readLine()?.toIntOrNull() ?: 0
    print("¿Es juez? true/false: ")
    val esJuez = readLine()?.toBoolean() ?: false
    print("Puntaje Speaker: ")
    val puntajeSpeaker=readLine()?.toDoubleOrNull() ?: 0.0
    return Participante(id, nombre, debateId, esJuez, puntajeSpeaker)
}
