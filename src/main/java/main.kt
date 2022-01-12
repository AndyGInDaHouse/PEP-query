import java.io.File
import java.util.*
import kotlin.collections.HashMap
import kotlin.system.measureNanoTime

val file = File("src/main/resources/PEP_listen.csv")

fun main(args: Array<String>) {
    val query = Query()
    val person = query.createPersonKey(args[0], args[1], args[2])

    query.testMap()

    query.testMapListSpeed(person)

    println(query.getMap())

    println(query.query(person))
}

class Query {
    private val map = createMap()

    fun query(person:String): Boolean {
        return map.containsKey(person)
    }

    fun createPersonKey(firstName: String, lastName:String, birthDay:String): String {
        return firstName.lowercase(Locale.getDefault()) + " " +
                lastName.lowercase(Locale.getDefault()) + " " +
                birthDay.replace(".", "")
    }

    // Iterate file and split lines to extract map key from columns
    private fun createMap(): HashMap<String, Boolean> {
        val map = HashMap<String, Boolean>()
        file.forEachLine {
            // some lines are not persons, these are empty in the birthday column
            val splitLine = it.split(';')
            if (splitLine[2].isNotEmpty()) {
                val key = createPersonKey(splitLine[2], splitLine[1], splitLine[4])
                map[key] = true
            }
        }
        return map
    }

    fun getMap(): HashMap<String, Boolean> {
        val map2 = HashMap<String, Boolean>()
        map2.putAll(map.toList())
        return map2
    }

    // Test for elements not in map. If only invalid (not a person) lines print here the map is complete.
    fun testMap() {
        file.forEachLine {
            val splitList = it.split(';')
            val key = createPersonKey(splitList[2], splitList[1], splitList[4].replace(".", ""))
            if (!map.containsKey(key)) {
                println("Did not find key extracted from $splitList in map")
            }
        }
    }

    // creates list of keys and compares map to list.contains retrieval speed
    fun testMapListSpeed(person:String){
        val list = map.keys.toList()
        val mapTime = measureNanoTime { map[person] }
        val listTime = measureNanoTime { list.contains(person) }

        println("\nmap took   $mapTime ns to find $person")

        println("list took $listTime ns to find $person")

        println("\nmap was " + (listTime/mapTime) + " times faster")
    }
}



