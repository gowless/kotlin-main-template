package com.kotlin_base_dev.network.connect

import com.kotlin_base_dev.network.models.getmodels.Example
import retrofit2.Call
import retrofit2.http.*

interface MainInterface {
    //query for offers
    @GET("v3/{id}")
    fun getData(
        @Path("id") appId: String?
    ): Call<Example?>?


    //post for info send
    @POST("stats")
    fun putOfferData(
        @Query("geo") geo: String?,
        @Query("cpa") cpa: String?,
        @Query("start_date") start_date: String?,
        @Query("end_date") end_date: String?
    ): Call<Example?>?

    //main post json data to server
    @POST("offer-click")
    fun putMainOfferData(
        @Query("geo") geo: String?,
        @Query("cpa") cpa: String?,
        @Query("app") app: String?,
        @Query("offer_name") offer_name: String?,
        @Query("offer_id") offer_id: String?,
        @Query("client_id") client_id: String?,
        @Query("advertising_id") advertising_id: String?,
        @Query("click_date") click_date: String?,
        @Query("source") source: String?,
        @Query("chanel") chanel: String?,
        @Query("ad_id") ad_id: String?,
        @Query("campaign") campaign: String?,
        @Query("campaign_id") campaign_id: String?,
        @Query("adset") adset: String?,
        @Query("adset_id") adset_id: String?,
        @Query("adgroup") adgroup: String?,
        @Query("adgroup_id") adgroup_id: String?
    ): Call<Example?>?

    @FormUrlEncoded
    @POST("offer-click")
    fun putMainDataField(
        @Field("geo") geo: String?,
        @Field("cpa") cpa: String?,
        @Field("app") app: String?,
        @Field("offer_name") offer_name: String?,
        @Field("offer_id") offer_id: String?,
        @Field("client_id") client_id: String?,
        @Field("advertising_id") advertising_id: String?,
        @Field("click_date") click_date: String?,
        @Field("source") source: String?,
        @Field("chanel") chanel: String?,
        @Field("campaign") campaign: String?,
        @Field("campaign_id") campaign_id: String?,
        @Field("adset") adset: String?,
        @Field("adset_id") adset_id: String?,
        @Field("adgroup") adgroup: String?,
        @Field("adgroup_id") adgroup_id: String?
    ): Call<Example?>?
}