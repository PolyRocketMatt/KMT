# Kotlin Mathematical Toolkit (KMT)

[![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg?style=for-the-badge)](https://ktlint.github.io/)
[![Version](https://img.shields.io/badge/dynamic/json?color=blue&label=version&query=version&url=https%3A%2F%2Fraw.githubusercontent.com%2FPolyRocketMatt%2FKMT%2Fmain%2Fversion.json&style=for-the-badge)]()
[![Build Status](https://img.shields.io/badge/build-passing-brightgreen?style=for-the-badge)]()
[![State](https://img.shields.io/badge/state-alpha-red?style=for-the-badge)]()

KMT is a library for various mathematical operations in Kotlin. Its original purpose was to provide
a modular library for mathematical tasks. Since Kotlin is emerging more and more as a language for 
scientific computing, this library is intended to provide a solid foundation for such applications.

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

## License

KMT is licensed under the GNU General Public License (version 3). The relevant information for this license can be found [here](https://www.gnu.org/licenses/gpl-3.0.html).