pipeline {
    agent any
	
    tools {
	maven "3.6.3"
}
    stages {
        stage('Build') {
            steps {
                // Get some code from a GitHub repository
                git 'https://github.com/rempek99/ISDP.git'

                // Run Maven on a Unix agent.
                sh "mvn -Dmaven.test.failure.ignore=true -f WM/pom.xml clean package"

                // To run Maven on a Windows agent, use
                // bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }

            
            }
        }
    
}
