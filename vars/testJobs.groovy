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

def tests() {
    return [
[
name: 'challenge1', 
expect:'''
1, 2, Fizz, 4, Buzz, Fizz, 7, 8, Fizz, Buzz, 11, Fizz, 13, 14, FizzBuzz, 16, 17, Fizz, 19, Buzz, Fizz, 22, 23, Fizz, Buzz, 26, Fizz, 28, 29, FizzBuzz, 31, 32, Fizz, 34, Buzz, Fizz, 37, 38, Fizz, Buzz, 41, Fizz, 43, 44, FizzBuzz, 46, 47, Fizz, 49, Buzz, Fizz, 52, 53, Fizz, Buzz, 56, Fizz, 58, 59, FizzBuzz, 61, 62, Fizz, 64, Buzz, Fizz, 67, 68, Fizz, Buzz, 71, Fizz, 73, 74, FizzBuzz, 76, 77, Fizz, 79, Buzz, Fizz, 82, 83, Fizz, Buzz, 86, Fizz, 88, 89, FizzBuzz, 91, 92, Fizz, 94, Buzz, Fizz, 97, 98, Fizz, 100
''',
nextDesc:'''
#### Challenge 2
Add a line wrap at every 14 characters.
Sample output:
```
1, 2, Fizz, 4,
 Buzz, Fizz, 7
, 8, Fizz, Buz
...
```
''' 
],
[
name: 'challenge2', 
expect:'''
1, 2, Fizz, 4,
 Buzz, Fizz, 7
, 8, Fizz, Buz
z, 11, Fizz, 1
3, 14, FizzBuz
z, 16, 17, Fiz
z, 19, Buzz, F
izz, 22, 23, F
izz, Buzz, 26,
 Fizz, 28, 29,
 FizzBuzz, 31,
 32, Fizz, 34,
 Buzz, Fizz, 3
7, 38, Fizz, B
uzz, 41, Fizz,
 43, 44, FizzB
uzz, 46, 47, F
izz, 49, Buzz,
 Fizz, 52, 53,
 Fizz, Buzz, 5
6, Fizz, 58, 5
9, FizzBuzz, 6
1, 62, Fizz, 6
4, Buzz, Fizz,
 67, 68, Fizz,
 Buzz, 71, Fiz
z, 73, 74, Fiz
zBuzz, 76, 77,
 Fizz, 79, Buz
z, Fizz, 82, 8
3, Fizz, Buzz,
 86, Fizz, 88,
 89, FizzBuzz,
 91, 92, Fizz,
 94, Buzz, Fiz
z, 97, 98, Fiz
z, 100
''',
nextDesc:'''
#### Challenge 3
Print the first 100 numbers from Fibonacci list with FizzBuzz rule and line breaks.
The Fibonacci list is:
```
    1, 1, 2, 3, 5, 8, 13, 21, 34, 55, ...
```
Sample output:
```
1, 2, Fizz, 4,
 Buzz, Fizz, 7
, 8, Fizz, Buz
...
```
''' 
],
[
name: 'challenge3', 
expect:'''
1, 1, 2, "Fizz
", "Buzz", 8, 
13, "Fizz", 34
, "Buzz", 89, 
"Fizz", 233, 3
77, "Buzz", "F
izz", 1597, 25
84, 4181, "Fiz
zBuzz", 10946,
 17711, 28657,
 "Fizz", "Buzz
", 121393, 196
418, "Fizz", 5
14229, "Buzz",
 1346269, "Fiz
z", 3524578, 5
702887, "Buzz"
, "Fizz", 2415
7817, 39088169
, 63245986, "F
izzBuzz", 1655
80141, 2679142
96, 433494437,
 "Fizz", "Buzz
", 1836311903,
 2971215073, "
Fizz", 7778742
049, "Buzz", 2
0365011074, "F
izz", 53316291
173, 862675712
72, "Buzz", "F
izz", 36543529
6162, 59128672
9879, 95672202
6041, "FizzBuz
z", 2504730781
961, 405273953
7881, 65574703
19842, "Fizz",
 "Buzz", 27777
890035288, 449
45570212853, "
Fizz", 1176690
30460994, "Buz
z", 3080615211
70129, "Fizz",
 8065155330493
93, 1304969544
928657, "Buzz"
, "Fizz", 5527
939700884757, 
89443943237914
64, "FizzBuzz"
, 234167283484
67684, 3788906
2373143900, "F
izz", 99194853
094755490, 160
50064381636707
0, "Buzz", 420
19614072748966
0, "Fizz", 110
00877783661019
00, "Fizz", "B
uzz", "Fizz", 
75401138047463
46000, 1220016
0415121877000,
 1974027421986
8226000, "Fizz
Buzz", 5168070
8854858330000,
 8362114348984
8430000, 13530
18523447067600
00, "Fizz", "F
izz"
''',
nextDesc: '''
#### Well Done
'''
]
]}


