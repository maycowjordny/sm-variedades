package com.maycowjordny.sm.infra.mapper;
import com.maycowjordny.sm.domain.model.User;
import com.maycowjordny.sm.infra.dto.UserDtoRequest;
import com.maycowjordny.sm.infra.entity.UserEntity;

public class UserMapper {

    public static User convertRequestUserDtoToDomain(UserDtoRequest userDtoRequest){
        return User.create(
                null,
                userDtoRequest.getName(),
                userDtoRequest.getEmail(),
                userDtoRequest.getPassword(),
        null,
                null
        );
    }

    public static UserEntity toEntity(User user){
        return UserEntity.create(user);
    }

    public static User toDomain(UserEntity user){
        return User.create(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
