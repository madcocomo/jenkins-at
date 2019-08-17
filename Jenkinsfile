pipeline {
    agent any
    tools {
        maven 'M3'
    }
    stages { 
        stage('Build') {
            steps {
                sh 'mvn clean package'
                junit '**/target/surefire-reports/TEST-*.xml'
                sh 'git config --local credential.helper "!p() { echo username=\\$GIT_USERNAME; echo password=\\$GIT_PASSWORD; }; p"'
            }
        }
        stage('Verify') {
            steps {
                script {
                    //git 'https://github.com/madcocomo/jenkins-at.git/'
                    def tests = 'v1,v2,v3,v4,v5'.split(',')

                    def tags = sh script: "git tag --merged", returnStdout: true
                    def success = false
                    for (test in tests) {
                        stage("Test ${test}") {
                            if (tags ==~ /(?s).*-pass-${test}-.*/) {
                                echo "Passed before"
                            } else {
                                sh "echo ${test} > expect"
                                sh 'java -jar target/*.jar > result'
                                sh 'diff expect result'

                                sh "git tag ${BRANCH_NAME}-pass-${test}-${BUILD_ID}"
                                withCredentials([
                                  usernamePassword(credentialsId: 'github', usernameVariable: 'GIT_USERNAME', passwordVariable: 'GIT_PASSWORD')
                                ]) {
                                  sh 'git push origin --tags'
                                }
                                success = true
                                echo "Passed"
                            }
                        }
                        if (success) break;
                    }                    
                }
            }
        }
    }
}
