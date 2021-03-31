package org.closure.app.UserModule.mapper;

import org.closure.app.UserModule.dto.UserRequest;
import org.closure.app.UserModule.dto.UserResponse;
import org.closure.app.UserModule.models.UserModel;
import org.closure.app.UserModule.services.ImgService;
import org.closure.app.entities.CommunityEntity;
import org.closure.app.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class UserMapper {


    public static UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );


    public abstract UserResponse userToResponse(UserEntity user);


    public abstract UserModel userToModel(UserEntity user);
    
    public abstract UserEntity modelToUser(UserModel model);

    public UserEntity requestToUser(UserRequest request, CommunityEntity... community){
        return new UserEntity().
        password(request.getPassword()).
        name(request.getName()).
        email(request.getEmail()).
        img(ImgService.generateImg(request.getName())).
        flag(true).            
        communinty(community[0]).
        community_name(community[0].getName());
   

    }
}
