pipeline {
    agent any

    options {
        timestamps()
        disableConcurrentBuilds()
    }

    stages {
        stage('Checkout GitHub') {
            steps {
                checkout scm
            }
        }

        stage('Compile Java') {
            steps {
                sh '''
                    java -version
                    javac -version

                    mkdir -p build/classes
                    javac -d build/classes src/jenkins.java
                '''
            }
        }
        stage('Run Java') {
                    steps {
                        sh 'java -cp build/classes Hello'
                    }
                }

                stage('SonarQube Analysis') {
                    steps {
                        script {
                            def scannerHome = tool 'SonarScanner'

                            withSonarQubeEnv('SonarQube') {
                                sh "${scannerHome}/bin/sonar-scanner"
                            }
                        }
                    }
                }
        }
            post {
                    success {
                        echo 'Java CI Analysis Submitted'
                    }

                    failure {
                        echo 'CI Pipeline FAILED'
                    }
            }
    }