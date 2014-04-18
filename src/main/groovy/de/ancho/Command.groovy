package de.ancho

import groovy.text.SimpleTemplateEngine

class Command {

    def executeCommand(def alt, def priority) {

        def command = getCommand(alt, priority)
        println "Execute: $command"
        def proc = command.execute()
        proc.waitFor()
        if (proc.exitValue()) {
            println proc.err.text
        } else {
            def out = proc.text
            if (!out.empty) {
                println out
            }
        }

    }

    def getCommand(Alternative alternative, def priority) {

        def template = 'sudo update-alternatives --install $destination $alias $path $prio'

        def destination = (alternative.type == 'plugin' ? "/usr/lib/mozilla/plugins/$alternative.name" : "/usr/bin/$alternative.name")
        def binding = [
                "destination": destination,
                "alias"      : alternative.name,
                "path"       : alternative.path,
                "prio"       : priority
        ]

        def engine = new SimpleTemplateEngine()
        def command = engine.createTemplate(template).make(binding)

        command.toString()
    }

}
