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


                sh "mv -v /var/lib/jenkins/workspace/ISDP/WM/target/WM-1.1.war /var/lib/jenkins/workspace/ISDP/WM/target/WM.war" 


        //Running JDB
        sh "java -Dderby.system.home=/home/student/JavaTools/.derbydb -jar /home/student/JavaTools/db-derby-10.14.2.0-bin/lib/derbyrun.jar server start&"
        sh 'sleep 5'
        sh "/home/student/JavaTools/db-derby-10.14.2.0-bin/bin/ij connect 'jdbc:derby://localhost:1527/WM;create=true;user=WM;password=WM';"
        sh "/home/student/JavaTools/db-derby-10.14.2.0-bin/bin/ij run '/var/lib/jenkins/workspace/ISDP/WM/src/main/resources/createDB.sql';" 
        sh "/home/student/JavaTools/db-derby-10.14.2.0-bin/bin/ij run '/var/lib/jenkins/workspace/ISDP/WM/src/main/resources/initDB.sql';" 

             sh "whoami" 
        //Running & deploy on Payara
            sh "/payara/payara5.2020.5/bin/asadmin start-domain"
            sh "/payara/payara5.2020.5/bin/asadmin -u admin deploy --force /var/lib/jenkins/workspace/ISDP/WM/target/WM.war"
            sh "mvn -f Tests/pom.xml clean compile"
            sh"sudo chmod -x /var/lib/jenkins/workspace/ISDP/Tests/gekodriver"
            sh "mvn -f Tests/pom.xml  test"
            sh '/payara/payara5.2020.5/bin/asadmin stop-domain'

            //sh "/home/student/JavaTools/db-derby-10.14.2.0-bin/bn/stopNetworkServer"
}


            }
        }
}
