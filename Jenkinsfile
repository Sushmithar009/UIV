pipeline {
    agent any   // runs on any Jenkins agent

    environment {
        // Make sure Maven and Java are in PATH, or configured in Jenkins
        MAVEN_HOME = "C:\\apache-maven-3.9.6"   // update if different
        PATH = "${MAVEN_HOME}\\bin;${PATH}"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', 
                    url: 'https://github.com/Sushmithar009/UIV.git',
                    credentialsId: 'github-pat'   // replace with your Jenkins credential ID
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
                bat 'java -jar target\\*.jar'
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
            echo "✅ Build and packaging successful!"
        }
        failure {
            echo "❌ Build failed. Check logs."
        }
    }
}
