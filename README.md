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
  - Retrifit 2
    - For the coinpaprika and the cryptopanic APIs
    - Complex quieries (GET, POST, DELETE)
  - Moshi
- Glide
- LiveData
  - Livedata Observers
  - Kotlin Flow
- Kotlin coroutines (for synchronous excecutions)
- RecyclerView

---

## **There are 3 main screens**

- Cat pictures, cat gifs, and favorites
- Each screen displays a list of these media files using Glide
- The favorite items are stored locally and can be displayed without internet connection
