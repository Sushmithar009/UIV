pipeline {
    agent any

    environment {
        // Update this if Maven is installed elsewhere
        MAVEN_HOME = "C:\\apache-maven-3.9.9"
        PATH = "${MAVEN_HOME}\\bin;${PATH}"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/Sushmithar009/UIV.git',
                    credentialsId: 'Sushmithar009' // replace with your Jenkins GitHub PAT credentialsId
            }
        }

        stage('Build') {
            steps {
                powershell '''
                    mvn clean install -DskipTests
                '''
            }
        }

        stage('Test') {
            steps {
                powershell '''
                    mvn test
                '''
            }
        }

        stage('Package') {
            steps {
                powershell '''
                    mvn package -DskipTests
                '''
            }
        }

        stage('Run Spring Boot App') {
            steps {
                powershell '''
                    java -jar target/*.jar
                '''
            }
        }

        stage('Archive Artifacts') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }
    }

    post {
        success {
            echo '✅ Build succeeded!'
        }
        failure {
            echo '❌ Build failed. Please check logs.'
        }
    }
}
