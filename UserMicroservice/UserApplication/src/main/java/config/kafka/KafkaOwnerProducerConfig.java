package config.kafka;

import dtos.owner.OwnerDto;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaOwnerProducerConfig {
    private String bootstrapServers = "localhost:9092";

    private Map<String, Object> ConfigProps() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapServers);
        configProps.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        configProps.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                JsonSerializer.class);
        configProps.put(
                JsonSerializer.ADD_TYPE_INFO_HEADERS,
                false);
        return configProps;
    }

    @Bean
    public ProducerFactory<String, OwnerDto> ownerProducerFactory() {
        return new DefaultKafkaProducerFactory<>(ConfigProps());
    }

    @Bean
    public KafkaTemplate<String, OwnerDto> ownerKafkaTemplate(ProducerFactory<String, OwnerDto> ownerProducerFactory) {
        return new KafkaTemplate<>(ownerProducerFactory);
    }
}

