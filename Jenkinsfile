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
                    // Copy results to the Jenkins workspace so plugins can find them
                    sh 'mkdir -p target && cp -r /workspace/target/* target/ || true'
                }
            }
        }
    }
    post {
        always {
            junit '**/target/surefire-reports/*.xml'
            cucumber buildStatus: 'null',
                     fileIncludePattern: '**/cucumber.json',
                     jsonReportDirectory: 'target/cucumber-reports',
                     sortingMethod: 'ALPHABETICAL'
            publishHTML([allowMissing: true,
                         alwaysLinkToLastBuild: true,
                         keepAll: true,
                         reportDir: 'target/cucumber-reports',
                         reportFiles: 'cucumber.html',
                         reportName: 'Cucumber HTML Report',
                         reportTitles: ''])
            archiveArtifacts artifacts: '**/target/cucumber-reports/**/*', allowEmptyArchive: true
        }
    }
}
