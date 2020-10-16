package com.wordchainer.bot

import com.wordchainer.bot.data.ConfigHandler
import com.wordchainer.bot.events.MessageReceived
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        ConfigHandler.load()

        DefaultShardManagerBuilder
            .createDefault(ConfigHandler.token)
            .addEventListeners(MessageReceived)
            .build()
    }
}