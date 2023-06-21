# NSSF Take Home Assessment - MADS04 #

The purpose of this assessment is to build a weather application that displays weather information of the current device location and other places.

## Screenshots ##
![rainy](https://github.com/OlukaDenis/weatheapp-MADS04/assets/37341054/0a7120c3-6d08-4283-a48f-6ab6c1d45238)
![cloudy](https://github.com/OlukaDenis/weatheapp-MADS04/assets/37341054/c365e14c-7d1c-466b-a500-cc741a968a59)



## Features
- View current device location weather information
- Search a place and view weather information

## Architecture ##

The architecture of the application is based, apply and strictly complies with each of the following:

[Android architecture components](https://developer.android.com/guide/navigation/navigation-getting-started) - Part of Android Jetpack for give to project a robust design, testable and maintainable.

[Model-View-ViewModel](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel) - Pattern (MVVM) facilitates a separation of concerns between user interface and domain logic.

[S.O.L.I.D](https://en.wikipedia.org/wiki/SOLID) - Design principles intended to make software designs more understandable, flexible and maintainable.

## Tech-stack ##

Minimum API level is set to 21, so the presented approach is suitable for over 94% of devices running Android. This project takes advantage of many popular libraries and tools of the Android ecosystem. Most of the libraries are in the stable version unless there is a good reason to use non-stable dependency.
-   [Jetpack](https://developer.android.com/jetpack):
    -   [Android KTX](https://developer.android.com/kotlin/ktx.html)  - provide concise, idiomatic Kotlin to Jetpack and Android platform APIs.
    -   [AndroidX](https://developer.android.com/jetpack/androidx)  - major improvement to the original Android  [Support Library](https://developer.android.com/topic/libraries/support-library/index), which is no longer maintained.
    -   [View Binding](https://developer.android.com/topic/libraries/view-binding)  - allows you to more easily write code that interacts with views/
    -   [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle)  - perform actions in response to a change in the lifecycle status of another component, such as activities and fragments.
    -   [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)  - lifecycle-aware, meaning it respects the lifecycle of other app components, such as activities, fragments, or services.
    -   [Navigation](https://developer.android.com/guide/navigation/)  - helps you implement navigation, from simple button clicks to more complex patterns, such as app bars and the navigation drawer.
    -   [Room](https://developer.android.com/topic/libraries/architecture/room)  - persistence library provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.
    -   [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)  - designed to store and manage UI-related data in a lifecycle conscious way. The ViewModel class allows data to survive configuration changes such as screen rotations.
-   [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)  - managing background threads with simplified code and reducing needs for callbacks.
-    [Coroutines Flow](https://kotlinlang.org/docs/reference/coroutines-overview.html)  - cold asynchronous data stream that sequentially emits values and completes normally or with an exception
-   [Dagger Hilt](https://dagger.dev/hilt/)  - dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in your project


## Build Instructions ##

1. Make sure you've installed [Android Studio](https://developer.android.com/studio/index.html).
2. In Android Studio, open the project from the local repository. This will auto-generate `local.properties` with the SDK location.
3. Recommended: The project uses JDK11 to build the app and run the tests. Some tests won't pass on the JDK embedded in Android Studio (JDK8). You might want to set JAVA_HOME and JDK location in Android Studio to JDK11.
4. Go to Tools → AVD Manager and create an emulated device or connect a physical device via ADB.
2. Run.

**Notes:**

* While loading/building the app in Android Studio ignore the prompt to update the gradle plugin version as that will probably introduce build errors. On the other hand, feel free to update if you are planning to work on ensuring the compatibility of the newer version.

## Run Tests ##

The project has both unit and instrumental tests. To test the project from the command line:

    $ ./gradlew test                  # assemble, install and run unit tests
    $ ./gradlew connectedAndroidTest  # assemble, install and run Instrumented tests

**Notes:**

* Connect a physical device or run emulator before running the instrumented tests
* You can as well run tests in android studio
## Author ##

<img src="https://avatars.githubusercontent.com/u/37341054?v=4" width="60" align="left" />

**Denis Oluka**

[![Linkedin](https://img.shields.io/badge/-linkedin-grey?logo=linkedin)](https://www.linkedin.com/in/denis-oluka/) [![Twitter](https://shields.io/badge/-twitter-grey?logo=twitter)](https://twitter.com/dennycodev)

## License ##

    MIT License

    Copyright (c) 2023 Oluka Denis

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
