package com.kotlin_base_dev.uiactivities

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.telephony.TelephonyManager
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.kotlin_base_dev.BuildConfig
import com.kotlin_base_dev.MainActivity
import com.kotlin_base_dev.R
import com.kotlin_base_dev.network.connect.Common
import com.kotlin_base_dev.network.connect.MainInterface
import com.kotlin_base_dev.network.models.getmodels.Example
import com.kotlin_base_dev.network.models.getmodels.Liste
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Splash : AppCompatActivity() {

     val APP_ID = "e0f0c81b-2bf4-482e-8862-80474131223b"
    lateinit var mService: MainInterface
    companion object {
         //tabs strings
         lateinit var first:String
         lateinit var second:String
         lateinit var third:String
        lateinit var ad_id:String
        lateinit var carrier:String
        //number of tabs
         var numberOfTabs:Int = 0;

        //is empty field
        var isEmpty: Boolean = false

        //main data lists
        var listDataAll: List<Liste>? = null
        var listDataBad: List<Liste>? = null
        var listDataZero: List<Liste>? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //get ad_id
        getID()

        //getting carrier ISO name
        getCarrier()

        //checking carrier to match current country code SIM
        if (carrier == "ua") {
            getJsonData()
        } else {
            getJsonDataCloak()
        }


    }

    //setting to get json file and parse it to models in main case
    fun getJsonData() {
        mService = Common.retrofitService
        val call: Call<Example?>? = mService.getData(APP_ID)
        call?.enqueue(object : Callback<Example?> {
            override fun onResponse(
                call: Call<Example?>,
                response: Response<Example?>
            ) {
                if (BuildConfig.DEBUG && response.body() == null) {
                    error("Assertion failed")
                }
                listDataAll = response.body()?.list
                isEmpty = response.body()?.categories!!.isEmpty()
                numberOfTabs = response.body()!!.categories.size
                listDataBad = response.body()!!.list
                listDataZero = response.body()!!.list
                when (numberOfTabs) {
                    0 -> {
                    }
                    1 -> first = response.body()!!.categories.get(0).label

                    2 -> {
                        first = response.body()!!.categories.get(0).label
                        second = response.body()!!.categories.get(1).label
                    }
                    3 -> {
                        first = response.body()!!.categories.get(0).label
                        second = response.body()!!.categories.get(1).label
                        third = response.body()!!.categories.get(2).label
                    }
                }
                //open MainActivity
                getBeforeMain()
            }

            override fun onFailure(call: Call<Example?>, t: Throwable) {
                //starting MainActivity when failed to connect
                startActivity(Intent(this@Splash, MainActivity::class.java))
            }
        })
    }

    //setting to get json file and parse it to models in main case
    fun getJsonDataCloak() {
        mService = Common.retrofitService
        val call: Call<Example?>? = mService.getData(APP_ID)
        call?.enqueue(object : Callback<Example?> {
            override fun onResponse(
                call: Call<Example?>,
                response: Response<Example?>
            ) {
                if (BuildConfig.DEBUG && response.body() == null) {
                    error("Assertion failed")
                }
                listDataAll = response.body()?.list
                isEmpty = response.body()?.categories!!.isEmpty()
                numberOfTabs = response.body()!!.categories.size
                listDataBad = response.body()!!.list
                listDataZero = response.body()!!.list
                when (numberOfTabs) {
                    0 -> {
                    }
                    1 -> first = response.body()!!.categories.get(0).label

                    2 -> {
                        first = response.body()!!.categories.get(0).label
                        second = response.body()!!.categories.get(1).label
                    }
                    3 -> {
                        first = response.body()!!.categories.get(0).label
                        second = response.body()!!.categories.get(1).label
                        third = response.body()!!.categories.get(2).label
                    }
                }
                //open MainActivity
                getBeforeMain()
            }

            override fun onFailure(call: Call<Example?>, t: Throwable) {
                //starting MainActivity when failed to connect
                startActivity(Intent(this@Splash, MainActivity::class.java))
            }
        })
    }

    //starting CloakActivity
    fun getCloak() {
        startActivity(Intent(this@Splash, Cloak::class.java))
    }

    //get carrier name
    fun getCarrier() {
        val manager =
            getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        carrier = manager.simCountryIso
    }

    //starting BeforeMain activity
    fun getBeforeMain() {
        startActivity(Intent(this@Splash, BeforeMain::class.java))
    }

    //getting advertising ID
    fun getID() {
        AsyncTask.execute {
            try {
                val adInfo =
                    AdvertisingIdClient.getAdvertisingIdInfo(this@Splash)
                ad_id = adInfo?.id.toString()
            } catch (e: Exception) {
            }
        }
    }

}