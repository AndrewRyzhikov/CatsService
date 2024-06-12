package mapper;

import cat.CatModel;
import dtos.cat.CatDto;
import entity.CatEntity;

import java.util.ArrayList;
import java.util.List;

public class CatMapper {
    public static CatModel DtoToModel(CatDto catDto) {
        return new CatModel(
                catDto.id(),
                catDto.name(),
                catDto.birthDate(),
                catDto.breed(),
                catDto.color(),
                catDto.ownerId(),
                new ArrayList<>());
    }

    public static CatDto modelToDto(CatModel catModel) {
        return new CatDto(
                catModel.getId(),
                catModel.getName(),
                catModel.getBirthDate(),
                catModel.getBreed(),
                catModel.getColor(),
                catModel.getOwnerId());
    }

    public static CatEntity modelToEntity(CatModel catModel) {
        List<CatEntity> catEntities = new ArrayList<>(catModel.getFriends().stream()
                .map(cat -> new CatEntity(
                        cat.getId(),
                        cat.getName(),
                        cat.getBirthDate(),
                        cat.getBreed(),
                        cat.getColor(),
                        cat.getOwnerId(),
                        new ArrayList<>()
                ))
                .toList());

        return new CatEntity(
                catModel.getId(),
                catModel.getName(),
                catModel.getBirthDate(),
                catModel.getBreed(),
                catModel.getColor(),
                catModel.getOwnerId(),
                catEntities
        );

    }

    public static CatModel entityToModel(CatEntity catEntity) {
        List<CatModel> catModels = new ArrayList<>(catEntity.getFriends().stream()
                .map(cat -> new CatModel(
                        cat.getId(),
                        cat.getName(),
                        cat.getBirthDate(),
                        cat.getBreed(),
                        cat.getColor(),
                        cat.getOwnerId(),
                        new ArrayList<>()))
                .toList());

        return new CatModel(
                catEntity.getId(),
                catEntity.getName(),
                catEntity.getBirthDate(),
                catEntity.getBreed(),
                catEntity.getColor(),
                catEntity.getOwnerId(),
                catModels
        );
    }
}