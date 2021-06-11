package org.closure.app.UserModule.services;

import java.util.List;

import org.closure.app.CommunityModule.exceptions.CommunityErrorException;
import org.closure.app.CommunityModule.repositories.CommunityRepo;
import org.closure.app.UserModule.dto.UserRequest;
import org.closure.app.UserModule.dto.UserResponse;
import org.closure.app.UserModule.exceptions.UserErrorException;
import org.closure.app.UserModule.mapper.UserMapper;
import org.closure.app.UserModule.models.UserModel;
import org.closure.app.UserModule.repositories.UserRepo;
import org.closure.app.boardModule.dto.BoardResponse;
import org.closure.app.boardModule.exceptions.BoardErrorException;
import org.closure.app.boardModule.mapper.BoardMapper;
import org.closure.app.commentModule.dto.CommentResponse;
import org.closure.app.commentModule.mapper.CommentMapper;
import org.closure.app.entities.UserEntity;
import org.closure.app.postModule.dto.PostResponse;
import org.closure.app.postModule.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CommunityRepo communityRepo;
    
    @Autowired
    private JavaMailSender javaMailSender;

    public UserResponse addUser(UserRequest userRequest)
    {
        if(userRepo.findByEmail(userRequest.getEmail()).isEmpty())
            return UserMapper.INSTANCE.userToResponse(
                userRepo.save(userRequest.getCommunity() != null ?
                    UserMapper.INSTANCE.requestToUser(
                        userRequest, 
                        communityRepo.findById(userRequest.getCommunity()).orElseThrow(
                            () -> new CommunityErrorException("no community with this id"))) 
                    : UserMapper.INSTANCE.requestToUser(userRequest)
                )
            );
        else 
            throw new UserErrorException(userRequest.getEmail()+" is exist");
    }
    public UserResponse signin(String email, String password)
    {
         return  UserMapper.INSTANCE.userToResponse(
             userRepo.save(
                 userRepo.findByEmailAndPassword(email, password).orElseThrow(
                    () -> new UserErrorException(email+" error in email or password")
                ).flag(true)
            )
        );
    }
    public boolean delete(Long id, String password)
    {
        userRepo.delete(userRepo.findByIdAndPassword(id, password).orElseThrow(
            () -> new UserErrorException("error in id or password")));
        return userRepo.findById(id).isEmpty();
    }
    public boolean signout(Long id, String name)
    {
        userRepo.save(userRepo.findByIdAndName(id, name).orElseThrow(
            () -> new UserErrorException("error in id or name")).flag(false));
        return !userRepo.findByIdAndName(id, name).get().isFlag();
    }
    public UserModel edit(UserModel userModel)
    {
        UserEntity user = userRepo.findByIdAndPassword(Long.valueOf(userModel.getId()), userModel.getPassword()).orElseThrow(
            () -> new UserErrorException("error in email or password"));
        user
         .age(userModel.getAge())
         .name(userModel.getName())
         .password(userModel.getPassword())
         .email(userModel.getEmail())
         .university(userModel.getUniversity())
         .img(userModel.getImg())
         .community_name(userModel.getCommunity_name())
         .study_year(userModel.getStudy_year())       
         .start_year(userModel.getStart_year());
        userRepo.save(user);
        return userModel;    
    }

    public List<UserResponse> search(String value)
    {
       return userRepo.findByEmailLikeOrNameLike(value).stream().map(
           UserMapper.INSTANCE::userToResponse
        ).toList();
    }

    public UserEntity getById(Long id)
    {
        return userRepo.findById(id).orElseThrow(
            ()-> new UserErrorException("no user with this id"));
    }

    public List<BoardResponse> getBoards(Long userID)
    {
        return userRepo.findById(userID).orElseThrow(
            ()-> new BoardErrorException("no board with this name")).getBoards().stream().map(
                BoardMapper.INSTANCE::boardToResponse).toList();
       
    }

    public List<PostResponse> getPosts(Long userID)
    {
        return userRepo.findById(userID).orElseThrow(
            () -> new UserErrorException("no user with this id"))
            .getPosts()
            .stream()
            .map(PostMapper.mapper::PostToResponse)
            .toList();
        
        
    }

      //TODO: add method to fetch general info about user by its id(Unconvinced)
      
    public List<CommentResponse> getCommentsForUser(Long userID)
    {
        return userRepo.findById(userID).orElseThrow(
            ()-> new UserErrorException("no post with this id"))
        .getComments()
        .stream()
        .map(
            CommentMapper.INSTANCE::commentToResponse
        ).toList();
    }

    public boolean sendEmail(String id, String email)
    {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom("support@smart-mira.com");
        msg.setTo(email);

        msg.setSubject("Verfication mail (Magic Mirror)");
        msg.setText("thanks for joining our platform \nplease verify your account by this link : http://localhost:8080/api/v2/users/home/verifyaccount/"+id);

        javaMailSender.send(msg);
        return true;
    }



}
