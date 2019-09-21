pipeline {
  agent {
    docker {
      image 'openjdk:8-jdk-alpine'
    }
    stages {
        stage('Build') {
            steps {
                sh './mvnw -DskipTests clean package'
            }
        }
        stage('Test') {
            steps {
                sh './mvnw test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Docker Image Build') {
            steps {
                sh './mvnw jib:dockerBuild -DskipTests'
            }

        }
  }
}