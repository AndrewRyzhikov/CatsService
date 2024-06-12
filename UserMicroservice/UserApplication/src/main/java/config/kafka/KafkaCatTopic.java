package config.kafka;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaCatTopic {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public KafkaAdmin kafkaCatAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(
                AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapServers);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic addCatTopic() {
        return new NewTopic("add_cat", 1, (short)1);
    }

    @Bean
    public NewTopic addCatFriendTopic() {
        return new NewTopic("add_friend", 1, (short)1);
    }

    @Bean
    public NewTopic updateCatTopic() {
        return new NewTopic("update_cat", 1, (short)1);
    }

    @Bean
    public NewTopic deleteCatTopic() {
        return new NewTopic("delete_cat", 1, (short)1);
    }
}
