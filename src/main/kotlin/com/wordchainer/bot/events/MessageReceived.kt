package com.wordchainer.bot.events

import com.wordchainer.bot.data.Commands
import com.wordchainer.bot.data.ConfigHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.future.await
import kotlinx.coroutines.launch
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

object MessageReceived : ListenerAdapter() {
    private val prefix = ConfigHandler.prefix
    private val commands = Commands.commands

    override fun onMessageReceived(ctx: MessageReceivedEvent) {
        GlobalScope.launch {
            if (ctx.author.isBot) return@launch
            if (ctx.isWebhookMessage) return@launch

            if (ctx.isFromGuild) ctx.guild.retrieveMember(ctx.author).submit().await()

            val content = ctx.message.contentRaw
            if (!content.startsWith(prefix)) return@launch
            if (content == prefix) return@launch

            val inputCommand = content.removePrefix(prefix).toLowerCase()
            val command = commands.find { (it.name + it.aliases).contains(inputCommand) }
                ?: return@launch

            val args = content.drop(1).split(' ').filter { it.isNotEmpty() }
            command.execute(ctx, args)
        }
    }
}