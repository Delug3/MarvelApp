# Marvel Characters
Android app that displays a list of marvel characters and the details of each character

*Api Website:* https://developer.marvel.com/documentation/apiresults 

*Endpoint: /v1/public/characters*

# Instructions
In order to make this app to work, simply add your own public and private keys to the Constants file values:

 *const val PUBLIC_KEY = "Add here your own public key"*
 
 *const val PRIVATE_KEY = "Add here your own private key"*
 
 For security purposes isn't recommendable to publish your own keys in a public repository.

# Structure
The app is implemented based on MVVM design pattern.

**Models:** represented as data classes that provide the needed data to be displayed in the views from network.

**ViewModels:** the logic of how to retrieve the data from backend or network in our case is Marvel API.

**Views:** the app UI that contains two activities. Each one is responsible for displaying it's data.

**Repository:** here we store the api endpoints with the desired parameters so we can obtain data from the internet.

**Adapter:** required class to load data in a recyclerView.

**Network:** the file here returns a retrofit instance that we need to build up our coroutines calls.

**Utilites:** a handy package where we store values or even method that we can use across the entire up in order to reduce boilerplate code.


# Dependencies
**Android architecture components:**

 ViewModel

 LiveData
  
**Libraries:**  

Retrofit

Gson

Picasso

Swipe Refresh

# Screenshots
<img src="https://github.com/Delug3/MarvelApp/blob/master/main_activity_list.png" width="200"> <img src="https://github.com/Delug3/MarvelApp/blob/master/detail_activity.png" width="200">


