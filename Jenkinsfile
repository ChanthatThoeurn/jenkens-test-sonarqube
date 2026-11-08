
pipeline {
    agent any

    options {
        skipDefaultCheckout(true)
    }

    stages {
        stage('Checkout GitHub') {
            steps {
                echo 'Cloning source code from GitHub...'
                checkout scm
            }
        }

        stage('Check Java') {
            steps {
                sh '''
                    java -version
                    javac -version
                '''
            }
        }

        stage('Compile Java') {
            steps {
                echo 'Compiling Java source code...'

                sh '''
                    mkdir -p build
                    javac -d build src/jenkins.java
                '''
            }
        }

        stage('SonarQube Analysis') {
            steps {
                echo 'Checking code quality...'

                script {
                    def scannerHome = tool 'SonarScanner'

                    withSonarQubeEnv() {
                        sh """
                            "${scannerHome}/bin/sonar-scanner"
                        """
                    }
                }
            }
        }

        stage('Quality Gate') {
            steps {
                echo 'Checking SonarQube Quality Gate...'

                timeout(time: 10, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
    }

    post {
        success {
            echo 'SUCCESS: Code Quality PASSED!'
        }

        failure {
            echo 'FAILURE: Check the Jenkins logs.'
        }

        aborted {
            echo 'ABORTED: Quality Gate or timeout.'
        }

        always {
            echo 'CI Pipeline Finished.'
        }
    }
}
