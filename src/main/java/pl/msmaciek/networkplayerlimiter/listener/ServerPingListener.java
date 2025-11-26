package pl.msmaciek.networkplayerlimiter.listener;

import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyPingEvent;
import com.velocitypowered.api.proxy.server.ServerPing;
import pl.msmaciek.networkplayerlimiter.NetworkPlayerLimiter;
import pl.msmaciek.networkplayerlimiter.config.ConfigManager;

public class ServerPingListener {
    private final NetworkPlayerLimiter plugin = NetworkPlayerLimiter.getInstance();
    private final ConfigManager configManager = plugin.getConfigManager();

    @Subscribe
    public void onProxyPing(ProxyPingEvent event) {
        if (!configManager.getConfig().isReflectLimitInPing()) return;

        ServerPing originalPing = event.getPing();

        // Only modify if players info exists
        if (originalPing.getPlayers().isEmpty()) return;

        ServerPing.Players playersOriginal = originalPing.getPlayers().get();
        ServerPing.Players newPlayers = new ServerPing.Players(
            playersOriginal.getOnline(),
            plugin.getConfigManager().getPlayerLimit(),
            playersOriginal.getSample()
        );

        ServerPing newPing = new ServerPing(
            originalPing.getVersion(),
            newPlayers,
            originalPing.getDescriptionComponent(),
            originalPing.getFavicon().orElse(null)
        );

        event.setPing(newPing);
    }
}

