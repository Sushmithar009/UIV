pipeline {
    agent any

    tools {
        // These names must match the ones you configure in:
        // Jenkins → Manage Jenkins → Tools
        maven 'Maven-3.9'   // e.g. Maven installation name
        jdk 'JDK-17'        // e.g. JDK installation name
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/Sushmithar009/UIV.git',
                    credentialsId: 'github-pat'   // Replace with your GitHub credentials ID
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean install -DskipTests'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Package') {
            steps {
                bat 'mvn package -DskipTests'
            }
        }

        stage('Run Spring Boot App') {
            steps {
                bat 'java -jar target/*.jar'
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
            echo '✅ Build and packaging completed successfully!'
        }
        failure {
            echo '❌ Build failed. Please check logs.'
        }
    }
}
