pipeline {
    agent any

    environment {
    // === 사용자 수정 영역 ===
    GIT_URL                = 'https://github.com/JHa9980/spring_jenkins.git'
    GIT_BRANCH             = 'main'            // 또는 main
    GIT_ID                 = 'skala-github-id'   // GitHub PAT credential ID
    IMAGE_NAME             = 'sk081-spring-jenkins'    
    // =======================
    IMAGE_TAG              = '1.0.0'    
    IMAGE_REGISTRY_URL     = 'amdp-registry.skala-ai.com'
    IMAGE_REGISTRY_PROJECT = 'skala25a'

    DOCKER_CREDENTIAL_ID   = 'skala-image-registry-id'  // Harbor 인증 정보 ID
  }

    stages {
        stage('Checkout') {
            steps {
                echo 'Cloning Repository'
                checkout scm
            }
        }

        stage('Build with Maven') {
            steps {
                dir('springbootsample') {
                    echo 'Building Spring Boot application'
                    sh 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Build & Push Docker Image') {
            steps {
                script {
                    env.IMAGE_TAG = "v${env.BUILD_NUMBER}"
                    env.IMAGE_REF = "${IMAGE_REGISTRY_URL}/${IMAGE_NAME}:${IMAGE_TAG}"
                    
                    dir('springbootsample') {
                        docker.withRegistry("https://${IMAGE_REGISTRY_URL}", DOCKER_CREDENTIAL_ID) {
                            def appImage = docker.build(IMAGE_REF, '.')
                            appImage.push()
                        }
                    }
                    echo "Successfully pushed ${IMAGE_REF}"
                }
            }
        }

        stage('Update Kubernetes Manifest') {
            steps {
                dir('springbootsample/k8s') {
                    sh 'echo "--- BEFORE ---"'
                    sh 'grep -n \'image:\' deployment.yaml || true'
                    sh "sed -i 's|image: .*|image: ${IMAGE_REF}|g' deployment.yaml"
                    sh 'echo "--- AFTER ---"'
                    sh 'grep -n \'image:\' deployment.yaml || true'
                }
            }
        }

        stage('Git Commit & Push (GitOps)') {
            steps {
                withCredentials([usernamePassword(credentialsId: GIT_ID, usernameVariable: 'GIT_USER', passwordVariable: 'GIT_PASS')]) {
                    sh 'git config --global user.name "Jenkins CI"'
                    sh 'git config --global user.email "jenkins@example.com"'
                    
                    // 원격 저장소 URL을 인증 정보와 함께 설정
                    def remoteUrl = GIT_URL.replace("https://", "https://${GIT_USER}:${GIT_PASS}@")
                    
                    sh "git add springbootsample/k8s/deployment.yaml"
                    sh "git commit -m '[CI] Update image to ${IMAGE_REF}'"
                    sh "git push ${remoteUrl} HEAD:${GIT_BRANCH}"
                    echo "Pushed manifest changes to ${GIT_BRANCH} branch."
                }
            }
        }
    }
}
