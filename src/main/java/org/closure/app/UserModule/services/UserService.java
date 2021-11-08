package org.closure.app.UserModule.services;

import java.util.List;

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
import org.closure.app.entities.CommunityEntity;
import org.closure.app.entities.UserEntity;
import org.closure.app.postModule.dto.PostResponse;
import org.closure.app.postModule.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CommunityRepo communityRepo;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    PostMapper postMapper;

    @Value("${org.closure.mail.service.sernder:anas.anas1998.tar@gmail.com}")
    private String sender;
    @Value("${org.closure.host.name:http://localhost:8080}")
    private String hostName;

    @Transactional
    public UserResponse addUser(UserRequest userRequest) throws Exception {
        if (userRepo.findByEmail(userRequest.getEmail()).isEmpty()) {

            CommunityEntity communityEntity = communityRepo.findById(userRequest.getCommunity())
                    .orElseThrow(() -> new Exception("no community with this id"));
            UserEntity userEntity = UserMapper.INSTANCE.requestToUser(userRequest, communityEntity);
            userEntity = userRepo.save(userEntity);
            UserResponse userResponse = UserMapper.INSTANCE.userToResponse(userEntity);
            sendEmail(userResponse);

            return userResponse;
        }

        else
            throw new UserErrorException(userRequest.getEmail() + " is exist");
    }

    public UserResponse readUser(Long uid) throws Exception {
        return UserMapper.INSTANCE
                .userToResponse(userRepo.findById(uid).orElseThrow(() -> new Exception("no user with this id")));

    }

    public UserEntity getUser(Long uid) throws Exception {
        return userRepo.findById(uid).orElseThrow(() -> new Exception("no user with this id"));

    }

    public UserResponse signin(String email, String password) {
        return UserMapper.INSTANCE.userToResponse(userRepo.save(userRepo.findByEmailAndPassword(email, password)
                .orElseThrow(() -> new UserErrorException(email + " error in email or password")).withFlag(true)));
    }

    public boolean delete(Long id, String password) {
        userRepo.delete(userRepo.findByIdAndPassword(id, password)
                .orElseThrow(() -> new UserErrorException("error in id or password")));
        return userRepo.findById(id).isEmpty();
    }

    public boolean signout(Long id, String name) {
        userRepo.save(userRepo.findByIdAndName(id, name)
                .orElseThrow(() -> new UserErrorException("error in id or name")).withFlag(false));
        return !userRepo.findByIdAndName(id, name).get().isFlag();
    }

    //TODO : add udpate function for user info

    public List<UserResponse> search(String value) {
        return userRepo.findByEmailLikeOrNameLike(value).stream().map(UserMapper.INSTANCE::userToResponse).toList();
    }

    public UserEntity getById(Long id) {
        return userRepo.findById(id).orElseThrow(() -> new UserErrorException("no user with this id"));
    }

    public List<BoardResponse> getBoards(Long userID) {
        return userRepo.findById(userID).orElseThrow(() -> new BoardErrorException("no board with this name"))
                .getBoards().stream().map(BoardMapper.INSTANCE::boardToResponse).toList();

    }

    public List<PostResponse> getPosts(Long userID) {
        return userRepo.findById(userID).orElseThrow(() -> new UserErrorException("no user with this id")).getPosts()
                .stream().map((e) -> postMapper.PostToResponse(e, userID)).toList();

    }


    public List<CommentResponse> getCommentsForUser(Long userID) {
        return userRepo.findById(userID).orElseThrow(() -> new UserErrorException("no post with this id")).getComments()
                .stream().map(CommentMapper.INSTANCE::commentToResponse).toList();
    }

    // @Async
    public UserResponse sendEmail(UserResponse userResponse) throws Exception {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(sender);
        msg.setTo(userResponse.getEmail());

        msg.setSubject("Verfication mail (USM - verfication mail)");
        String text = String.format(
                "thanks for joining our platform \nplease verify your account by this link : %s/v2/api/user/home/verifyaccount/%s",
                hostName, userResponse.getId());
        msg.setText(text);
        javaMailSender.send(msg);
        return userResponse;
    }

    public UserResponse verifyAccount(Long uid) throws Exception {
        UserEntity user = userRepo.findById(uid).orElseThrow(() -> new Exception("no user with this account"));
        user.setActivated(true);
        return UserMapper.INSTANCE.userToResponse(userRepo.save(user));
    }

}
