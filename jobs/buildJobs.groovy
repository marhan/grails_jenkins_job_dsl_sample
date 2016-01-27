import groovy.json.JsonSlurper

String basePath = 'grails_jenkins_job_dsl_sample'
String repo = 'marhan/grails_jenkins_job_dsl_sample'

folder(basePath) {
    description 'This example shows basic folder/job creation.'
}

job("$basePath/grails_build") {
    scm {
        github repo
    }
    triggers {
        scm '*/2 * * * *'
    }
    steps {
        grails {
            useWrapper false
            nonInteractive true
            name 'grails-3.0.12'
            serverPort '8881'
            target 'test-app'
            target 'package'
        }
    }
}
