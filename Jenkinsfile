pipeline {

  agent any

  stages {

    stage('Ansible Init') {
        steps {
            script {
                
               def tfHome = tool name: 'ansible'
               env.PATH = "${tfHome}:${env.PATH}"
               sh 'ansible --version'
                    
            }
        }
    }
    stage('SonarQube analysis') {
        steps {
            withSonarQubeEnv(installationName: 'EterationSonar') {
              sh './mvnw org.jacoco:jacoco-maven-plugin:0.8.4:prepare-agent test  org.jacoco:jacoco-maven-plugin:0.8.4:report  org.sonarsource.scanner.maven:sonar-maven-plugin:3.6.1.1688:sonar'
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
    stage('Docker Image Build  & Push') {
        steps {
            sh './mvnw -Ddocker.skip=false package docker:build'
            withCredentials([string(credentialsId: 'dockerhub_password', variable: 'password')]) {
                 sh 'docker login -u mkarakas -p   ${password}'
                 sh 'docker push mkarakas/ci-cd-template'
            }
           
        }
    }

    stage('Deploy With Ansible'){
       steps {
         ansiblePlaybook(installation:'ansible',disableHostKeyChecking: true, credentialsId: 'server_key', inventory: 'playbooks/inventory.yml', playbook:'playbooks/docker_install.yml',colorized: true)
       }
    }

    /*
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
    stage('Deploy pods') {
        agent {
                    docker { 
                            image 'smesch/kubectl' 
                            args '-t'
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
    */

  }
}