Marvel App Mobile Test

App that shows a list of Marvel Characters with details of each one.

Endpoint: /v1/public/characters

Functionalities:

Data is obtained when there is internet connection automatically. If the internet connection is gone, it wont retrieve the data.

App show a list of characters. (FULLY IMPLEMENTED) RecyclerView added here to show the data, format: linearLayout

Selection of character and details. (FULLY IMPLEMENTED) Implemented a second activity, when user click on an item a method will get the character id and send it to a new activity

Usability: Implemented recyclerview over listview and constraint layout over relative, last version of android too.

Libraries: retrofit2, gson and Picasso. Will replace gson with moshi probably

Architecture: Implemented with MVP at first, but replaced with MVVM
