plugins {
    alias(libs.plugins.fabric.loom)
}

base {
    archivesName = property("archives_base_name") as String
}
version = "${property("mod_version")}+${libs.versions.minecraft.get()}"
group = property("maven_group") as String

repositories {
    mavenCentral()
    mavenLocal()
    maven("https://maven.ccbluex.net/releases")
    maven("https://maven.ccbluex.net/snapshots")
    maven("https://maven.fabricmc.net/")
}

dependencies {
    minecraft(libs.minecraft)
    implementation(libs.fabric.loader)
    // Keep LiquidBounce's runtime libraries while excluding Fabric API and all its modules.
    implementation(libs.liquidbounce) {
        exclude(group = "net.fabricmc.fabric-api")
    }
    testImplementation("net.fabricmc:fabric-loader-junit:${libs.versions.fabric.loader.get()}")
    testImplementation("org.junit.jupiter:junit-jupiter:6.0.0")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:6.0.0")
}

tasks.processResources {
    val properties = mapOf(
        "version" to project.version.toString(),
        "minecraft_version" to libs.versions.minecraft.get(),
        "loader_version" to libs.versions.fabric.loader.get(),
        "liquidbounce_version" to libs.versions.liquidbounce.get(),
    )
    inputs.properties(properties)
    filesMatching("fabric.mod.json") {
        expand(properties)
    }
}

java {
    withSourcesJar()
    toolchain.languageVersion = JavaLanguageVersion.of(libs.versions.jdk.get())
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
    options.release = libs.versions.jdk.get().toInt()
    // LiquidBounce's Java-facing API may reference classes compiled with preview features.
    options.compilerArgs.add("--enable-preview")
}

tasks.test {
    useJUnitPlatform()
    jvmArgs("--enable-preview")
}

tasks.jar {
    from("LICENSE") {
        rename { "${it}_${base.archivesName.get()}" }
    }
}
