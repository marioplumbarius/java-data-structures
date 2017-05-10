node {
    stage('checkout'){
        checkout scm
    }

    stage('test'){
        sh "./gradlew clean coberturaCheck check"
    }

    stage('done'){
        sh "echo finished!"
    }
}