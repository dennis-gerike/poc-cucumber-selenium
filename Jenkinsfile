pipeline {
    agent any
    environment {
        // Host path to the project root - required to calculate workspace host path for DooD
        HOST_PROJECT_ROOT = "/home/dennis/Documents/projects/dennis-gerike/poc-cucumber-selenium"
    }
    stages {
        stage('Build & Test') {
            steps {
                script {
                    // Calculate the host path of the current workspace for Docker-out-of-Docker (DooD)
                    // The Jenkins controller's /var/jenkins_home is mapped to ${HOST_PROJECT_ROOT}/jenkins/jenkins_home on the host.
                    def workspaceHostPath = env.WORKSPACE.replace("/var/jenkins_home", "${HOST_PROJECT_ROOT}/jenkins/jenkins_home")
                    def mavenRepoHostPath = "${HOST_PROJECT_ROOT}/jenkins/maven_repo"
                    
                    echo "Mapping host workspace ${workspaceHostPath} to container workspace ${env.WORKSPACE}"
                    echo "Using Maven repository cache at ${mavenRepoHostPath}"
                    
                    // Run tests in the container, mounting the host workspace path and maven repo cache.
                    // This ensures results are written directly to the Jenkins workspace and dependencies are cached.
                    docker.image('poc-selenium-test-runner:latest').inside("-v ${workspaceHostPath}:${env.WORKSPACE} -v ${mavenRepoHostPath}:/maven-repo -w ${env.WORKSPACE}") {
                        // Ensure output directory exists
                        sh 'mkdir -p target/cucumber-reports'
                        sh 'mvn test -Dmaven.repo.local=/maven-repo'
                    }
                }
            }
        }
    }
    post {
        always {
            // Jenkins plugins will now find these files directly in the workspace
            junit testResults: '**/target/surefire-reports/*.xml', allowEmptyResults: true
            
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
            
            archiveArtifacts artifacts: 'target/cucumber-reports/**/*', allowEmptyArchive: true
        }
    }
}
