package client;

import dtos.cat.CatDto;
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
public class WebCatClient implements CatClient {
    private final WebClient webClient;

    @Autowired
    public WebCatClient(@Qualifier("WebCatConfiguration") WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Optional<CatDto> getById(Long id) {
        String uri = UriComponentsBuilder
                .fromPath("/admin/{id}")
                .buildAndExpand(id)
                .toUriString();

        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(CatDto.class)
                .blockOptional();
    }

    @Override
    public Optional<CatDto> getByIdAndOwnerId(Long id, Long ownerId) {
        String uri = UriComponentsBuilder
                .fromPath("/{id}/{ownerId}")
                .buildAndExpand(id, ownerId)
                .toUriString();

        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono(CatDto.class)
                .blockOptional();
    }

    @Override
    public Optional<List<CatDto>> getAll(String breed, String color) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromPath("/admin");

        return getCatDtos(breed, color, uriBuilder);
    }

    @Override
    public Optional<List<CatDto>> getAllByOwnerId(String breed, String color, Long ownerId) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromPath("/" + ownerId);

        return getCatDtos(breed, color, uriBuilder);
    }

    private Optional<List<CatDto>> getCatDtos(String breed, String color, UriComponentsBuilder uriBuilder) {
        if (breed != null) {
            uriBuilder.queryParam("id", breed);
        }

        if (color != null) {
            uriBuilder.queryParam("color", color);
        }

        return webClient.get()
                .uri(uriBuilder.toUriString())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<CatDto>>() {
                })
                .blockOptional();
    }
}
