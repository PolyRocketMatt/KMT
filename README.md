# Kotlin Mathematical Toolkit (KMT)

[![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg?style=for-the-badge)](https://ktlint.github.io/)
![State](https://img.shields.io/badge/State-ALPHA-red?style=for-the-badge)
![Build Status](https://img.shields.io/badge/Build-Passing-%2368AD63?style=for-the-badge)
![License](https://img.shields.io/badge/License-GPLv3-%2368AD63?style=for-the-badge)
![Kotlin](https://img.shields.io/badge/Kotlin-1.7.20-%233e7fa8?logo=kotlin&style=for-the-badge)
[![Version](https://img.shields.io/badge/dynamic/json?color=3e7fa8&label=version&query=version&url=https%3A%2F%2Fraw.githubusercontent.com%2FPolyRocketMatt%2FKMT%2Fmain%2Fversion.json&style=for-the-badge)](https://github.com/PolyRocketMatt/KMT/releases)

KMT is a library for various mathematical operations in Kotlin. Its original purpose was to provide
a modular library for mathematical tasks. Since Kotlin is emerging more and more as a language for 
scientific computing, this library is intended to provide a solid foundation for such applications.

KMT is currently in very early alpha stages. Implementations, documentations, testing may change in the future. Please 
keep track of the changelog to see what has changed in the latest versions.

## Goals

The primary goal of KMT is to provide a modular library for mathematical operations in Kotlin. Also, KMT aims
to provide users with a library that is easy to understand and use. 

## Modules

KMT is split into several modules, each of which can be used independently. Currently, the following modules
are available:

* [`kmt-common`](https://github.com/PolyRocketMatt/KMT/wiki/KMT-Common) - Common mathematical operations
* [`kmt-interval`](https://github.com/PolyRocketMatt/KMT/wiki/KMT-Interval) - Interval classes and operations
* [`kmt-vector`](https://github.com/PolyRocketMatt/KMT/wiki/KMT-Vector) - Vector classes and operations
* [`kmt-matrix`](https://github.com/PolyRocketMatt/KMT/wiki/KMT-Matrix) - Matrix classes and operations
* [`kmt-trigonometry`](https://github.com/PolyRocketMatt/KMT/wiki/KMT-Trigonometry) - Tabular trigonometric functions for faster computation
* [`kmt-function`](https://github.com/PolyRocketMatt/KMT/wiki/KMT-Function) - Functions and their relative operations (algebraic operations, integration, differentiation)

## Documentation

Documentation for KMT can be found [here](http://polyrocketmatt.me/KMT/). Documentation is generated
using [Dokka](https://github.com/Kotlin/dokka) and is updated automatically upon each release.

## Usage

- ```MODULE``` is the module you want to use.
- ```VERSION``` is the version of the module you want to use. The latest version is 0.0.9-SNAPSHOT
- ```USERNAME``` is your GitHub username
- ```TOKEN``` is a GitHub personal access token with the `read:packages` scope

The available modules are listed [here](#modules)

### Gradle

To use KMT in a Gradle project, add the following to your `build.gradle`:

```groovy
repositories {
    // Repository
    maven {
        name = "GitHubPackages"
        url = "https://maven.pkg.github.com/PolyRocketMatt/KMT"
        credentials {
            username = USERNAME
            password = TOKEN
        }
    }
}

dependencies {
    // Dependency
    implementation 'com.github.polyrocketmatt:kmt:VERSION'
}
```

### Maven 

To use KMT in a Maven project, add the following to your `pom.xml`:

```xml
<repositories>
    <!-- Repository -->
    <repository>
        <id>ID</id>
        <url>https://maven.pkg.github.com/PolyRocketMatt/KMT</url>
    </repository>
</repositories>

<dependencies>
    <!-- Dependency -->
    <dependency>
        <groupId>com.github.polyrocketmatt</groupId>
        <artifactId>kmt</artifactId>
        <version>VERSION</version>
    </dependency>
</dependencies>
        
<!-- Distribution -->
<distributionManagement>
   <repository>
     <id>github</id>
     <name>GitHub PolyRocketMatt Apache Maven Packages</name>
     <url>https://maven.pkg.github.com/PolyRocketMatt/KMT</url>
   </repository>
</distributionManagement>

<!-- Necessary to access public packages hosted on GitHub -->
<servers>
    <server>
        <id>github</id>
        <username>USERNAME</username>
        <password>TOKEN</password>
    </server>
</servers>
```

### Java

KMT can be used within Java as well, since Kotlin is interoperable with Java. You do however need to provide the kotlin
standard library in order for KMT to work.

## License

KMT is licensed under the GNU General Public License (version 3). The relevant information for this license can be found [here](https://www.gnu.org/licenses/gpl-3.0.html).