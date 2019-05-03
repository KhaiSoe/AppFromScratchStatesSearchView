package com.pursuit.appfromscratch032319.network;

import com.pursuit.appfromscratch032319.model.StatesWrapper;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * https://gist.githubusercontent.com/jpriebe/d62a45e29f24e843c974/raw/b1d3066d245e742018bce56e41788ac7afa60e29/us_state_capitals.json
 */

public interface GetApi {
    @GET("jpriebe/d62a45e29f24e843c974/raw/b1d3066d245e742018bce56e41788ac7afa60e29/us_state_capitals.json")
    Single<StatesWrapper> getStates();
}
