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

        ServerPing newPing = originalPing.asBuilder()
            .maximumPlayers(plugin.getConfigManager().getPlayerLimit())
            .build();

        event.setPing(newPing);
    }
}

