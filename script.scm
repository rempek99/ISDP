pipeline {
    agent any

    tools {
    maven "3.6.3"
}
    stages {
        stage('Build, Package') {
            steps {

                git 'https://github.com/rempek99/ISDP.git'

                // Run Maven on a Unix agent.
                sh "mvn -Dmaven.test.failure.ignore=true -f WM/pom.xml clean package"
                sh "mv -v /var/lib/jenkins/workspace/ISDP/WM/target/WM-1.1.war /var/lib/jenkins/workspace/ISDP/WM/target/WM.war" 
            }
        }
        stage('Create DB connection, Deploy') {
            steps {
                //sudo mode
                //Running JDB
                sh "java -Dderby.system.home=/home/student/JavaTools/.derbydb -jar /home/student/JavaTools/db-derby-10.14.2.0-bin/lib/derbyrun.jar server start&"
                sh 'sleep 3'
                sh "sudo /home/student/JavaTools/db-derby-10.14.2.0-bin/bin/ij connect 'jdbc:derby://localhost:1527/WM;create=true;user=WM;password=WM';"
                sh "sudo /home/student/JavaTools/db-derby-10.14.2.0-bin/bin/ij run '/var/lib/jenkins/workspace/ISDP/WM/src/main/resources/createDB.sql';" 
                sh "sudo /home/student/JavaTools/db-derby-10.14.2.0-bin/bin/ij run '/var/lib/jenkins/workspace/ISDP/WM/src/main/resources/initDB.sql';" 
                sh "sudo /home/student/JavaTools/db-derby-10.14.2.0-bin/bin/ij run 'script.sql'; > myoutput.txt"

                sh "/payara/payara5.2020.5/bin/asadmin start-domain"
                sh "/payara/payara5.2020.5/bin/asadmin -u admin deploy --force /var/lib/jenkins/workspace/ISDP/WM/target/WM.war"
            }
        }
        stage('Execute tests') {
            steps {              
                    sh "mvn -Dmaven.test.failure.ignore=true -f Tests/pom.xml test"
                sh "mvn -DSuiteXmlFile=testXML.xml -f Tests/pom.xml test"
                    step([$class: 'Publisher', reportFilenamePattern: '**/target/surefire-reports/testng-results.xml'])
            }
        }
        stage('Undeploy') {
            steps {
                sh '/payara/payara5.2020.5/bin/asadmin stop-domain'
                    //sh "/home/student/JavaTools/db-derby-10.14.2.0-bin/bin/stopNetworkServer"
            }
        }
    }
}

