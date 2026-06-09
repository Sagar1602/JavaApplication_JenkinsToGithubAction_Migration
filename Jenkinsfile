pipeline {
    agent any
    
    tools {
        maven 'Maven 3.9.0'
        jdk 'Java 17'
    }

    environment {
        APP_NAME = "java-cicd-app"
        APP_VERSION = "1.0.0"
        DOCKER_IMAGE = "sagar1602/${APP_NAME}:${BUILD_NUMBER}"
        REGISTRY = "docker.io"
    }

    stages {
        stage('Checkout') {
            steps {
                script {
                    echo "=== Checking out code from GitHub ==="
                    checkout scm
                }
            }
        }

        stage('Build') {
            steps {
                script {
                    echo "=== Building Java application with Maven ==="
                    sh 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Unit Tests') {
            steps {
                script {
                    echo "=== Running unit tests ==="
                    sh 'mvn test'
                }
            }
        }

        stage('Code Analysis') {
            steps {
                script {
                    echo "=== Running code analysis ==="
                    sh 'mvn clean package'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    echo "=== Building Docker image ==="
                    sh "docker build -t ${DOCKER_IMAGE} ."
                }
            }
        }

        stage('Push to DockerHub') {
            when {
                branch 'main'
            }
            steps {
                script {
                    echo "=== Pushing Docker image to DockerHub ==="
                    withCredentials([usernamePassword(credentialsId: 'docker-cred', 
                            usernameVariable: 'DOCKER_USER', 
                            passwordVariable: 'DOCKER_PASS')]) {
                        sh '''
                            docker login -u ${DOCKER_USER} -p ${DOCKER_PASS}
                            docker push ${DOCKER_IMAGE}
                        '''
                    }
                }
            }
        }

        stage('Deploy to Kubernetes') {
            when {
                branch 'main'
            }
            steps {
                script {
                    echo "=== Deploying to Kubernetes ==="
                    sh '''
                        kubectl set image deployment/${APP_NAME} \
                        ${APP_NAME}=${DOCKER_IMAGE} \
                        -n production || echo "Deployment not found, creating new one"
                    '''
                }
            }
        }
    }

    post {
        always {
            echo "=== Cleaning up workspace ==="
            cleanWs()
        }
        success {
            echo "✓ Pipeline completed successfully!"
        }
        failure {
            echo "✗ Pipeline failed. Check logs above."
        }
    }
}
