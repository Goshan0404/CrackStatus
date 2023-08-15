package com.example.crackstatus

import android.util.Log
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import org.json.JSONObject

class WebsocketListener : WebSocketListener() {
    var callBack = fun (text: String) {
    }

    override fun onOpen(webSocket: WebSocket, response: Response) {
        super.onOpen(webSocket, response)
        val json = JSONObject()
        json.put("type", "disabled")
        json.put("name", "Георгий")
        webSocket.send(json.toString())
    }

    override fun onMessage(webSocket: WebSocket, text: String) {

        callBack(text)
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        webSocket.close(1000, null)
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        super.onFailure(webSocket, t, response)
        Log.d("URL", "onFailure: ${t.message}")
    }
}