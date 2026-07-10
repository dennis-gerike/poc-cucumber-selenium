pipeline {
    agent any
    environment {
        // In a real setup, this might be dynamically determined or passed as a parameter
        HOST_PROJECT_ROOT = "/home/dennis/Documents/projects/dennis-gerike/poc-cucumber-selenium"
    }
    stages {
        stage('Build & Test') {
            steps {
                script {
                    // Fix for dubious ownership inside the container
                    sh 'git config --global --add safe.directory /workspace || true'
                    // Using the modern Jenkins Docker DSL
                    docker.image('poc-selenium-test-runner:latest').inside("-v ${HOST_PROJECT_ROOT}:/app -w /app") {
                        sh 'mvn test'
                    }
                }
            }
        }
    }
    post {
        always {
            junit '**/target/surefire-reports/*.xml'
            archiveArtifacts artifacts: '**/target/cucumber-reports/**/*', allowEmptyArchive: true
        }
    }
}
