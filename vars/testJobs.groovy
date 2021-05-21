def runTests(pipe, tests) {
    def tags = getTags()
    def success = false
    for (test in tests) {
        stage("Test ${test.name}") {
            if (isPassTagExists(tags, test.name)) {
                echo "Passed before"
            } else {
                verify(test)
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

def verify(test) {
    sh "${test.command}"
}

def isPassTagExists(tags, name) {
    return tags ==~ /(?s).*-pass-${name}-.*/
}

def updateGitRepo(pipe, test) {
    sh 'git config --local credential.helper "!p() { echo username=\\$GIT_USERNAME; echo password=\\$GIT_PASSWORD; }; p"'
    sh "git tag ${pipe.BRANCH_NAME}-pass-${test.name}-${pipe.BUILD_ID}"
    withCredentials([
      usernamePassword(credentialsId: 'github', usernameVariable: 'GIT_USERNAME', passwordVariable: 'GIT_PASSWORD')
    ]) {
      sh 'git push origin --tags'
    }
}

