def tests() {
    return [
[name: 'test1', nextDesc:'print "v2"', expect:'v1'],
[name: 'test2', nextDesc:'print "v3"', expect:'v2'],
[name: 'test3', nextDesc:'print "v4"', expect:'v3'],
[name: 'test4', nextDesc:'print "v5"', expect:'v4'],
[name: 'test5', nextDesc:'Well down', expect:'v5']
    ]
}

def runTests(pipe) {
    def tags = getTags()
    def success = false
    for (test in tests()) {
        stage("Test ${test.name}") {
            if (isPassTagExists(tags, test.name)) {
                echo "Passed before"
            } else {
                verify(test.expect)
                updateGitRepo(pipe, test)
                success = true
                echo "Passed"
            }
        }
        if (success) break
    }
}

def getTags() {
    def tags = sh script: "git tag --merged", returnStdout: true
    return tags
}

def verify(expect) {
    sh "echo ${expect} > expect"
    sh 'java -jar target/*.jar > result'
    sh 'diff expect result'
}

def isPassTagExists(tags, name) {
    return tags ==~ /(?s).*-pass-${name}-.*/
}

def updateGitRepo(pipe, test) {
    sh 'git config --local credential.helper "!p() { echo username=\\$GIT_USERNAME; echo password=\\$GIT_PASSWORD; }; p"'
    sh "echo '${test.nextDesc}' >> README.md"
    sh "git add README.md"
    sh "git config user.name 'jenkins'"
    sh "git config user.email 'jenkins@tdd.io'"
    sh "git commit -m 'update README'"

    sh "git tag ${pipe.BRANCH_NAME}-pass-${test.name}-${pipe.BUILD_ID}"
    withCredentials([
      usernamePassword(credentialsId: 'github', usernameVariable: 'GIT_USERNAME', passwordVariable: 'GIT_PASSWORD')
    ]) {
      sh "git push origin ${pipe.BRANCH_NAME}"
      sh 'git push origin --tags'
    }
}
