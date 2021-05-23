def supermarket() {
    return [
[
name: 'story1', 
command: 'rm -r src/test/java; git checkout --ignore-other-worktrees remotes/origin/at -- src/test/acceptance-test src/test/resources/acceptance-test src/test/java/cucumber; ./gradlew clean test -Pat=story1'
],
[
name: 'story2', 
command: 'rm -r src/test/java; git checkout --ignore-other-worktrees remotes/origin/at -- src/test/acceptance-test src/test/resources/acceptance-test src/test/java/cucumber; ./gradlew clean test -Pat=story2'
],
[
name: 'story3', 
command: 'rm -r src/test/java; git checkout --ignore-other-worktrees remotes/origin/at -- src/test/acceptance-test src/test/resources/acceptance-test src/test/java/cucumber; ./gradlew clean test -Pat=story3'
],
[
name: 'story4', 
command: 'rm -r src/test/java; git checkout --ignore-other-worktrees remotes/origin/at -- src/test/acceptance-test src/test/resources/acceptance-test src/test/java/cucumber; ./gradlew clean test -Pat=story4'
],
[
name: 'story5', 
command: 'rm -r src/test/java; git checkout --ignore-other-worktrees remotes/origin/at -- src/test/acceptance-test src/test/resources/acceptance-test src/test/java/cucumber; ./gradlew clean test -Pat=story5'
],
[
name: 'story6', 
command: 'rm -r src/test/java; git checkout --ignore-other-worktrees remotes/origin/at -- src/test/acceptance-test src/test/resources/acceptance-test src/test/java/cucumber; ./gradlew clean test -Pat=story6'
]
]}
