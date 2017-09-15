package com.creanal.lagosjavadevelopers.api;

import com.creanal.lagosjavadevelopers.model.itemResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by McCharley Okafor on 9/13/2017.
 */

public interface Service {
    @GET("/search/users?q=language:java+location:lagos")
    Call<itemResponse> getItems();
}
