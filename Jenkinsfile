node {
   def mvnHome
   stage('Check-out') { // for display purposes
      // Get some code from a GitHub repository
      git 'https://github.com/madcocomo/fizzbuzz-adv.git'
      // Get the Maven tool.
      // ** NOTE: This 'M3' Maven tool must be configured
      // **       in the global configuration.           
      mvnHome = tool 'M3'
   }
   stage('Build') {
      // Run the maven build
      withEnv(["MVN_HOME=$mvnHome"]) {
         if (isUnix()) {
            sh '"$MVN_HOME/bin/mvn" clean package'
         } else {
            bat(/"%MVN_HOME%\bin\mvn" clean package/)
         }
      }
      junit '**/target/surefire-reports/TEST-*.xml'
      //archiveArtifacts 'target/*.jar'
   }
   stage('Verify') {
      java -jar target/*.jar > result.txt
      sh 'diff -d 1.txt result.txt'
   }
}
