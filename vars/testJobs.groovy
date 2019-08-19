def tests() {
    return [
[name: 'test1', desc:'111', expect:'v1'],
[name: 'test2', desc:'222', expect:'v2'],
[name: 'test3', desc:'333', expect:'v3'],
[name: 'test4', desc:'444', expect:'v4'],
[name: 'test5', desc:'555', expect:'v5']
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
                tagBuild(pipe, test.name)
                success = true
                echo "Passed"
            }
        }
        if (success) break;
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

def isPassTagExists(tags, test) {
    return tags ==~ /(?s).*-pass-${test}-.*/
}

def tagBuild(pipe, test) {
    sh 'git config --local credential.helper "!p() { echo username=\\$GIT_USERNAME; echo password=\\$GIT_PASSWORD; }; p"'
    sh "git tag ${pipe.BRANCH_NAME}-pass-${test}-${pipe.BUILD_ID}"
    withCredentials([
      usernamePassword(credentialsId: 'github', usernameVariable: 'GIT_USERNAME', passwordVariable: 'GIT_PASSWORD')
    ]) {
      sh 'git push origin --tags'
    }
}
