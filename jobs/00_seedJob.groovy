
String basePath = 'grails_jenkins_job_dsl_sample'
String repo = 'marhan/grails_jenkins_job_dsl_sample'

job('seed') {
    scm {
        github repo
    }
    triggers {
        scm 'H/1 * * * *'
    }
    steps {
        gradle 'clean test'
        dsl {
            external 'jobs/**/*Job.groovy'
            additionalClasspath 'src/main/groovy'
        }
    }
    publishers {
        archiveJunit 'build/test-results/**/*.xml'
    }
}

listView('Seed Jobs') {
    description('')
    filterBuildQueue()
    filterExecutors()
    jobs {
        regex(/.*seedJob.*/)
    }
    columns {
        status()
        weather()
        name()
        lastSuccess()
        lastFailure()
        lastDuration()
        buildButton()
    }
}
