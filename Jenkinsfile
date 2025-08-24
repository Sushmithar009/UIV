pipeline {
    agent any

    tools {
        maven 'maven-3.8.8'   // configure in Global Tool Configuration
        jdk 'JDK-17'        // configure in Global Tool Configuration
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/Sushmithar009/UIV.git',
                    credentialsId: 'Sushmithar009'
            }
        }

        stage('Build') {
            steps {
                bat '"C:\\Windows\\System32\\cmd.exe" /c "mvn clean install -DskipTests"'
            }
        }

        stage('Test') {
            steps {
                bat '"C:\\Windows\\System32\\cmd.exe" /c "mvn test"'
            }
        }

        stage('Package') {
            steps {
                bat '"C:\\Windows\\System32\\cmd.exe" /c "mvn package"'
            }
        }

        stage('Run Spring Boot App') {
            steps {
                bat '"C:\\Windows\\System32\\cmd.exe" /c "java -jar target\\*.jar"'
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
