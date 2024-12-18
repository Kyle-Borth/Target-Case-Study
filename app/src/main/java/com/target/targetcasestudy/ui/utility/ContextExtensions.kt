package com.target.targetcasestudy.ui.utility

import android.content.Context
import com.target.targetcasestudy.api.DealsNetworkService
import kotlinx.serialization.serializer
import kotlin.reflect.typeOf

inline fun <reified T> Context.getMockJson(path: String): T {
    val json = DealsNetworkService.jsonSerializer
    val jsonString = resources.assets.open(path).bufferedReader().use { it.readText() }

    return json.decodeFromString(deserializer = json.serializersModule.serializer(typeOf<T>()), string = jsonString) as T
}