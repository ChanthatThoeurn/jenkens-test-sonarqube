
pipeline {
    agent any

    options {
        timestamps()
        disableConcurrentBuilds()
        skipDefaultCheckout(true)
    }

    stages {

        stage('Checkout GitHub') {
            steps {
                checkout scm
            }
        }

        stage('Check Environment') {
            steps {
                sh '''
                    echo "Checking Java environment..."
                    java -version
                    javac -version
                    git --version
                '''
            }
        }

        stage('Compile Java') {
            steps {
                sh '''
                    echo "Compiling Java source code..."

                    mkdir -p build/classes

                    javac -d build/classes src/jenkins.java
                '''
            }
        }

        stage('Run Java') {
            steps {
                sh '''
                    echo "Running Java application..."

                    java -cp build/classes jenkins
                '''
            }
        }

        stage('SonarQube Analysis') {
            steps {
                script {
                    def scannerHome = tool 'SonarScanner'

                    withSonarQubeEnv(
                        installationName: 'SonarQube',
                        credentialsId: 'sonarqube-github-token'
                    ) {
                        sh """
                            "${scannerHome}/bin/sonar-scanner"
                        """
                    }
                }
            }
        }

        stage('Quality Gate') {
            steps {
                timeout(time: 10, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }

    }

    post {
        success {
            echo 'SUCCESS: Java Build and Quality Gate PASSED!'
        }

        failure {
            echo 'FAILED: Check Java Compilation, SonarQube Analysis or Quality Gate.'
        }

        aborted {
            echo 'ABORTED: Pipeline stopped or timed out.'
        }

        always {
            echo 'Java CI Pipeline Finished.'
        }
    }
}
