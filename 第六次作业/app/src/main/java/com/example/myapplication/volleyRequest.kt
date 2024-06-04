package com.example.myapplication

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject


fun volleyRequest(context: Context, cardNun: MutableState<String>, cardExp: MutableState<String>){
    val queue = Volley.newRequestQueue(context)
    val url="http://192.168.0.200:7885/credit_cards"
    var response: JSONObject
    val stringRequest = StringRequest(
        Request.Method.GET,url,
        {
            Log.d("Success","simpleRequest:${it}")
            response = JSONObject(it)
            cardNun.value = response.getString("credit_card_number")
            cardExp.value = response.getString("credit_card_expiry_date")
        },{
            cardNun.apply{"0000-0000-0000-0000"}
            cardExp.apply{"9999-12-31"}
            Log.d("Error","simpleRequest:${it}")
        }
    )
    queue.add(stringRequest)
}



