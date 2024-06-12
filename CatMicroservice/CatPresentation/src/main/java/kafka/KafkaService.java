package kafka;

import dtos.cat.CatDto;
import dtos.cat.CatFriendDto;
import mapper.CatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import services.CatService;

@Component
@ComponentScan(basePackages = {"kafka"})
public class KafkaService {
    private final CatService catService;

    @Autowired
    public KafkaService(CatService catService) {
        this.catService = catService;
    }

    @KafkaListener(topics = "add_cat", groupId = "addCatGroup", containerFactory = "catConcurrentKafkaListenerContainerFactory")
    public void addCat(CatDto catDto) {
        catService.add(CatMapper.DtoToModel(catDto));
    }

    @KafkaListener(topics = "add_friend", groupId = "addCatFriendGroup", containerFactory = "catFriendConcurrentKafkaListenerContainerFactory")
    public void addCatFriend(CatFriendDto catFriendDto) {
        catService.addFriend(catFriendDto.id(), catFriendDto.idFriend());
    }

    @KafkaListener(topics = "update_cat", groupId = "updateCatGroup", containerFactory = "catConcurrentKafkaListenerContainerFactory")
    public void updateCat(CatDto catDto) {
        catService.update(CatMapper.DtoToModel(catDto));
    }

    @KafkaListener(topics = "delete_cat", groupId = "deleteCatGroup", containerFactory = "catConcurrentKafkaListenerContainerFactory")
    public void deleteCat(CatDto catDto) {
        catService.delete(catDto.id());
    }
}
