pipeline {
    agent {
        label 'JavaAgent'        
    }
    options {
        skipStagesAfterUnstable()
    }
    stages {
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
/*        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }*/
/*        stage('Deliver') { 
            steps {
                sh './jenkins/scripts/deliver.sh' 
            }
        }
    }*/
        stage("Docker build") {
            steps {
                sh '''
                    if [ "$GIT_BRANCH" = "develop" ]; then
                        TAG=latest  
                        ENV=dev                        
                    fi

                    if [ "$GIT_BRANCH" = "master" ]; then
                        TAG=latest-prod
                        ENV=prod
                    fi  

                    if [ "$(sudo docker images -q wms-user-login:$TAG 2> /dev/null)" != "" ]; then
                        sudo docker rmi -f wms-user-login:$TAG
                    fi

                    if [ "$(sudo docker images -q 294028721626.dkr.ecr.us-east-1.amazonaws.com/wms-user-login:$TAG 2> /dev/null)" != "" ]; then
                        sudo docker rmi -f 294028721626.dkr.ecr.us-east-1.amazonaws.com/wms-user-login:$TAG
                    fi                    

                    aws ecr get-login-password --region us-east-1 | sudo docker login --username AWS --password-stdin 294028721626.dkr.ecr.us-east-1.amazonaws.com
                    sudo docker build -t wms-user-login:$TAG .
                    sudo docker tag wms-user-login:$TAG 294028721626.dkr.ecr.us-east-1.amazonaws.com/wms-user-login:$TAG
                    sudo docker push 294028721626.dkr.ecr.us-east-1.amazonaws.com/wms-user-login:$TAG
                    aws ecs update-service --cluster wms-cluster-$ENV --service wms-user-login-$ENV --force-new-deployment
                '''
            }
        }    
    }

    post {
        always {
            echo 'Finish.'
        }
        
        success {
            slackSend(
                channel: "tech-wms", 
                color: 'good', 
                message: "Project: wms-user-login \nStatus: Success \nBuild URL: $env.JOB_URL \nMessage: Package sent to AWS ECS."
            )
            
            echo 'Success :)'
        }
        
        failure {
            slackSend(
                channel: "tech-wms", 
                color: 'danger', 
                message: "Project: wms-user-login \nStatus: Failed \nBuild URL: $env.JOB_URL"
            )
            
            echo 'Failed :('            
        }  
    }
}
