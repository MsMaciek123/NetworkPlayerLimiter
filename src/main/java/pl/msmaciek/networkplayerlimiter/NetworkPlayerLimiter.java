package pl.msmaciek.networkplayerlimiter;

import com.google.inject.Inject;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.plugin.Plugin;
import org.slf4j.Logger;

@Plugin(id = "networkplayerlimiter", name = "NetworkPlayerLimiter", version = BuildConstants.VERSION)
public class NetworkPlayerLimiter {

    @Inject
    private Logger logger;

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
    }
}
