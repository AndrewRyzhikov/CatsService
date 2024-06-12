package mapper;

import owner.OwnerModel;
import entity.OwnerEntity;

public class OwnerMapper {

    public static OwnerModel dtoToModel(dtos.owner.OwnerDto ownerDto) {
        return new OwnerModel(
                ownerDto.id(),
                ownerDto.name(),
                ownerDto.birthDate());
    }

    public static dtos.owner.OwnerDto modelToDto(OwnerModel ownerModel) {
        return new dtos.owner.OwnerDto(
                ownerModel.getId(),
                ownerModel.getName(),
                ownerModel.getBirthDate());
    }

    public static OwnerEntity modelToEntity(OwnerModel ownerModel) {
        return new OwnerEntity(
                ownerModel.getId(),
                ownerModel.getName(),
                ownerModel.getBirthDate());
    }

    public static OwnerModel entityToModel(OwnerEntity ownerEntity) {
        return new OwnerModel(ownerEntity.getId(),
                ownerEntity.getName(),
                ownerEntity.getBirthDate());
    }
}
