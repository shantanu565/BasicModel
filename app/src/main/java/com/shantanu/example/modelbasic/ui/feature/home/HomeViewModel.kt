package com.shantanu.example.modelbasic.ui.feature.home


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Response


class HomeViewModel : ViewModel() {
    /*private var filtersResponse = MutableLiveData<FilterResponse>()
    private var searchResponse = MutableLiveData<SearchResponse>()

    var languageFilter = MutableLiveData<String>()
    var genrefilter = MutableLiveData<String>()
    var query = MutableLiveData<String>()


    fun doSearch(queryString: String) {

        var mApiService: ApplicationApis = RestClient.client.create(ApplicationApis::class.java)
        // var searchRequest=SearchRequest(queryString,"all",0,true)
        val call = mApiService.getSearch(queryString, "all", 0, true)
        Log.v("bd", queryString)
        call.enqueue(object : retrofit2.Callback<SearchResponse> {
            override fun onResponse(
                call: Call<SearchResponse>,
                response: Response<SearchResponse>
            ) {
                if (response.isSuccessful) {
                    searchResponse.setValue(response.body());
                    Log.e("list", Gson().toJson(response.body()))
                    Log.v("list", response.toString())

                } else {
                    Log.v("list", "failed")
                }

            }

            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                t.printStackTrace()
                Log.v("data", t.printStackTrace().toString())

            }

        })
    }

    fun getSearchFilters() {
        var mApiService: ApplicationApis = RestClient.client.create(ApplicationApis::class.java)
        val call = mApiService.fetchSearchfilters()
        call.enqueue(object : retrofit2.Callback<FilterResponse> {
            override fun onResponse(
                call: Call<FilterResponse>,
                response: Response<FilterResponse>
            ) {
                if (response.isSuccessful) {
                    //var filterResponse: FilterResponse? =response.body()

                    filtersResponse.setValue(response.body());

                    var languageFilter: MutableList<String>? =
                        filtersResponse?.value?.data?.languageFilters
                    // var genreFilter: MutableList<String>? = filterResponse?.data?.genreFilters
                    var genreFilter: MutableList<String>? =
                        filtersResponse?.value?.data?.genreFilters

                    //  var languageFilter:List<FilterResponse> = listOf(response.body())
                    Log.e("Success", Gson().toJson(response.body()))
                    Log.e("Success", Gson().toJson(languageFilter))
                    Log.e("Success", Gson().toJson(genreFilter))


                } else {
                    Log.e("Success", Gson().toJson(response.body()))
                }
            }


            override fun onFailure(call: Call<FilterResponse>, t: Throwable) {
                t.printStackTrace()
            }

        })

    }

    //Method for searching the query
    fun getSearchResponse(queryString: String): LiveData<SearchResponse> {
        doSearch(queryString)
        return searchResponse
    }

    //Method for getting the filters
    fun getFilterResponse(): LiveData<FilterResponse> {
        getSearchFilters()
        return filtersResponse
    }*/
}