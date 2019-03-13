def call() {
    node() {
        stage('Checkout') {
            git branch: 'feature/jenkins-module', credentialsId: 'xandr-git', url: 'https://kr367s@codecloud.web.att.com/scm/st_addco/audience_builder.git'
        }
        stage('Build') {
            echo "Building..."
        }
    }
}