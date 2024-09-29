
pipeline {
  agent any
  stages {
    stage("Build") {
      steps {
        git url: 'https://github.com/tapiwanasheMbizvo/nice.app.git'  branch: 'simple-spring-boot-with-docker'
        withMaven {
          sh "mvn clean verify"
        } // withMaven will discover the generated Maven artifacts, JUnit Surefire & FailSafe reports and FindBugs reports
      }
    }
  }
}