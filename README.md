# Marvel Characters
Android app that displays a list of marvel characters and the details of each character

**Endpoint:** /v1/public/characters

# Structure
The app is implemented based on MVVM design pattern.

**Models:** represented as data classes that provide the needed data to be displayed in the views from network.

**ViewModels:** the logic of how to retrieve the data from backend or network in our case is Marvel API.

**Views:** the app UI that contains two activities. Each one is responsible for displaying it's data.

# Dependencies
**Android architecture components:**

 ViewModel

 LiveData
  
**Libraries:**  

Retrofit

Gson

Picasso

# Screenshots
![Main Activity](https://github.com/Delug3/MarvelApp/blob/master/main_marvel_api.png)
![Details Activity](https://github.com/Delug3/MarvelApp/blob/master/detail_marvel_api.png)

