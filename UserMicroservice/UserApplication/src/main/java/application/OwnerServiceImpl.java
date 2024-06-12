package application;

import client.OwnerClient;
import contracts.OwnerService;
import dtos.owner.OwnerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@ComponentScan(basePackages = "client")
public class OwnerServiceImpl implements OwnerService {
    private final OwnerClient ownerClient;
    private final KafkaTemplate<String, OwnerDto> ownerKafka;

    @Autowired
    public OwnerServiceImpl(OwnerClient ownerClient,
                            KafkaTemplate<String, OwnerDto> ownerKafka) {
        this.ownerClient = ownerClient;
        this.ownerKafka = ownerKafka;
    }

    @Override
    public void add(OwnerDto ownerDto) {
        ownerKafka.send("add_owner", ownerDto);
    }

    @Override
    public Optional<OwnerDto> get(Long id) {
        return ownerClient.getById(id);
    }

    @Override
    public Optional<List<OwnerDto>> getAll() {
        return ownerClient.getAll();
    }

    @Override
    public void update(OwnerDto ownerDto) {
        ownerKafka.send("add_owner", ownerDto);
    }

    @Override
    public void delete(Long id) {
        ownerKafka.send("add_owner", OwnerDto.builder().id(id).build());
    }
}
