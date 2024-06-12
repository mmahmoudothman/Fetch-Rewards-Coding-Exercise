# MyAppInitial2

MyAppInitial2 is an Android application built using Jetpack Compose, Dagger Hilt for dependency injection, Retrofit for network communication, and Kotlin Coroutines with Flow for asynchronous operations.

## Table of Contents

- [Features](#features)
- [Architecture](#architecture)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Features

- Display a list of items fetched from a remote API
- Group and sort items based on their attributes
- Handle loading and error states
- Modern UI using Jetpack Compose
- Dependency injection with Dagger Hilt
- Network operations with Retrofit and OkHttp
- State management with Kotlin Coroutines and Flow

## Architecture

The project follows a modular and clean architecture:

- **app**: Application class setup for Hilt
- **data**: Handles data operations, including network requests and repository implementations
  - **remote**: API service definitions
  - **repository**: Repository implementations
  - **response**: Data models
- **di**: Dependency injection modules
- **domain**: Domain layer interfaces
- **nav**: Navigation setup with Jetpack Compose
- **presentation**: UI layer, including ViewModels and Composables
  - **main**: Main screen and ViewModel
  - **splash**: Splash screen
- **ui**: Theme definitions

## Installation

To get a local copy of the project, follow these steps:

1. Clone the repository:
    ```sh
    git clone https://github.com/mmahmoudothman/Fetch-Rewards-Coding-Exercise.git
    ```
2. Open the project in Android Studio.

3. Sync the project with Gradle files.

## Usage

### Prerequisites

- Android Studio Bumblebee or higher
- Kotlin 1.5 or higher

### Running the App

1. Open the project in Android Studio.
2. Connect an Android device or start an emulator.
3. Click on the `Run` button or press `Shift + F10`.

### Structure and Code Overview

- `MyApp`: The application class where Hilt is initialized.
- `ApiService`: Interface defining the API endpoints.
- `RepositoryImp`: Implementation of the repository pattern fetching data from the API.
- `ItemsViewModel`: ViewModel handling the data fetching and state management for the item list screen.
- `ItemListScreen`: Composable function displaying a list of items.
- `SplashScreen`: Composable function displaying the splash screen.
- `AppNavHost`: Navigation host managing the navigation within the app.
- `MainActivity`: Main activity setting up the content and navigation for the app.

### Code Examples

#### ViewModel Example
```kotlin
@HiltViewModel
class ItemsViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    private val _items = MutableStateFlow<List<Item>>(emptyList())
    val items: StateFlow<List<Item>> = _items.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    init {
        fetchItems()
    }

    private fun fetchItems() {
        viewModelScope.launch {
            _isLoading.value = true
            repository.getItems().collect { result ->
                _items.value = result
                _isLoading.value = false
            }
        }
    }
}
