package org.closure.app.postModule.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.closure.app.CommunityModule.dto.CommunityResponse;
import org.closure.app.CommunityModule.exceptions.CommunityErrorException;
import org.closure.app.CommunityModule.repositories.CommunityRepo;
import org.closure.app.UserModule.dto.UserResponse;
import org.closure.app.UserModule.exceptions.UserErrorException;
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

    public PostResponse addPost(Long userID, PostRequest request)
    {
        UserEntity userEntity = userRepo.findById(userID).orElseThrow(
            ()-> new UserErrorException("no user with this id"));
        if(userEntity.getCommuninty().getId() == null) 
            throw new CommunityErrorException("no community for this user");
        PostEntity postEntity = postRepo.save
            (
                new PostEntity()
                    .withAttach(request.getAttach())
                    .withCreated_at(new Date())
                    .withTitle(request.getTitle())
                    .withValue(request.getValue())
                    .withUEntity(userEntity)
                    .withPcommuninty(userEntity.getCommuninty())
            );
        return PostMapper.mapper.PostToResponse(postEntity);
    }
    public PostResponse getpost(Long id)
    {
        PostEntity pEntity = postRepo.findById(id).orElseThrow(
            () -> new PostErrorException("no post with this id"));
        return PostMapper.mapper.PostToResponse(pEntity);
    }
    public PostResponse updatePost(Long postID, Long userID, PostRequest request)
    {
        PostEntity pEntity = postRepo.findById(postID).orElseThrow(
            () -> new PostErrorException("no post with this id"));
            if(userRepo.findById(userID).orElseThrow(
                () -> new UserErrorException("no user with this id"))
                    .getPosts().stream().allMatch(
                        (p) -> !p.getId().equals(postID))) 
                            throw new PostErrorException("you don't have permissions to edit this post");
            pEntity = postRepo.save(pEntity 
            .withAttach(request.getAttach() != null ? request.getAttach() : pEntity.getAttach())
            .withTitle(request.getTitle() != null ? request.getTitle() : pEntity.getTitle())
            .withValue(request.getValue() != null ? request.getValue() : pEntity.getValue()));
            
            return PostMapper.mapper.PostToResponse(pEntity);
    }
    public boolean deletePost(Long postID, Long userID)
    {
        PostEntity pEntity = postRepo.findById(postID).orElseThrow(
            () -> new PostErrorException("no post with this id"));
        userRepo.findById(userID).orElseThrow(
            () -> new UserErrorException("no user with this id"));
        if(!pEntity.getUEntity().getId().equals(userID)) return false;
        postRepo.deleteById(postID);
        return postRepo.findById(postID).isEmpty();
    }
    public CommunityResponse getCommunity(Long postID)
    {
        return postRepo.findById(postID).orElseThrow(
            () -> new PostErrorException("no post with this id"))
                .getPcommuninty().toCommunityResponse();
    }
    public UserResponse getUser(Long postID)
    {
        return postRepo.findById(postID).orElseThrow(
            () -> new PostErrorException("no post with this id"))
                .getUEntity().toUserResponse();
    }
    public List<PostResponse> getAllPosts(Long communityID, Integer pageNo, Integer pageSize, String sortBy) {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
 
        Page<PostEntity> pagedResult = postRepo.findAllByPcommuninty(
            communityRepo.findById(communityID).orElseThrow(
                ()-> new CommunityErrorException("no community with this id")), 
            paging
        );
         
        if(pagedResult.hasContent()) {
            
            Page<PostResponse> response = pagedResult.map(PostMapper.mapper::PostToResponse);
            return response.getContent();
        } else {
            return new ArrayList<PostResponse>();
        }
    }

    public List<CommentResponse> getCommentsForPost(Long postID)
    {
        return postRepo.findById(postID).orElseThrow(
            ()-> new PostErrorException("no post with this id"))
            .getComments()
            .stream()
            .map(
                CommentMapper.INSTANCE::commentToResponse
            ).toList();
    }


}
