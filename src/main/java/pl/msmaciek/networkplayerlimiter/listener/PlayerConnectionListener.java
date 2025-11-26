package pl.msmaciek.networkplayerlimiter.listener;

import com.velocitypowered.api.event.PostOrder;
import com.velocitypowered.api.event.ResultedEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.LoginEvent;
import com.velocitypowered.api.proxy.ProxyServer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import pl.msmaciek.networkplayerlimiter.NetworkPlayerLimiter;
import pl.msmaciek.networkplayerlimiter.config.ConfigManager;

import java.util.List;

public class PlayerConnectionListener {

    public static final String BYPASS_PERMISSION = "networkplayerlimiter.bypass";
    private final MiniMessage miniMessage = MiniMessage.miniMessage();

    private final NetworkPlayerLimiter plugin = NetworkPlayerLimiter.getInstance();
    private final ProxyServer proxyServer = plugin.getProxyServer();
    private final ConfigManager configManager = plugin.getConfigManager();

    @Subscribe(order = PostOrder.FIRST)
    public void onPlayerLogin(LoginEvent event) {
        if (event.getPlayer().hasPermission(BYPASS_PERMISSION))
            return;

        int currentPlayers = proxyServer.getPlayerCount();
        int limit = configManager.getPlayerLimit();

        if (currentPlayers < limit) return;

        // Limit reached
        List<String> messageLines = configManager.getConfig().getLimitReachedMessage();

        List<Component> components = messageLines.stream()
            .map(miniMessage::deserialize)
            .toList();

        Component finalMessage = Component.empty();
        for (int i = 0; i < components.size(); i++) {
            finalMessage = finalMessage.append(components.get(i));

            if (i < components.size() - 1)
                finalMessage = finalMessage.append(Component.newline());
        }

        event.setResult(ResultedEvent.ComponentResult.denied(finalMessage));
    }
}

