pipeline {
    agent any


        environment {
        GITHUB_REPOSITORY = 'https://github.com/tapiwanasheMbizvo/nice.app.git'
        DOCKER_HUB_CREDENTIALS = 'jenkins-docker'
        BUILD_VERSION = "${env.BUILD_NUMBER}"
        DOCKER_USER_NAME = 'tapiwanashembizvo'
        DOCKER_REPO_NAME = 'nice-app'
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

         // sh "docker build -t ${DOCKER_USER_NAME}/${DOCKER_REPO_NAME}:${BUILD_VERSION} . -v /var/lib/jenkins:/home/jenkins/jenkins_workspace"
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
    }
}

