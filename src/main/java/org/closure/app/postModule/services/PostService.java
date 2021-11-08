package org.closure.app.postModule.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.google.firebase.messaging.FirebaseMessagingException;

import org.closure.app.CommunityModule.dto.CommunityResponse;
import org.closure.app.CommunityModule.exceptions.CommunityErrorException;
import org.closure.app.CommunityModule.repositories.CommunityRepo;
import org.closure.app.FirebaseModule.services.FirebaseService;
import org.closure.app.UserModule.dto.UserResponse;
import org.closure.app.UserModule.exceptions.UserErrorException;
import org.closure.app.UserModule.mapper.UserMapper;
import org.closure.app.UserModule.repositories.UserRepo;
import org.closure.app.commentModule.dto.CommentResponse;
import org.closure.app.commentModule.mapper.CommentMapper;
import org.closure.app.entities.PostEntity;
import org.closure.app.entities.UserEntity;
import org.closure.app.postModule.dto.PostRequest;
import org.closure.app.postModule.dto.PostResponse;
import org.closure.app.postModule.exceptions.PostErrorException;
import org.closure.app.postModule.mapper.PostMapper;
import org.closure.app.postModule.repositories.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    PostRepo postRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    CommunityRepo communityRepo;

    @Autowired
    PostMapper postMapper;

    @Autowired
    FirebaseService firebaseService;

    public PostResponse addPost(Long userID, PostRequest request) {
        UserEntity userEntity = userRepo.findById(userID)
                .orElseThrow(() -> new UserErrorException("no user with this id"));
        if (userEntity.getCommuninty().getId() == null)
            throw new CommunityErrorException("no community for this user");

        PostEntity postEntity = postRepo.save(new PostEntity().withAttach(request.getAttach())
                .withCreated_at(new Date()).withTitle(request.getTitle()).withValue(request.getValue())
                .withUEntity(userEntity).withPcommuninty(userEntity.getCommuninty()));
        try {
            firebaseService.postNotification(userEntity, Optional.ofNullable(userEntity.getImg()),postEntity.getId());
        } catch (FirebaseMessagingException e) {
            System.out.println(e.getMessage());
        }

        return postMapper.PostToResponse(postEntity, userID);
    }

    public PostResponse readPost(Long id) {
        PostEntity pEntity = postRepo.findById(id).orElseThrow(() -> new PostErrorException("no post with this id"));
        return postMapper.PostToResponse(pEntity, 0l); // zero to return ppst without like state
    }

    public PostEntity getPost(Long id) {
        return postRepo.findById(id).orElseThrow(() -> new PostErrorException("no post with this id"));
    }

    public PostResponse updatePost(Long postID, Long userID, PostRequest request) {
        PostEntity pEntity = postRepo.findById(postID)
                .orElseThrow(() -> new PostErrorException("no post with this id"));
        if (userRepo.findById(userID).orElseThrow(() -> new UserErrorException("no user with this id")).getPosts()
                .stream().allMatch((p) -> !p.getId().equals(postID)))
            throw new PostErrorException("you don't have permissions to edit this post");
        pEntity = postRepo
                .save(pEntity.withAttach(request.getAttach() != null ? request.getAttach() : pEntity.getAttach())
                        .withTitle(request.getTitle() != null ? request.getTitle() : pEntity.getTitle())
                        .withValue(request.getValue() != null ? request.getValue() : pEntity.getValue()));

        return postMapper.PostToResponse(pEntity, userID); // zero to return ppst without like state
    }

    public boolean deletePost(Long postID, Long userID) {
        PostEntity pEntity = postRepo.findById(postID)
                .orElseThrow(() -> new PostErrorException("no post with this id"));
        userRepo.findById(userID).orElseThrow(() -> new UserErrorException("no user with this id"));
        if (!pEntity.getUEntity().getId().equals(userID))
            return false;
        postRepo.deleteById(postID);
        return postRepo.findById(postID).isEmpty();
    }

    public CommunityResponse getCommunity(Long postID) {
        return postRepo.findById(postID).orElseThrow(() -> new PostErrorException("no post with this id"))
                .getPcommuninty().toCommunityResponse();
    }

    public UserResponse getUser(Long postID) {
        return UserMapper.INSTANCE.userToResponse(postRepo.findById(postID)
                .orElseThrow(() -> new PostErrorException("no post with this id")).getUEntity());
    }

    public List<PostResponse> getAllPosts(Long communityID, Integer pageNo, Integer pageSize, String sortBy,
            Long userID) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());

        Page<PostEntity> pagedResult = postRepo.findAllByPcommuninty(communityRepo.findById(communityID)
                .orElseThrow(() -> new CommunityErrorException("no community with this id")), paging);

        if (pagedResult.hasContent()) {

            Page<PostResponse> response = pagedResult.map((e) -> postMapper.PostToResponse(e, userID));
            return response.getContent();
        } else {
            return new ArrayList<PostResponse>();
        }
    }

    public List<CommentResponse> getCommentsForPost(Long postID) {
        return postRepo.findById(postID).orElseThrow(() -> new PostErrorException("no post with this id")).getComments()
                .stream().map(CommentMapper.INSTANCE::commentToResponse).toList();
    }

    public List<PostResponse> getBestPosts(Long userID) {
        return userRepo.findById(userID).orElseThrow(() -> new UserErrorException("no user with this id"))
                .getCommuninty().getPosts().stream().filter((predicate) -> {
                    return predicate.getCreated_at().after(new Date(new Date().getTime() - 86400000 * 3));
                }).map((e) -> postMapper.PostToResponse(e, userID)).toList().subList(0, 2);
    }

    public boolean checkUserLikeOnPost(Long userID, Long postID) {
        try {

            return userRepo.findById(userID).orElseThrow(() -> new UserErrorException("no user with this id"))
                    .getLikes().stream().anyMatch((l) -> l.getPentity().getId().equals(postID));
        } catch (Exception e) {
            return false;
        }
    }

    public int getPostLikes(Long postID) {
        return postRepo.findById(postID).orElseThrow(() -> new PostErrorException("no post with this id")).getLikes()
                .size();
    }

}
