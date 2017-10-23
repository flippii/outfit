node {

    stage('Configure') {
        env.PATH = "${tool 'maven-3.3.9'}/bin:${env.PATH}"
    }

    stage('Checkout') {
        git 'https://github.com/flippii/outfit'
    }

    stage('Build') {
        sh 'mvn -B -V -U -e clean package'
    }

    stage('Deploy') {

    }

}