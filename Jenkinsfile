pipeline {

  agent none

  stages {
    stage('Build') {
        agent {
                docker { image 'openjdk:8-jdk-alpine' }
        }
        steps {
           sh './mvnw -DskipTests clean package'
        }
    }
    stage('Test') {
       agent {
                docker { image 'openjdk:8-jdk-alpine' }
       }
      post {
        always {
          junit 'target/surefire-reports/*.xml'

        }

      }
      steps {
        sh './mvnw test'
      }
    }
    stage('Docker Image Build') {
        agent {
                docker { image 'docker:dind' }
        }
        steps {
           sh './mvnw  jib:dockerBuild -DskipTests
'
        }
    }
  }
}