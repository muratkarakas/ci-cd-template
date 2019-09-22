pipeline {

  agent any

  stages {


  stage('List pods') {
    agent {
                docker { 
                         image 'smesch/kubectl' 
                         args '-t'
                         run '--entrypoint  cat'
                        }
    }
     steps {
        withKubeConfig([credentialsId: 'local-k8s',
                        serverUrl: 'https://kubernetes.docker.internal:6443',
                        contextName: 'docker-desktop',
                        clusterName: 'docker-desktop',
                        namespace: 'kube-system'
                        ]) {
          sh 'kubectl get pods --insecure-skip-tls-verify=true'
        }
      }
    }

    stage('Build') {
        agent {
                docker { image 'openjdk:8-jdk-alpine' }
        }
        steps {
           sh './mvnw -DskipTests clean package'
        }
    }
    stage('Docker Image Build') {
        agent {
            docker {
                image 'openjdk:8-jdk-alpine'
                args ' -v /var/run/docker.sock:/var/run/docker.sock' 
            }
        }
        steps {
            sh './mvnw -Ddocker.skip=false -Ddocker.host=unix:///var/run/docker.sock package docker:build'
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

  }
}