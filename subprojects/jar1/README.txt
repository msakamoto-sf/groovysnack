

# 1. create test jar1:

cd javasnack/subprojects/jar1
jar cvfm testjar1-1.0.jar manifest.mf -C src/ testjar1/Greeting.class

java -jar testjar1-1.0.jar
->
Grood Morning, ABC.
Good Afternoon, DEF.

# 2. add file dependency to build.gradle

dependencies {
    compile files('subprojects/jar1/testjar1-1.0.jar')
}

see:
"49.4.4. File dependencies"
http://www.gradle.org/docs/current/userguide/dependency_management.html

"Gradle の依存関係を使いこなしてみる - bluepapa32’s Java Blog"
http://d.hatena.ne.jp/bluepapa32/20110511/1305135686

"maven 2 - Gradle: Make a 3rd party jar available to local gradle repository - Stack Overflow"
http://stackoverflow.com/questions/2572811/gradle-make-a-3rd-party-jar-available-to-local-gradle-repository

