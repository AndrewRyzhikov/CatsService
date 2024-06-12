package kafka;

import dtos.owner.OwnerDto;
import mapper.OwnerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import services.OwnerService;

@Component
@ComponentScan(basePackages = {"kafka"})
public class KafkaService {
    private final OwnerService ownerService;

    @Autowired
    public KafkaService(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @KafkaListener(topics = "add_owner", groupId = "addOwnerGroup", containerFactory = "ownerConcurrentKafkaListenerContainerFactory")
    public void addCat(OwnerDto ownerDto) {
        ownerService.add(OwnerMapper.dtoToModel(ownerDto));
    }

    @KafkaListener(topics = "update_owner", groupId = "updateOwnerGroup", containerFactory = "ownerConcurrentKafkaListenerContainerFactory")
    public void updateCat(OwnerDto ownerDto) {
        ownerService.update(OwnerMapper.dtoToModel(ownerDto));
    }

    @KafkaListener(topics = "delete_owner", groupId = "deleteOwnerGroup", containerFactory = "ownerConcurrentKafkaListenerContainerFactory")
    public void deleteCat(OwnerDto ownerDto) {
        ownerService.delete(ownerDto.id());
    }
}
