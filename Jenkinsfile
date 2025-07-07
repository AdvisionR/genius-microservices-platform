pipeline {
    agent any

    environment {
        DOCKER_REGISTRY = 'suatbayir'
        MAVEN_OPTS = "-Dmaven.test.failure.ignore=false"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                script {
                    docker.image('maven:3.9.9-eclipse-temurin-21-alpine').inside {
                        sh 'mvn clean verify -DskipTests=false'
                    }
                }
            }
        }

        stage('Docker Build') {
            parallel {
                stage('Auth Service') {
                    steps {
                        script {
                            sh """
                            docker build -t ${DOCKER_REGISTRY}/auth-service:latest -f auth-service/Dockerfile .
                            """
                        }
                    }
                }
                stage('User Service') {
                    steps {
                        script {
                            sh """
                            docker build -t ${DOCKER_REGISTRY}/user-service:latest -f user-service/Dockerfile .
                            """
                        }
                    }
                }
            }
        }

        stage('Docker Push') {
            when {
                branch 'main'
            }

            steps {
                withCredentials([usernamePassword(credentialsId: "DOCKER_CREDENTIALS_ID", usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                    script {
                        sh """
                        echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin ${DOCKER_REGISTRY}
                        docker push ${DOCKER_REGISTRY}/auth-service:latest
                        docker push ${DOCKER_REGISTRY}/user-service:latest
                        docker logout ${DOCKER_REGISTRY}
                        """
                    }
                }
            }
        }

        stage('Deploy') {
            when {
                branch 'main'
            }

            steps {
                script {
                    echo "Deploying application..."
                    sh '''
                        docker compose down
                        docker compose pull
                        docker compose up --build -d
                    '''
                }
            }
        }
    }

    post {
        success {
            echo "✅ Pipeline successfully completed!"
        }
        failure {
            echo "❌ Pipeline failed."
        }
    }
}
