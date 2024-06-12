package application;

import client.CatClient;
import config.security.UserDetailsImpl;
import contracts.CatService;
import dtos.cat.CatDto;
import dtos.cat.CatFriendDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import user.Role;

import java.util.List;
import java.util.Optional;

@Service
@ComponentScan(basePackages = "client")
public class CatServiceImpl implements CatService {
    private final CatClient catClient;
    private final KafkaTemplate<String, CatDto> catKafka;
    private final KafkaTemplate<String, CatFriendDto> catFriendKafka;

    @Autowired
    public CatServiceImpl(CatClient catClient,
                          KafkaTemplate<String, CatDto> catKafka,
                          KafkaTemplate<String, CatFriendDto> catFriendKafka) {
        this.catClient = catClient;
        this.catKafka = catKafka;
        this.catFriendKafka = catFriendKafka;
    }

    @Override
    public void add(CatDto catDto) {
        catKafka.send("add_cat", catDto);
    }

    @Override
    public void addFriend(Long catId, Long friendId) {
        catFriendKafka.send("add_friend", new CatFriendDto(catId, friendId));
    }

    @Override
    public Optional<CatDto> get(Long id) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (userDetails.getRole() == Role.ROLE_ADMIN) {
            return catClient.getById(id);
        }

        return catClient.getByIdAndOwnerId(id, userDetails.getOwnerId());
    }

    @Override
    public Optional<List<CatDto>> getAll(String breed, String color) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails.getRole() == Role.ROLE_ADMIN) {
            return catClient.getAll(breed, color);
        }

        return catClient.getAllByOwnerId(breed, color, userDetails.getOwnerId());
    }

    @Override
    public void update(CatDto catDto) {
        catKafka.send("update_cat", catDto);
    }

    @Override
    public void delete(Long id) {
        catKafka.send("delete_cat", CatDto.builder().id(id).build());
    }
}
