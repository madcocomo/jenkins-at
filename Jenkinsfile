node {
   def mvnHome
   stage('Check-out') { 
      git 'https://github.com/madcocomo/fizzbuzz-adv.git'
      mvnHome = tool 'M3'
   }
   stage('Build') {
      withEnv(["MVN_HOME=$mvnHome"]) {
         if (isUnix()) {
            sh '"$MVN_HOME/bin/mvn" clean package'
         } else {
            bat(/"%MVN_HOME%\bin\mvn" clean package/)
         }
      }
      junit '**/target/surefire-reports/TEST-*.xml'
   }
   stage('Verify') {
      git 'https://github.com/madcocomo/jenkins-at.git/'
      sh 'java -jar target/*.jar > result.txt'
      sh 'diff 1.txt result.txt'
   }
}
