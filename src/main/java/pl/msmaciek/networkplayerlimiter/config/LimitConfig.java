package pl.msmaciek.networkplayerlimiter.config;

import de.exlll.configlib.Comment;
import de.exlll.configlib.Configuration;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
public class LimitConfig {

    @Comment({
        "Maximum number of players allowed on the network",
        "Players with bypass permission can join even when the limit is reached"
    })
    private int playerLimit = 100;
}

