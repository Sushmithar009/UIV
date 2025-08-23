pipeline {
    agent any

    tools {
        maven 'Maven-3.9.6'   // configure this under Manage Jenkins → Tools
        jdk 'JDK-17'          // or whatever JDK you configured in Jenkins
    }

    environment {
        SPRING_PROFILES_ACTIVE = "dev"   // change to prod/qa if needed
        DB_URL = "jdbc:mysql://localhost:3306/mydb"
        DB_USER = "root"
        DB_PASSWORD = "password"
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/Sushmithar009/UIV.git',
                    credentialsId: 'github-pat'  // replace with your Jenkins GitHub credential ID
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('Run Unit Tests') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }

        stage('Database Migration') {
            when {
                expression { return fileExists('src/main/resources/db/migration') } // Only if migrations exist
            }
            steps {
                echo "Running DB migrations..."
                // Example: Flyway migration (replace if you use Liquibase)
                sh 'mvn flyway:migrate -Dflyway.url=$DB_URL -Dflyway.user=$DB_USER -Dflyway.password=$DB_PASSWORD'
            }
        }

        stage('Archive Artifacts') {
            steps {
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
            }
        }

        stage('Run Application (Optional)') {
            when {
                branch 'main'
            }
            steps {
                echo "Starting Spring Boot app..."
                sh 'nohup java -jar target/*.jar --spring.profiles.active=$SPRING_PROFILES_ACTIVE &'
            }
        }
    }

    post {
        success {
            echo "✅ Build and package successful!"
        }
        failure {
            echo "❌ Build failed!"
        }
    }
}
