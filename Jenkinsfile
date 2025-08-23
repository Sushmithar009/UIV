pipeline {
    agent any

    tools {
        maven 'maven-3.8.8'   // name you configure in Jenkins -> Global Tool Configuration
        jdk 'JDK-17'        // same for Java
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', 
                    url: 'https://github.com/Sushmithar009/UIV.git'
            }
        }

        stage('Build') {
            steps {
                sh "mvn clean install -DskipTests"
            }
        }

        stage('Test') {
            steps {
                sh "mvn test"
            }
        }

        stage('Package') {
            steps {
                sh "mvn package -DskipTests"
            }
        }

        stage('Run Spring Boot App') {
            steps {
                sh "java -jar target/*.jar"
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
