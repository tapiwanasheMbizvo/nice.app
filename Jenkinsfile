pipeline {
    agent any

        environment {
        GITHUB_REPOSITORY = 'https://github.com/tapiwanasheMbizvo/nice.app.git'

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
                echo 'Testing..'
                script {
                    sh 'mvn test'
                }
            }
        }
        stage('Build ') {
            steps {
                echo 'Deploying....'
                script {
                    sh 'mvn clean install'
                }
            }
        }
    }
}