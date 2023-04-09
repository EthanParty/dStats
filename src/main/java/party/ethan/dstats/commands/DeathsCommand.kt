package party.ethan.dstats.commands

import net.dv8tion.jda.api.EmbedBuilder
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter
import org.bukkit.Bukkit
import org.bukkit.Statistic
import java.util.regex.Pattern

class DeathsCommand : ListenerAdapter() {
    private fun validate(text: String): Boolean {
        val pattern = Pattern.compile("^[a-zA-Z0-9_]*$")
        val m = pattern.matcher(text)
        val length = text.length in 1..16
        return m.find() && length
    }

    override fun onSlashCommandInteraction(event: SlashCommandInteractionEvent) {
        if (event.name.equals("deaths", ignoreCase = true)) {
            if (validate(event.getOption("player")!!.asString)) {
                val player = Bukkit.getOfflinePlayer(event.getOption("player")!!.asString)
                val embedBuilder = EmbedBuilder().setDescription("**Deaths:** " + player.getStatistic(Statistic.DEATHS))
                event.reply("").setEmbeds(embedBuilder.build()).queue()
            } else {
                event.reply("Invalid Username.").setEphemeral(true).queue()
            }
        }
    }
}
