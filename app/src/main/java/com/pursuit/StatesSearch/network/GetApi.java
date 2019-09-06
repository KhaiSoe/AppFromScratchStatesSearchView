package com.pursuit.StatesSearch.network;

import com.pursuit.StatesSearch.data.model.StatesWrapper;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * https://gist.githubusercontent.com/jpriebe/d62a45e29f24e843c974/raw/b1d3066d245e742018bce56e41788ac7afa60e29/us_state_capitals.json
 */

public interface GetApi {
    String API_ENDPOINT = "jpriebe/d62a45e29f24e843c974/raw/b1d3066d245e742018bce56e41788ac7afa60e29/us_state_capitals.json";
    
    @GET(API_ENDPOINT)
    Single<StatesWrapper> getStates();
}

