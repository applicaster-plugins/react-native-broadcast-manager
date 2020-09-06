package com.applicaster.react

import android.content.Intent
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.ReadableMap

class RNBroadcasterManager(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext){

    val TAG = "RNBroadcasterManager"
    val SEND_BROADCAST_ACTION = "send_broadcast_from_rn"
    val EVENT_NAME = "event_name"
    val EVENT_PROPERTIES = "event_properties"

    override fun getName(): String {
        return TAG
    }

    @ReactMethod
    fun sendBroadcastEvent(eventName: String, properties: ReadableMap) {
        val intent = Intent(SEND_BROADCAST_ACTION)
        intent.putExtra(EVENT_NAME, eventName)
        intent.putExtra(EVENT_PROPERTIES, properties.toHashMap().toString())
        LocalBroadcastManager.getInstance(reactApplicationContext).sendBroadcast(intent)
    }
}