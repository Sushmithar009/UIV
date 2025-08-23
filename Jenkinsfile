pipeline {
    agent any

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
                // Run the generated Spring Boot JAR
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
