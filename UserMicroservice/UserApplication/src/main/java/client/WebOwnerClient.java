package client;

import dtos.cat.CatDto;
import dtos.owner.OwnerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@Service
@ComponentScan(basePackages = "config.web")
public class WebOwnerClient implements OwnerClient {
    private final WebClient webClient;

    @Autowired
    public WebOwnerClient(@Qualifier("WebOwnerConfiguration") WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Optional<OwnerDto> getById(Long id) {
        String uri = UriComponentsBuilder
                .fromPath("/{id}")
                .buildAndExpand(id)
                .toUriString();

        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(OwnerDto.class)
                .blockOptional();
    }

    @Override
    public Optional<List<OwnerDto>> getAll() {
        return webClient.get()
                .uri("")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<OwnerDto>>() {
                })
                .blockOptional();
    }
}
