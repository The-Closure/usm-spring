package org.closure.app.UserModule.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.closure.app.UserModule.dto.UserRequest;
import org.closure.app.UserModule.dto.UserResponse;
import org.closure.app.UserModule.exceptions.UserErrorException;
import org.closure.app.UserModule.models.UserModel;
import org.closure.app.UserModule.repositories.UserRepo;
import org.closure.app.boardModule.dto.BoardResponse;
import org.closure.app.boardModule.exceptions.BoardErrorException;
import org.closure.app.entities.BoardEntity;
import org.closure.app.entities.UserEntity;
import org.closure.app.postModule.dto.PostResponse;
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

    public UserEntity getById(Long id)
    {
        return userRepo.findById(id).orElseThrow(
            ()-> new UserErrorException("no user with this id"));
    }

    public List<BoardResponse> getBoards(Long userID)
    {
        List<BoardEntity> boards= userRepo.findById(userID).orElseThrow(
            ()-> new BoardErrorException("no board with this name")).getBoards();
        List<BoardResponse> BoardResponses = new ArrayList<>();
        boards.forEach((e) -> {
            BoardResponses.add
                (
                    new BoardResponse()
                        .withId(e.getId())
                        .withName(e.getName())
                        .withImage(e.getImage())
                        .withDescription(e.getDescription())
                );
        });
        return BoardResponses;
    }

    public List<PostResponse> getPosts(Long userID)
    {
        UserEntity uEntity = userRepo.findById(userID).orElseThrow(
            () -> new UserErrorException("no user with this id"));
        List<PostResponse> postResponses = new ArrayList<>();
        uEntity.getPosts().forEach(
            (p) -> {
                PostResponse postResponse = new PostResponse()
                    .withAttach(p.getAttach())
                    .withCommunityID(p.getPcommuninty().getId())
                    .withPostID(p.getId())
                    .withTitle(p.getTitle())
                    .withUserID(p.getUEntity().getId())
                    .withValue(p.getValue());
                postResponses.add(postResponse);
            });
        return postResponses;
    }
      //TODO: add method to fetch general info about user by its id
      
      //TODO: add method to fetch comment for user by its id

}
