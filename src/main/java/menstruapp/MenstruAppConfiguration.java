package menstruapp;

import menstruapp.domain.menstruation.MenstruationRegistries;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MenstruAppConfiguration {
    @Bean
    public MenstruationRegistries menstruationRegistries(){
        // TODO in the future: read from DB to load valid registries
        return MenstruationRegistries.of();
    }


}
