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
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}