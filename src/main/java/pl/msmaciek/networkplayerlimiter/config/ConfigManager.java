package pl.msmaciek.networkplayerlimiter.config;

import de.exlll.configlib.YamlConfigurations;
import lombok.Getter;
import pl.msmaciek.networkplayerlimiter.NetworkPlayerLimiter;

import java.nio.file.Path;

public class ConfigManager {
    private final Path configPath;
    @Getter private PluginConfig config;

    private final Path limitPath;
    private LimitConfig limitConfig;

    public ConfigManager() {
        Path dataDirectory = NetworkPlayerLimiter.getInstance().getDataDirectory();

        this.configPath = dataDirectory.resolve("config.yml");
        this.config = YamlConfigurations.update(configPath, PluginConfig.class);

        this.limitPath = dataDirectory.resolve("limit.yml");
        this.limitConfig = YamlConfigurations.update(limitPath, LimitConfig.class);
    }

    public void reload() {
        this.config = YamlConfigurations.update(configPath, PluginConfig.class);
        this.limitConfig = YamlConfigurations.update(limitPath, LimitConfig.class);
    }

    public void saveLimit() {
        YamlConfigurations.save(limitPath, LimitConfig.class, limitConfig);
    }

    public int getPlayerLimit() {
        return limitConfig.getPlayerLimit();
    }

    public void setPlayerLimit(int limit) {
        limitConfig.setPlayerLimit(limit);
    }
}

