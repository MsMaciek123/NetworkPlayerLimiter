package pl.msmaciek.networkplayerlimiter;

import com.google.inject.Inject;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import dev.rollczi.litecommands.LiteCommands;
import dev.rollczi.litecommands.velocity.LiteVelocityFactory;
import lombok.Getter;
import org.slf4j.Logger;
import pl.msmaciek.networkplayerlimiter.command.NetworkPlayerLimiterCommand;
import pl.msmaciek.networkplayerlimiter.config.ConfigManager;
import pl.msmaciek.networkplayerlimiter.listener.PlayerConnectionListener;
import pl.msmaciek.networkplayerlimiter.listener.ServerPingListener;

import java.nio.file.Path;

@Plugin(
    id = "networkplayerlimiter",
    name = "NetworkPlayerLimiter",
    version = BuildConstants.VERSION,
    authors = {"MsMaciek"}
)
public class NetworkPlayerLimiter {

    @Getter private static NetworkPlayerLimiter instance;

    @Inject @Getter
    private Logger logger;

    @Inject @Getter
    private ProxyServer proxyServer;

    @Inject @Getter @DataDirectory
    private Path dataDirectory;

    @Getter
    private ConfigManager configManager;

    private LiteCommands<CommandSource> liteCommands;

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        instance = this;

        configManager = new ConfigManager();

        proxyServer.getEventManager().register(this, new PlayerConnectionListener());
        proxyServer.getEventManager().register(this, new ServerPingListener());

        liteCommands = LiteVelocityFactory.builder(proxyServer)
            .commands(new NetworkPlayerLimiterCommand())
            .build();

        logger.info("NetworkPlayerLimiter has been enabled!");
    }
}

