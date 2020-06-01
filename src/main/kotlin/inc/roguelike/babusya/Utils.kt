package inc.roguelike.babusya

import java.lang.StringBuilder

fun collectToString(name: String, args: List<String>): String {
    val builder = StringBuilder(name)
    builder.append("(")
    for (i in 0..args.size - 2) {
        builder.append(args[i])
        builder.append(", ")
    }

    if (args.isNotEmpty()) {
        builder.append(args.last())
    }
    builder.append(")")
    return builder.toString()
}

fun getName(line: String): String? {
    val endName = findFirstBracket(line)
    return if (endName == null || line.last() != ')') {
        null
    } else {
        line.substring(0, endName)
    }
}

fun getArguments(line: String): List<String>? {
    val endName = findFirstBracket(line)
    if (endName == null || line.last() != ')') {
        return null
    }

    val argPart = line.substring(endName + 1, line.lastIndex)
    return splitIntoParts(argPart)
}

fun splitIntoParts(line: String): ArrayList<String> {
    if (line.isEmpty()) {
        return ArrayList()
    }

    var brackets = 0
    for (i in line.length - 1 downTo 1) {
        if (line[i] == ')') {
            brackets += 1
        }

        if (line[i] == '(') {
            brackets -= 1
        }

        if (line[i] == ' ' && line[i - 1] == ',' && brackets == 0) {
            val splittedPrevious = splitIntoParts(line.substring(0, i - 1))
            splittedPrevious.add(line.substring(i + 1))

            return splittedPrevious
        }
    }

    val result = ArrayList<String>()
    result.add(line)
    return result
}

fun findFirstBracket(line: String): Int? {
    for (i in line.indices) {
        if (line[i] == '(') {
            return i
        }
    }

    return null
}
