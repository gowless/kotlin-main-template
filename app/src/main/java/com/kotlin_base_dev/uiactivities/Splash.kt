package com.kotlin_base_dev.uiactivities

import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication5.HerokuEndpoints
import com.example.myapplication5.ServiceBuilder
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.kotlin_base_dev.R
import com.kotlin_base_dev.network.models.getmodels.Data
import com.kotlin_base_dev.network.models.getmodels.Listoffers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Splash : AppCompatActivity() {

    companion object {
        //tabs strings
        lateinit var first: String
        lateinit var second: String
        lateinit var third: String
        lateinit var ad_id: String
        lateinit var carrier: String

        //number of tabs
        var numberOfTabs: Int = 0;

        //is empty field
        var isEmpty: Boolean = false

        //main data lists
        lateinit var listDataAll: List<Listoffers>
        lateinit var listDataBad: List<Listoffers>
        lateinit var listDataZero: List<Listoffers>
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
        val request = ServiceBuilder.buildService(HerokuEndpoints::class.java)
        val call = request.getData("ru", "com.mgnovenniycredit")

        call.enqueue(object : Callback<Data> {
            override fun onResponse(call: Call<Data>, response: Response<Data>) {
                if (response.isSuccessful) {

                    Log.d("TASG", response.body()?.categories?.get(1).toString())
                    listDataAll = response.body()!!.listoffers
                    isEmpty = false
                    numberOfTabs = response.body()!!.categories.size
                    listDataBad = response.body()!!.listoffers
                    listDataZero = response.body()!!.listoffers
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
            }

            override fun onFailure(call: Call<Data>, t: Throwable) {
                Toast.makeText(this@Splash, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    //setting to get json file and parse it to models in main case
    fun getJsonDataCloak() {
        val request = ServiceBuilder.buildService(HerokuEndpoints::class.java)
        val call = request.getData("ru", "com.mgnovenniycredit")

        call.enqueue(object : Callback<Data> {
            override fun onResponse(call: Call<Data>, response: Response<Data>) {
                if (response.isSuccessful) {

                    Log.d("TASG", response.body()?.categories?.get(1).toString())
                    listDataAll = response.body()!!.listoffers
                    isEmpty = false
                    numberOfTabs = response.body()!!.categories.size
                    listDataBad = response.body()!!.listoffers
                    listDataZero = response.body()!!.listoffers
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
                    getCloak()
                }
            }

            override fun onFailure(call: Call<Data>, t: Throwable) {
                Toast.makeText(this@Splash, "${t.message}", Toast.LENGTH_SHORT).show()
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

