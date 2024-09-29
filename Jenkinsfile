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
    }
}