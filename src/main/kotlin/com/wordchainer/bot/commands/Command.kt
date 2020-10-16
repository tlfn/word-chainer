package com.wordchainer.bot.commands

import net.dv8tion.jda.api.events.message.MessageReceivedEvent

abstract class Command {
    var name: String = ""
    var aliases: List<String> = listOf()
    var guildOnly: Boolean = false

    abstract suspend fun execute(ctx: MessageReceivedEvent, args: List<String>)
}