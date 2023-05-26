package com.example.UtilityHelper;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Url;

public class HttpUtility {
    private static final String BASE_URL = "https://api.example.com/";
    private static Retrofit retrofit;

    public  Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    private interface ApiService {
        @GET
        Call<String> sendGetRequest(@Url String url);
    }

    public  void sendGetRequest(String url, retrofit2.Callback<String> callback) {
        ApiService apiService = getRetrofitInstance().create(ApiService.class);
        Call<String> call = apiService.sendGetRequest(url);
        call.enqueue(callback);
    }
}
