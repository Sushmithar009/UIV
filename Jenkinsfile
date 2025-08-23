pipeline {
    agent any

    environment {
        // Set your Maven installation path
        MAVEN_HOME = "C:\\Program Files\\apache-maven-3.8.8-bin\\apache-maven-3.8.8"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/Sushmithar009/UIV.git',
                    credentialsId: 'Sushmithar009' // replace with your GitHub credentials ID in Jenkins
            }
        }

        stage('Build') {
    steps {
        bat "\"${env.MAVEN_HOME}\\bin\\mvn.cmd\" clean install -DskipTests"
    }
}
stage('Test') {
    steps {
        bat "\"${env.MAVEN_HOME}\\bin\\mvn.cmd\" test"
    }
}
stage('Package') {
    steps {
        bat "\"${env.MAVEN_HOME}\\bin\\mvn.cmd\" package -DskipTests"
    }
}
stage('Run Spring Boot App') {
    steps {
        bat "java -jar target/*.jar"
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
