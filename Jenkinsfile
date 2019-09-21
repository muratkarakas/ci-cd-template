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
                docker { image 'dind' }
        }
        steps {
           sh 'docker  ps'
        }
    }
  }
}