import java.io.File
import kotlin.system.measureNanoTime

fun main(args: Array<String>) {
    // Create HashMap
    val map = createMap("src/main/resources/PEP_listen.csv")
    val person = args[0] + " " + args[1] + " " + args[2]
    fun query(person: String): Boolean {
        return map.containsKey(person)
    }

//    testMap(map)

//    testAgainstList(person, map)

    println(query(person))
}
// Iterate file and split lines to extract map key from columns
fun createMap(file: String): HashMap<String, Boolean> {
    val map = HashMap<String, Boolean>()
    File(file).forEachLine {
        // some lines are not persons, these are empty in the birthday column
        val splitLine = it.split(';')
        if (splitLine[2].isNotEmpty()) {
            val key = (splitLine[2] + " " + splitLine[1] + " " + (splitLine[4].replace(".", "")))
            map[key] = true
        }
    }
    return map
}

// Test for elements not in map. If only invalid (not a person) lines print here the map is complete.
fun testMap(map: HashMap<String, Boolean>) {
    val testFile = File("src/main/resources/PEP_listen.csv")
    testFile.forEachLine {
        val splitList = it.split(';')
        if ( true) {
            val key = (splitList[2] + ' ' + splitList[1] + ' ' + splitList[4].replace(".", ""))
            if (!map.containsKey(key)) {
                println("Did not find key extracted from $splitList in map")
            }
        }
    }
}

// creates list of keys and compares map to list.contains retrieval speed
fun testAgainstList(person: String, map:HashMap<String, Boolean>){
    val list = map.keys.toList()
    val map_Time = measureNanoTime { map[person] }
    val list_Time = measureNanoTime { list.contains(person) }

    println("\nmap took   " + map_Time + " ns to find " + person)

    println("list took " + list_Time + " ns to find " + person)

    println("\nmap was " + (list_Time/map_Time) + " times faster")
}