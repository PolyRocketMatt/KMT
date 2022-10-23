# Kotlin Mathematical Toolkit (KMT)

[![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg?style=for-the-badge)](https://ktlint.github.io/)
[![Version](https://img.shields.io/badge/dynamic/json?color=blue&label=version&query=version&url=https%3A%2F%2Fraw.githubusercontent.com%2FPolyRocketMatt%2FKMT%2Fmain%2Fversion.json&style=for-the-badge)](https://github.com/PolyRocketMatt/KMT/releases)
[![Build Status](https://img.shields.io/badge/build-passing-brightgreen?style=for-the-badge)]()
[![State](https://img.shields.io/badge/State-ALPHA-red?style=for-the-badge)]()

KMT is a library for various mathematical operations in Kotlin. Its original purpose was to provide
a modular library for mathematical tasks. Since Kotlin is emerging more and more as a language for 
scientific computing, this library is intended to provide a solid foundation for such applications.

KMT is currently in very early alpha stages. Implementations, documentations, testing may change in the future. Please 
keep track of the changelog to see what has changed in the latest versions.

## Modules

KMT is split into several modules, each of which can be used independently. Currently, the following modules
are available:

* [`common`](https://github.com/PolyRocketMatt/KMT/wiki/KMT-Common) - Common mathematical operations
* [`interval`](https://github.com/PolyRocketMatt/KMT/wiki/KMT-Interval) - Interval classes and operations
* [`vector`](https://github.com/PolyRocketMatt/KMT/wiki/KMT-Vector) - Vector classes and operations
* [`trigonometry`](https://github.com/PolyRocketMatt/KMT/wiki/KMT-Trigonometry) - Tabular trigonometric functions for faster computation
* [`function`](https://github.com/PolyRocketMatt/KMT/wiki/KMT-Function) - Functions and their relative operations (algebraic operations, integration, differentiation)

## Documentation

Documentation for KMT can be found [here](http://polyrocketmatt.me/KMT/). Documentation is generated
using [Dokka](https://github.com/Kotlin/dokka) and is updated automatically upon each release.

## Usage

- ```VERSION``` is the version of the module you want to use. The latest version is 0.0.7-SNAPSHOT
- ```USERNAME``` is your GitHub username
- ```TOKEN``` is a GitHub personal access token with the `read:packages` scope

Currently only the complete library is offered. In the future, individual modules will be available.

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