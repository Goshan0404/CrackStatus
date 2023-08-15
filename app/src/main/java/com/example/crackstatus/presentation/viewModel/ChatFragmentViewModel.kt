package com.example.crackstatus.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.crackstatus.WebsocketListener
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import org.json.JSONObject

class ChatFragmentViewModel(apiKey: String): ViewModel() {
    var messageDisabled = MutableLiveData<String>()
    var messageWorker = MutableLiveData<String>()
    lateinit var webSocket: WebSocket

    init {
        val client = OkHttpClient()
        val request: Request = Request.Builder()
            .url("ws://100.73.206.28/room/$apiKey")
            .build()
        val listener = WebsocketListener()
        webSocket = client.newWebSocket(request, listener)
        listener.callBack = fun(text: String) {
            val json = JSONObject(text)
            val type = json.get("type")

            if (type == "webcam") {
                messageDisabled.postValue(json.get("message").toString())
            } else if (type  == "worker") {
                messageWorker.postValue(json.get("message").toString())
            }
        }
    }

    fun sendMessage(message: String) {
        val jsonObject = JSONObject()
        jsonObject.put("type", "disabled")
        jsonObject.put("message", message)
        jsonObject.put("id", 0)
        webSocket.send(jsonObject.toString())
    }

}