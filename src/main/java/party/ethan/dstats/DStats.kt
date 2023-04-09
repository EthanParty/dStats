package party.ethan.dstats

import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.interactions.commands.OptionType
import net.dv8tion.jda.api.requests.GatewayIntent
import org.bukkit.plugin.java.JavaPlugin
import party.ethan.dstats.commands.*

class DStats : JavaPlugin() {
    override fun onEnable() {
        saveDefaultConfig()
        val token = config.getString("token")!!
        val bot = JDABuilder.createDefault(token)
                .setActivity(Activity.playing("Minecraft"))
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .addEventListeners(KillsCommand(), DeathsCommand())
                .build()
        bot.upsertCommand("kills", "See how many kills a player has.")
                .addOption(OptionType.STRING, "player", "What player do you want to know the kills for?", true)
                .queue()
        bot.upsertCommand("deaths", "See how many deaths a player has.")
                .addOption(OptionType.STRING, "player", "What player do you want to know the deaths for?", true)
                .queue()
    }
}
