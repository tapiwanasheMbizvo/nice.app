pipeline {
    agent any


        environment {
        GITHUB_REPOSITORY = 'https://github.com/tapiwanasheMbizvo/nice.app.git'
        DOCKER_HUB_CREDENTIALS = 'jenkins-docker'
        BUILD_VERSION = "${env.BUILD_NUMBER}"
        DOCKER_USER_NAME = 'tapiwanashembizvo'
        DOCKER_REPO_NAME = 'nice-app'
        K8S_DEPLOYMENT_NAME= "nice-app"
        K8S_CONTAINER_NAME = "nice-app-container"
        K8S_NAMESPACE = "dev-namespace"
    }

tools{
    maven 'mvn-jenkins'
}
 

    stages {

        stage('Checkout Code') {
            steps {
                echo "Checking out code "
                git url: "${GITHUB_REPOSITORY}", branch: 'simple-spring-boot-with-docker'
            }
        }
        stage('Run Test') {
            steps {
                echo 'Running Testing..'
               
                 withMaven {
                          sh "mvn test"
                 }


            }
        }
        stage('Build ') {
            steps {
                echo 'mvn clean install....'
                
                withMaven {
                    sh "mvn clean install"
                }
        
            }
        }
        stage('Docker image build'){
            steps{

           sh "docker build -t ${DOCKER_USER_NAME}/${DOCKER_REPO_NAME}:${BUILD_VERSION} ."

            }
        }


       stage('Push Docker Image') {
             steps {
                 script {
                     docker.withRegistry('', DOCKER_HUB_CREDENTIALS) {
                         docker.image("${DOCKER_USER_NAME}/${DOCKER_REPO_NAME}:${env.BUILD_NUMBER}").push()
                     }
                 }
             }
         }

         stage('Deploy to K8s Cluster'){

            steps{

                script{

                    sh "microk8s kubectl describe deploy nice-app"
                    sh "microk8s kubectl set image deployment/${K8S_DEPLOYMENT_NAME} ${K8S_CONTAINER_NAME}=${DOCKER_USER_NAME}/${DOCKER_REPO_NAME}:${BUILD_VERSION}"
                    sh "microk8s kubectl describe deploy nice-app"
                }
            }
         }
    }
}

