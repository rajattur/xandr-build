import com.att.xandr.Constants

def call() {
    pipeline {
        agent any
        // agent {
        //     label "audience-builder"
        // }

        stages {
            stage('Main') {
                steps {
                    script {

                        stage('Checkout') {
                            if (env.BRANCH_NAME) {
                                checkout scm
                            } else {
                                println 'Skipping checkout as SCM is not configured'
                            }
                        }

                        stage('Build') {
                            echo "Building..."
                            def constants = new Constants()
                            def aws_region = constants.getDockerRegistry()
                            println(aws_region.aws_region)
                            withEnv(["S3_URL=${params.get("s3_bucket")}"]) {
                                sh '''
                                    echo "${S3_URL}"
                                    ls -al && \
                                    cd audiencebuilder-gui && \
                                    ls -al
                                '''        
                            }
                        }

                    }
                }
            }
        }
    }
    // node() {
    //     stage('Checkout') {
    //         git branch: 'feature/jenkins-module', credentialsId: 'xandr-git', url: 'https://kr367s@codecloud.web.att.com/scm/st_addco/audience_builder.git'
    //     }
    //     stage('Build') {
    //         echo "Building..."
    //         def constants = new Constants()
    //         def aws_region = constants.getDockerRegistry()
    //         println(aws_region.aws_region)
    //         withEnv(["S3_URL=${params.get("s3_bucket")}"]) {
    //             sh '''
    //                 echo "${S3_URL}"
    //                 ls -al && \
    //                 cd audiencebuilder-gui && \
    //                 ls -al
    //             '''        
    //         }
    //     }
    // }
}