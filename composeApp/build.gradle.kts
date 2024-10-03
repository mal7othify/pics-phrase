import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSetTree

plugins {
  alias(libs.plugins.multiplatform)
  alias(libs.plugins.compose.compiler)
  alias(libs.plugins.compose)
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlinx.serialization)
  alias(libs.plugins.sqlDelight)
  alias(libs.plugins.buildConfig)
  alias(libs.plugins.gradleKtlint)
}

kotlin {
  jvmToolchain(11)
  androidTarget {
    instrumentedTestVariant.sourceSetTree.set(KotlinSourceSetTree.test)
  }

  jvm()

  wasmJs {
    browser()
    binaries.executable()
  }

  listOf(
    iosX64(),
    iosArm64(),
    iosSimulatorArm64()
  ).forEach {
    it.binaries.framework {
      baseName = "ComposeApp"
      isStatic = true
    }
  }

  sourceSets {
    commonMain.dependencies {
      implementation(compose.runtime)
      implementation(compose.foundation)
      implementation(compose.material3)
      implementation(compose.components.resources)
      implementation(compose.components.uiToolingPreview)
      implementation(libs.kermit)
      implementation(libs.kotlinx.coroutines.core)
      implementation(libs.ktor.client.core)
      implementation(libs.ktor.client.content.negotiation)
      implementation(libs.ktor.client.serialization)
      implementation(libs.ktor.client.logging)
      implementation(libs.androidx.lifecycle.viewmodel)
      implementation(libs.androidx.lifecycle.runtime.compose)
      implementation(libs.androidx.navigation.composee)
      implementation(libs.kotlinx.serialization.json)
      implementation(libs.koin.core)
      implementation(libs.koin.compose)
      implementation(libs.coil)
      implementation(libs.coil.network.ktor)
      implementation(libs.multiplatformSettings)
      implementation(libs.kstore)
    }

    commonTest.dependencies {
      implementation(kotlin("test"))
      @OptIn(ExperimentalComposeLibrary::class)
      implementation(compose.uiTest)
      implementation(libs.kotlinx.coroutines.test)
    }

    androidMain.dependencies {
      implementation(compose.uiTooling)
      implementation(libs.androidx.activityCompose)
      implementation(libs.kotlinx.coroutines.android)
      implementation(libs.ktor.client.okhttp)
      implementation(libs.sqlDelight.driver.android)
    }

    jvmMain.dependencies {
      implementation(compose.desktop.currentOs)
      implementation(libs.kotlinx.coroutines.swing)
      implementation(libs.ktor.client.okhttp)
      implementation(libs.sqlDelight.driver.sqlite)
    }

    iosMain.dependencies {
      implementation(libs.ktor.client.darwin)
      implementation(libs.sqlDelight.driver.native)
    }
  }
}

android {
  namespace = "org.company.picsphrase"
  compileSdk = 35

  defaultConfig {
    minSdk = 21
    targetSdk = 35

    applicationId = "org.company.picsphrase.androidApp"
    versionCode = 1
    versionName = "1.0.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }
}

dependencies {
  androidTestImplementation(libs.androidx.uitest.junit4)
  debugImplementation(libs.androidx.uitest.testManifest)
  androidTestImplementation("androidx.test:monitor") {
    version { strictly("1.6.1") }
  }
  add("ktlintRuleset", libs.ktlintComposeRules)
}

compose.desktop {
  application {
    mainClass = "MainKt"

    nativeDistributions {
      targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
      packageName = "PicsPhrase"
      packageVersion = "1.0.0"

      linux {
        iconFile.set(project.file("desktopAppIcons/LinuxIcon.png"))
      }
      windows {
        iconFile.set(project.file("desktopAppIcons/WindowsIcon.ico"))
      }
      macOS {
        iconFile.set(project.file("desktopAppIcons/MacosIcon.icns"))
        bundleID = "org.company.picsphrase.desktopApp"
      }
    }
  }
}

buildConfig {
  // BuildConfig configuration here.
}

sqldelight {
  databases {
    create("MyDatabase") {
      // Database configuration here.
      packageName.set("org.company.picsphrase.db")
    }
  }
}

configure<org.jlleitschuh.gradle.ktlint.KtlintExtension> {
  // Here you can add your ktlint settings
  version.set(libs.versions.ktlint.get())
  android.set(true)
  outputColorName.set("RED")
  enableExperimentalRules.set(true)

  // Filter to exclude specific directories
  filter {
    exclude { element ->
      val path = element.file.path
      path.contains("\\generated\\") ||
        path.contains("/generated/") ||
        path.contains("/jvmMain/") ||
        path.contains("/iosMain/") ||
        path.contains("/wasmJsMain/")
    }
  }
}
