# AuthModule

Kotlin Multiplatform Library

### Publish to MavenCentral

1) Registering a Sonatype account as described here: 
   https://dev.to/kotlin/how-to-build-and-publish-a-kotlin-multiplatform-library-going-public-4a8k
2) Add developer id, name, email and the project url to
   `/convention-plugins/src/main/kotlin/convention.publication.gradle.kts`
3) Add the secrets to `local.properties`:

```
signing.keyId=...
signing.password=...
signing.secretKeyRingFile=...
ossrhUsername=...
ossrhPassword=...
```

4) Run `./gradlew :common:publishAllPublicationsToSonatypeRepository`

### Build platform artifacts

#### Android aar

- Run `./gradlew :common:assembleRelease`
- Output: `/common/build/outputs/aar/common-release.aar`

#### JVM jar

- Run `./gradlew :common:jvmJar`
- Output: `/common/build/libs/common-jvm-1.0.jar`

#### iOS Framework

- Run `./gradlew :common:linkReleaseFrameworkIosArm64`
- Output: `/common/build/bin/iosArm64/releaseFramework/common.framework`
