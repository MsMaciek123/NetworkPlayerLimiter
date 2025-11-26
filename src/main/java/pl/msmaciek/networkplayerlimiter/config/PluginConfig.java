package pl.msmaciek.networkplayerlimiter.config;

import de.exlll.configlib.Comment;
import de.exlll.configlib.Configuration;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@SuppressWarnings("FieldMayBeFinal")
@Getter
@Configuration
public class PluginConfig {

    @Comment({
        "Message displayed to players when they try to join and the limit is reached",
        "Supports MiniMessage format: https://docs.advntr.dev/minimessage/format.html",
        "Multiple lines supported"
    })
    private List<String> limitReachedMessage = Arrays.asList(
        "<red><bold>Server Full!</bold></red>",
        "<gray>The network has reached the maximum player limit.</gray>"
    );

    @Comment({
        "",
        "Whether to show the configured player limit in the server list (MOTD)"
    })
    private boolean reflectLimitInPing = true;
}

