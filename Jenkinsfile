pipeline {
  agent {
    docker {
      image 'openjdk:8-jdk-alpine'
    }

  }
  stages {
    stage('Build') {
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
      steps {
        sh './mvnw jib:dockerBuild -DskipTests'
      }
    }
  }
}