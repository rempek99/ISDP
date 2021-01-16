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

		//Running JDB
		/home/student/JavaTools/db-derby-10.14.2.0-bin/bin/ij connect 'jdbc:derby://localhost:1527/WM;create=true;user=WM;password=WM';
			
		//Running & deploy on Payara
            	/home/student/JavaTools/payara5.2020.5/bin/asadmin start-domain
		/home/student/JavaTools/payara5.2020.5/bin/asadmin deploy --force /var/lib/jenkins/workspace/ISDP/WM/target/WM-1.1.war		 
}

            
            }
        }
    
}
