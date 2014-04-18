package de.ancho

/*
 *
 * Add new JVM to the alternatives
 *
 */

class JinfoAlternatives {

    static void main(args) {

        if (args.length < 1) {
            println "give me an jinfo file. you can find some in /usr/lib/jvm on debian based systems"
            System.exit(1)
        }

        File jinfoFile = new File(args[0])

        def properties = [:]
        def alternatives = []

        jinfoFile.eachLine { line ->
            if (line =~ '=') {
                def propertyPair = line.tokenize('=')
                properties[propertyPair.getAt(0)] = propertyPair.getAt(1)
            } else if (!line.empty) {
                def alternativeLine = line.tokenize(' ')
                alternatives << new Alternative(type: alternativeLine[0], name: alternativeLine[1], path: alternativeLine[2])
            }

        }

        println "Creating alternatives for $properties.name with priority $properties.priority"

        alternatives.each { alt ->
            new Command().executeCommand(alt, properties.priority)
        }

    }
}