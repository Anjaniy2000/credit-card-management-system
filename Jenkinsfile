pipeline {
    agent any

    environment {
        DOCKER_REGISTRY = 'anjaniy472'
        DOCKER_REPO = 'credit-card-management-system'
        DOCKER_IMAGE = "${DOCKER_REGISTRY}/${DOCKER_REPO}"
        POSTGRES_DB = 'credit_card_management_system'
        POSTGRES_USER = 'postgres'
        POSTGRES_PASSWORD = 'root'
    }

    tools {
        maven 'maven'
    }

    stages {

        stage('Build Project') {
            steps {
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/Anjaniy2000/credit-card-management-system']])
                bat "mvn clean install"
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("${DOCKER_IMAGE}")
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    bat "docker login -u nerdx01salekar@gmail.com -p Anjaniy@12345"
                    bat "docker push ${DOCKER_IMAGE}"
                }
            }
        }

        stage('Deploy with Docker Compose') {
            steps {
                script {
                    writeFile file: 'docker-compose.yml', text: """
                    version: '3'
                    services:
                      app:
                        image: ${DOCKER_IMAGE}
                        ports:
                          - "8081:8080"
                        depends_on:
                          - postgres
                        restart: on-failure
                        environment:
                          SPRING_DATASOURCE_URL: jdbc:postgresql://postgres:5432/${POSTGRES_DB}
                          SPRING_DATASOURCE_USERNAME: ${POSTGRES_USER}
                          SPRING_DATASOURCE_PASSWORD: ${POSTGRES_PASSWORD}
                          SPRING_JPA_HIBERNATE_DDL_AUTO: update

                      postgres:
                        image: postgres:latest
                        environment:
                          POSTGRES_DB: ${POSTGRES_DB}
                          POSTGRES_USER: ${POSTGRES_USER}
                          POSTGRES_PASSWORD: ${POSTGRES_PASSWORD}
                        volumes:
                          - ./data:/var/lib/postgresql/data
                    """
                }

                bat 'docker-compose up -d'
            }
        }
    }

    post {
        success {
            echo 'Deployment successful!'
        }
        failure {
            echo 'Deployment failed!'
        }
    }
}
