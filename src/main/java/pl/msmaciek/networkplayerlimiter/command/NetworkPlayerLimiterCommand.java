package pl.msmaciek.networkplayerlimiter.command;

import com.velocitypowered.api.command.CommandSource;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import dev.rollczi.litecommands.annotations.argument.Arg;
import dev.rollczi.litecommands.annotations.permission.Permission;
import net.kyori.adventure.text.minimessage.MiniMessage;
import pl.msmaciek.networkplayerlimiter.NetworkPlayerLimiter;
import pl.msmaciek.networkplayerlimiter.config.ConfigManager;

@Command(name = "networkplayerlimiter", aliases = {"npl"})
@Permission("networkplayerlimiter.admin")
public class NetworkPlayerLimiterCommand {

    private final MiniMessage miniMessage = MiniMessage.miniMessage();

    @Execute(name = "setLimit")
    public void setLimit(@Context CommandSource source, @Arg int limit) {
        if (limit < 0) {
            source.sendMessage(miniMessage.deserialize("<red>Limit must be a positive number!</red>"));
            return;
        }

        ConfigManager configManager = NetworkPlayerLimiter.getInstance().getConfigManager();
        configManager.setPlayerLimit(limit);
        configManager.saveLimit();
        source.sendMessage(miniMessage.deserialize("<green>Player limit set to " + limit + "</green>"));
    }

    @Execute(name = "reload")
    public void reload(@Context CommandSource source) {
        try {
            ConfigManager configManager = NetworkPlayerLimiter.getInstance().getConfigManager();
            configManager.reload();
            source.sendMessage(miniMessage.deserialize("<green>Configuration reloaded successfully!</green>"));
        } catch (Exception e) {
            source.sendMessage(miniMessage.deserialize("<red>Failed to reload configuration. Please check console for details.</red>"));
            NetworkPlayerLimiter.getInstance().getLogger().error("Failed to reload configuration!", e);
        }
    }

    @Execute
    public void help(@Context CommandSource source) {
        source.sendMessage(miniMessage.deserialize("<gold><bold>NetworkPlayerLimiter Commands:</bold></gold>"));
        source.sendMessage(miniMessage.deserialize("<yellow>/npl setLimit <number></yellow> <gray>- Set the player limit</gray>"));
        source.sendMessage(miniMessage.deserialize("<yellow>/npl reload</yellow> <gray>- Reload the configuration</gray>"));
    }
}

