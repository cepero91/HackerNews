<br />
<p align="center">
  <h3 align="center">Hacker News</h3>

  <p align="center">
    Clean Architecture Android app with an offline-first approach
  </p>
</p>
<p align="center">
  <a href="https://github.com/cepero91/HackerNews/suites/1724168539/artifacts/32623510">
    Download
  </a>
</p>

### Problem to solve

1. Get data from https://hn.algolia.com/api/v1/search_by_date?query=android and cache it for loading when device is offline
2. Ability to delete an article and prevent it from being reinserted when the data has been refreshed (Swipe to Delete)
3. Pull to Refresh
4. View the article on a webview (within the app)

### Third Party Libraries and Jetpack Component used to solve the problems

1. ViewModel & LiveData
2. Coroutine Flow
3. Paging3 (RemoteMediator for offline-first approach)
4. Koin (Dependency Injection)
5. Room
6. Retrofit
7. Navigation Component
8. LeakCanary
9. Material Design, Lottie and Toasty (UI)

### Screenshot
<table>
  <tr>
    <td><img src="https://github.com/cepero91/HackerNews/blob/master/screenshots/hacker_new_splash.png" alt="splash" width="200"></td>
    <td><img src="https://github.com/cepero91/HackerNews/blob/master/screenshots/hit_list.png" alt="splash" width="200"></td>
    <td><img src="https://github.com/cepero91/HackerNews/blob/master/screenshots/hit_detail.png" alt="splash" width="200"></td>
    <td><img src="https://github.com/cepero91/HackerNews/blob/master/screenshots/remove_hit.png" alt="splash" width="200"></td>
    <td><img src="https://github.com/cepero91/HackerNews/blob/master/screenshots/no_internet_error.png" alt="splash" width="200"></td>
  </tr>
</table>
