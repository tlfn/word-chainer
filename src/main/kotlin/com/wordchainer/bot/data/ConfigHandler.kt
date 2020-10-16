package com.wordchainer.bot.data

import com.google.gson.Gson
import com.google.gson.JsonObject
import java.io.File

object ConfigHandler {
    private val String.asJsonObject: JsonObject
        get() = Gson().fromJson(this, JsonObject::class.java)

    var token: String = ""
    var prefix: String = ""

    fun load() {
        val file = File("config.json").readText()
        val json = file.asJsonObject

        token = json["token"].asString
        prefix = json["prefix"].asString
    }
}