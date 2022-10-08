# rate-cats

Android App to rate pictures and gifs of cats.
The purpose of this app is mainly to showcase how to receive and send different request queries to an API using retrofit.

---

### Tools and skills used:

- MVVM architecture
  - Shared ViewModel
- Material Design
- Jetpack Navigation Component
- Bottom navigation
- SQLite Room storage
- REST APIs
  - Retrofit 2
    - Complex queries (GET, POST, DELETE)
  - Moshi
- Glide
- LiveData
  - Livedata Observers
  - Kotlin Flow
- Kotlin coroutines (for synchronous executions)
- RecyclerView

---

## **There are 3 main screens**

<p align="left" style="display:flex">
  <img align="center" width=132 src="https://user-images.githubusercontent.com/79296181/184086619-934cbc74-ff47-40c9-bbfd-f6415fd36eb0.gif" />
  <img align="center" width=132 src="https://user-images.githubusercontent.com/79296181/184087785-421596f7-b1d1-4e41-9699-1d3b2b0d4f8d.gif" />
</p>

- Cat pictures, cat gifs, and favorites
- Each screen displays a list of these media files using Glide
- The favorite items are stored locally and can be displayed without internet connection
