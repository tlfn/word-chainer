package com.wordchainer.bot.commands.general

import com.wordchainer.bot.commands.Command
import net.dv8tion.jda.api.events.message.MessageReceivedEvent

object Ping : Command() {
    init {
        this.name = "ping"
        this.aliases = listOf("pong", "핑", "퐁")
    }

    override suspend fun execute(ctx: MessageReceivedEvent, args: List<String>) {
        ctx.channel.sendMessage("퐁! ${ctx.jda.gatewayPing.displayForm}ms").queue()
    }

    private val Long.displayForm: String
        get() = "%,d".format(this)
}