# FlickrExample 

FlickrExample is a photo gallery sample Android application, where you can search and view all the images from the Flickr API.

***Builded with: Android Studio Flamingo | 2022.2.1***

### ⬇️ Try out the app, download the apk from the link below:

[![FlickrExample](https://img.shields.io/badge/FlickrExample-v1.0.0-%6006090E?style=for-the-badge&logo=android)](https://github.com/giacomoparisi/FlickrExample/blob/main/releases/FlickrExample-v1.0.0.apk)

# Table of Contents

1. [Setup](#setup)
2. [Architecture](#architecture)
3. [Libraries](#libraries)
4. [Credits](#credits)

## SETUP : <a name="setup"></a>

In order to be able to **build** this project you need to create a [Flickr API key](https://www.flickr.com/services/).
Then you need to create a string resource called **"flicker_api_key"** in the **data** module.

```
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <string name="flicker_api_key" translatable="false">YOUR FLICKR API KEY</string>
</resources>
```

## ARCHITECTURE : <a name="architecture"></a>

This project is based on the CleanArchitecture

 ```
com.giacomoparisi.flickrexample
├── 📂 app/                          # App module 
|   
│
│
│
├── 📂 core/                         # Contains all possible shared UI and utilities for all 
|                                      presentation modules
│                       
├── 📂 data/                         # Module that interacts with external services like 
│                                      network or database
│                                     
├── 📂 domain/                       # Contains the business logic, also it is responsible 
|                                      for processing the data from the data layer to the 
|                                      presentation layer and viceversa
│                           
├── 📂 entities/                     # Contains all the data classes used by the business logic
│                           
├── 📂 navigation/                   # Contains all the navigation routes of the application
│                           
├── 📂 home/                         # Presentation(UI) module for the home screen  
│   
└── 📂 detail/                       # Presentation(UI) module for the detail screen

```

## LIBRARIES <a name="libraries"></a>

- **View Model**
  ```
  Official Android library for ViewModel, a class for business logic or
  screen level state holder
  
  Benefits:
      - Better integration with the android framework
      - Caches state and persists it through configuration changes
        (no need to fetch data again when navigating between screens)
  ```

- **Constraint Layout**
- **Card View**
- **App Compat**
- **Recycler View**
  ```
  Official Android libraries for UI
  ```

- **RX Java**
  ```
  Java VM implementation of Reactive Extensions
  ```
- **Retrofit**
  ```
  HTTP client for Android
  
  Benefits:
     - Easier networking implementation for Android apps
     - Very fast
     - Reduce error-prone boilerplate code
     - Support for Kotlin Coroutines
     - Use OkHttp for low level network operations
  ```

- **Gson**
  ```
  Gson Json library for response deserialization
  ```

- **Dagger Hilt**
  ```
  Dependency injection library
  
  Benefits:
      - Reduce error-prone boilerplate code
      - Builds and validates dependency graphs, ensuring that every object's dependencies
        can be satisfied and no dependency cycles exist
      - Better dependencies management (dependencies scope, modules, singleton ecc.)
      - Easier unit and integration testing
  ```

- **Glide**
  ```
    Image loading framework for Android
  ```

## Credits <a name="credits"></a>

The API used by this project are provided by **[Flickr](https://www.flickr.com/services/api/)**

## License

```
 Copyright 2023 Giacomo Parisi

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```