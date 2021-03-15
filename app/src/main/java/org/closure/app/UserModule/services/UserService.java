package org.closure.app.UserModule.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.closure.app.UserModule.dto.UserRequest;
import org.closure.app.UserModule.dto.UserResponse;
import org.closure.app.UserModule.exceptions.UserErrorException;
import org.closure.app.UserModule.models.UserModel;
import org.closure.app.UserModule.repositories.UserRepo;
import org.closure.app.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ImgService imgService;

    public UserResponse addUser(UserRequest userRequest)
    {
        Optional<UserEntity> user = userRepo.findByEmail(userRequest.getEmail());
        if(user.isEmpty())
        {
            UserEntity uEntity = new UserEntity();
            uEntity.setName(userRequest.getName());
            uEntity.setPassword(userRequest.getPassword());
            uEntity.setEmail(userRequest.getEmail());
            uEntity.setImg(imgService.generateImg(userRequest.getName()));
            uEntity.setFlag(true);            
            userRepo.save(uEntity);
            UserResponse response = 
             new UserResponse()
                .withId(uEntity.getId())
                .withName(uEntity.getName())
                .withImg(uEntity.getImg());
            return response;
        }else throw new UserErrorException(userRequest.getEmail()+" is exist");
    }
    public UserResponse signin(String email, String password)
    {
        UserEntity user = userRepo.findByEmailAndPassword(email, password).orElseThrow(
            () -> new UserErrorException(email+"error in email or password"));
        user.setFlag(true);
        userRepo.save(user);
        UserResponse response = 
            new UserResponse()
                .withId(user.getId())
                .withName(user.getName())
                .withImg(user.getImg());
        return response;
    }
    public boolean delete(Long id, String password)
    {
        UserEntity user = userRepo.findByIdAndPassword(id, password).orElseThrow(
            () -> new UserErrorException("error in id or password"));
        userRepo.delete(user);
        return userRepo.findById(id).isEmpty();
    }
    public boolean signout(Long id, String name)
    {
        UserEntity user = userRepo.findByIdAndName(id, name).orElseThrow(
            () -> new UserErrorException("error in id or name"));
        user.setFlag(false);
        userRepo.save(user);
        return !userRepo.findByIdAndName(id, name).get().isFlag();
    }
    public UserModel edit(UserModel userModel)
    {
        UserEntity user = userRepo.findByIdAndPassword(Long.valueOf(userModel.getId()), userModel.getPassword()).orElseThrow(
            () -> new UserErrorException("error in email or password"));
        user
         .withAge(userModel.getAge())
         .withName(userModel.getName())
         .withPassword(userModel.getPassword())
         .withEmail(userModel.getEmail())
         .withUniversity(userModel.getUniversity())
         .withImg(userModel.getImg())
         .withCommunity_name(userModel.getCommunity_name())
         .withStudy_year(userModel.getStudy_year())       
         .withStart_year(userModel.getStart_year());
        userRepo.save(user);
        return userModel;    
    }

    public List<UserResponse> search(String value)
    {
        List<UserResponse> responses = new ArrayList<UserResponse>();
        userRepo.findByEmailLikeOrNameLike(value, value).stream().forEach((e)->{
            UserResponse userResponse = new UserResponse()
                .withId(e.getId())
                .withName(e.getName())
                .withImg(e.getImg());
            responses.add(userResponse);
        });
        return responses;
    }
}
