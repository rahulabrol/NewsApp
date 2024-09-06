# NewsApp

Welcome to NewsApp, a news application demonstrates modern Android development with Jetpack Compose, Hilt, Coroutines, Flow, Jetpack (Room, ViewModel) based on MVVM architecture.

## How to Run the Project

- Clone the Repository:
```
git clone https://github.com/rahulabrol/NewsApp.git
cd NewsApp
```
- Visit newsapi.org and sign up for an API key, Copy the API key provided
- Open the build.gradle.kts file in the app module. Find the following line
```
buildConfigField("String", "API_KEY", "\"<Add your API Key>\"")
```
- Replace "Add your API Key" with the API key you obtained
- Build and run the NewsApp.

## Major Highlights

- **Jetpack Compose** for modern UI
- **MVVM architecture** for a clean and scalable codebase
- **Kotlin** and **Kotlin DSL**
- **Dagger Hilt** for efficient dependency injection.
- **Retrofit** for seamless networking
- **Coroutines** and **Flow** for asynchronous programming
- **StateFlow** for streamlined state management
- **Pagination** to efficiently load and display news articles (In-Progress)
- **Unit tests** and **UI tests** for robust code coverage
- **Instant search** for quick access to relevant news
- **Navigation 2.8.0** for smooth transitions between screens
- **CustomTabLauncher** for a seamless reading experience
- **Coil** for efficient image loading

## Dependency Use

- Jetpack Compose for UI: Modern UI toolkit for building native Android UIs
- Coil for Image Loading: Efficiently loads and caches images
- Retrofit for Networking: A type-safe HTTP client for smooth network requests
- Dagger Hilt for Dependency Injection: Simplifies dependency injection
- Room for Database: A SQLite object mapping library for local data storage (In-Progress)
- Paging Compose for Pagination: Simplifies the implementation of paginated lists (In-Progress)
- Mockito, JUnit, Turbine for Testing: Ensures the reliability of the application


## If this project helps you, show love ❤️ by putting a ⭐ on this project ✌️

## Contribute to the project

Feel free to improve or add features to the project.
Create an issue or find the pending issue. All pull requests are welcome 😄