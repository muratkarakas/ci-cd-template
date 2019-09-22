pipeline {

  agent any

  stages {


  stage('List pods') {
    agent {
                docker { 
                         image 'smesch/kubectl' 
                         args '-t'
                        }
    }
     steps {
        withKubeConfig([credentialsId: 'local-k8s',
                        caCertificate: 'LS0tLS1CRUdJTiBDRVJUSUZJQ0FURS0tLS0tCk1JSUN5RENDQWJDZ0F3SUJBZ0lCQURBTkJna3Foa2lHOXcwQkFRc0ZBREFWTVJNd0VRWURWUVFERXdwcmRXSmwKY201bGRHVnpNQjRYRFRFNU1EZ3dOREU0TVRReU1Gb1hEVEk1TURnd01URTRNVFF5TUZvd0ZURVRNQkVHQTFVRQpBeE1LYTNWaVpYSnVaWFJsY3pDQ0FTSXdEUVlKS29aSWh2Y05BUUVCQlFBRGdnRVBBRENDQVFvQ2dnRUJBTEJjCndVNWh1a2k5Tjd4dzJlM0FjaDQ0VC9LTWx0bG95aktSdWMwM0R4dFo4ak5uanh4RzVNQ1JJeTZtNktkTUVYMWEKcG5hbVV0aFFMZ0dOUXJDR05Xa3VXMElFVTlmWVpwK0NXWGVPT2hiNGsyc0FnU3NjSlV3VktRS2NLWnNXRmhkSQpiVXhzOGVycSt1SUthNVhuMjYzemRLNGwwMHVNNmRUVEFoVVg5ancwMlpkNFg4cWlIaVBCWDlEUjFWbjA0SGF5CnlXSWtwNGozM0h4c3B0N3hhQW8yNE5IZTF4K0N2YmIwN1IyVDNrbDhnZXBFd3kwRTByaDhURnAzMDBsMW9HbDMKcnJ6K1Rtc1hCdDBSSmJ5Z0RDZ2trd1FMNytNNURJTFllQnNaT2ZyeUIrNW5kYlpNMlNENzIzZUduZ3U0Z2hlWQpUZS9ySFlGekhEV0dDWG9FRnZzQ0F3RUFBYU1qTUNFd0RnWURWUjBQQVFIL0JBUURBZ0trTUE4R0ExVWRFd0VCCi93UUZNQU1CQWY4d0RRWUpLb1pJaHZjTkFRRUxCUUFEZ2dFQkFLTmkwZG9rM3dYbEoyZkdOd3k1VVFPekc0ZjMKa29POEtONm5wTGRtTlBFM1kvdmN2NkUxNnNlOU5XZnkxckk3ZGMrbEFFWmMxWFdmdGdmMWk3WElJN0l6SCsvQQo1bThnQ3BBQmQrOExTOHEzTnF3TGRkMHoyY3JGM3pLSkU0WC9IVFdXd1JLZmQvbER0WW5WUXlLRkxHanpTUkpECnZ2TksvcFkvTE4yL3lXOWlZNU1JWk5SN2hZUmRzMHhScEwxaU1qUEJXcHp0N0pMMDBzc3pSUEZqQTJWME10cU4KdmxqeHMvODBUS2QzUW1ldzVXV2hnaFNvWExDd2VSZ3ovSGhZampTYVppQnowYlMxbFR1a3RxbFpVUlF6L1AzYwptbXVpRHV6VFllWXR0dE5jbTB6cm9KNnQrV3dvS015OSsxTGVWU2NkdzlNMUlqNGE5SWhCN2IzWmhnTT0KLS0tLS1FTkQgQ0VSVElGSUNBVEUtLS0tLQo=',
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