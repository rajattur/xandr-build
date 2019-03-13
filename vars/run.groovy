import com.att.xandr.Constants
def constants = new Constants()
def call() {
    node() {
        stage('Checkout') {
            git branch: 'feature/jenkins-module', credentialsId: 'xandr-git', url: 'https://kr367s@codecloud.web.att.com/scm/st_addco/audience_builder.git'
        }
        stage('Build') {
            echo "Building..."
            def aws_region = constants.getDockerRegistry()
            println(aws_region)
            withEnv(["S3_URL=${params.s3_bucket}"]) {
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