# 🍽️ EasyFood App

EasyFood is an Android application that helps users discover meals, explore popular recipes, and save their favorite meals for quick access.  
The app follows modern Android development best practices and uses clean architecture.

---

## 📱 Features

- 🔀 Get a random meal suggestion
- 🔥 View popular meals
- 📂 Browse meals by category
- ❤️ Save meals to favorites (Room Database)
- ▶️ Watch recipe videos on YouTube
- 📶 Real-time API data using Retrofit
- 🧭 Bottom Navigation with Navigation Component

---

## 🛠️ Tech Stack

- **Language:** Kotlin
- **Architecture:** MVVM (Model–View–ViewModel)
- **UI:** XML, RecyclerView, ViewBinding
- **Networking:** Retrofit
- **Image Loading:** Glide
- **Local Database:** Room
- **Async:** Coroutines
- **Lifecycle:** LiveData, ViewModel
- **Navigation:** Navigation Component

---

## 🧱 Architecture Overview

The app uses **MVVM architecture**:

- **View (Activities & Fragments):**  
  Displays UI and observes LiveData

- **ViewModel:**  
  Handles business logic and exposes LiveData

- **Repository:**  
  Manages data from API and Room database

- **Room Database:**  
  Stores favorite meals locally

---

## 🚀 Getting Started

### Prerequisites
- Android Studio (latest version)
- Android SDK
- Internet connection (for API calls)

### Steps to Run
1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/EasyFoodApp.git

   📦 API Used

TheMealDB API
Provides meal recipes, images, and categories.

👩‍💻 Author

Arti Vaidya
Android Developer | QA Background

This project is for learning and demonstration purposes.
