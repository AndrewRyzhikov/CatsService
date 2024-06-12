package mapper;


import dto.UserDto;
import entities.UserEntity;
import models.UserModel;

public class UserMapper {
    public static UserModel dtoToModel(UserDto userDto) {
        System.out.println(userDto);
        return new UserModel(userDto.id(), userDto.name(), userDto.password(), userDto.role(), userDto.ownerId());
    }

    public static UserEntity modelToEntity(UserModel userModel) {
        System.out.println(userModel);
        return new UserEntity(userModel.getId(), userModel.getUsername(), userModel.getPassword(), userModel.getRole(), userModel.getOwnerId());
    }

    public static UserModel entityToModel(UserEntity userEntity) {
        System.out.println(userEntity.getId() + userEntity.getName() + userEntity.getPassword() + userEntity.getRole() + userEntity.getOwnerId());
        return new UserModel(userEntity.getId(), userEntity.getName(), userEntity.getPassword(), userEntity.getRole(), userEntity.getOwnerId());
    }
}