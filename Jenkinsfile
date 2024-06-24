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

    stages {
        stage('Checkout') {
            steps {
                // Checkout the code from your repository
                git 'https://github.com/Anjaniy2000/credit-card-management-system.git'
            }
        }

//         stage('Build Docker Image') {
//             steps {
//                 // Build the Docker image for the Spring Boot application
//                 script {
//                     docker.build("${DOCKER_IMAGE}")
//                 }
//             }
//         }
//
//         stage('Push Docker Image') {
//             steps {
//                 // Push the Docker image to the Docker registry
//                 script {
//                     docker.withRegistry('https://index.docker.io/v1/', 'docker-credentials-id') {
//                         docker.image("${DOCKER_IMAGE}").push()
//                     }
//                 }
//             }
//         }
//
//         stage('Deploy with Docker Compose') {
//             steps {
//                 // Create a docker-compose.yml file
//                 script {
//                     writeFile file: 'docker-compose.yml', text: """
//                     version: '3.1'
//                     services:
//                       app:
//                         image: ${DOCKER_IMAGE}
//                         ports:
//                           - "8081:8080"
//                         depends_on:
//                           - postgres
//                         environment:
//                           SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/${POSTGRES_DB}
//                           SPRING_DATASOURCE_USERNAME: ${POSTGRES_USER}
//                           SPRING_DATASOURCE_PASSWORD: ${POSTGRES_PASSWORD}
//
//                       postgres:
//                         image: postgres:latest
//                         environment:
//                           POSTGRES_DB: ${POSTGRES_DB}
//                           POSTGRES_USER: ${POSTGRES_USER}
//                           POSTGRES_PASSWORD: ${POSTGRES_PASSWORD}
//                         volumes:
//                           - pgdata:/var/lib/postgresql/data
//                     """
//                 }
//
//                 // Deploy the application using Docker Compose
//                 sh 'docker-compose up -d'
//             }
//         }
//     }

    post {
        success {
            // Actions to perform on successful deployment
            echo 'Deployment successful!'
        }
        failure {
            // Actions to perform if deployment fails
            echo 'Deployment failed!'
        }
    }
}
