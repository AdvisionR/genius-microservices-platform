pipeline {
    agent any

    environment {
        DOCKER_REGISTRY = 'suatbayir'
        GIT_KEY = credentials('GIT_KEY')
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
                stage('Config Server') {
                    steps {
                        script {
                            sh """
                            docker build -t ${DOCKER_REGISTRY}/config-server:latest -f config-server/Dockerfile .
                            """
                        }
                    }
                }
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
                stage('Chat Service') {
                    steps {
                        script {
                            sh """
                            docker build -t ${DOCKER_REGISTRY}/chat-service:latest -f chat-service/Dockerfile .
                            """
                        }
                    }
                }
                stage('Api Gateway') {
                    steps {
                        script {
                            sh """
                            docker build -t ${DOCKER_REGISTRY}/api-gateway:latest -f api-gateway/Dockerfile .
                            """
                        }
                    }
                }
                stage('Discovery Server') {
                    steps {
                        script {
                            sh """
                            docker build -t ${DOCKER_REGISTRY}/discovery-server:latest -f discovery-server/Dockerfile .
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
                        echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin
                        docker push ${DOCKER_REGISTRY}/auth-service:latest
                        docker push ${DOCKER_REGISTRY}/user-service:latest
                        docker push ${DOCKER_REGISTRY}/chat-service:latest
                        docker push ${DOCKER_REGISTRY}/config-server:latest
                        docker push ${DOCKER_REGISTRY}/api-gateway:latest
                        docker push ${DOCKER_REGISTRY}/discovery-server:latest
                        docker logout
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
                    writeFile file: '.env', text: """
                        GIT_KEY=${GIT_KEY}
                        PROFILE=dev
                        ENABLE_DEBUG=true
                        DEBUG_AUTH_PORT=5005
                        DEBUG_USER_PORT=5006
                    """
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
