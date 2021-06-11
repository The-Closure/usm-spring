package org.closure.app.likeModule.services;

import java.util.List;

import org.closure.app.CommunityModule.mapper.CommunityMapper;
import org.closure.app.UserModule.dto.UserResponse;
import org.closure.app.UserModule.exceptions.UserErrorException;
import org.closure.app.UserModule.repositories.UserRepo;
import org.closure.app.entities.LikeEntity;
import org.closure.app.likeModule.dto.LikeRequest;
import org.closure.app.likeModule.dto.LikeResponse;
import org.closure.app.likeModule.exceptions.LikeErrorException;
import org.closure.app.likeModule.repositories.LikeRepo;
import org.closure.app.postModule.exceptions.PostErrorException;
import org.closure.app.postModule.repositories.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {

    @Autowired
    LikeRepo likeRepo;
    
    @Autowired
    UserRepo userRepo;

    @Autowired 
    PostRepo postRepo;

    public boolean addLike(LikeRequest request)
    {
        likeRepo.save(
            new LikeEntity(
                null,
                 
                userRepo.findById(request.getUserID()).orElseThrow(
                    ()-> new UserErrorException("no user with this id")),

                postRepo.findById(request.getPostID()).orElseThrow(
                    ()-> new PostErrorException("no post with this id "))

            )
        );
        return true;
    }

    public List<LikeResponse> getLikesForPost(Long postID)
    {
        return likeRepo.findByPentity(postRepo.findById(postID).orElseThrow(
            ()-> new PostErrorException("no post with this id"))).stream().map
                (
                    (mapper) -> new LikeResponse
                        (
                            mapper.getId(),
                            new UserResponse
                            (
                                mapper.getUentity().getId(),
                                mapper.getUentity().getName(),
                                mapper.getUentity().getImg() )
                        )
                )
            .toList();
    }

    public List<LikeResponse> getLikesForUser(Long userID)
    {
        return likeRepo.findByUentity(userRepo.findById(userID).orElseThrow(
            ()-> new UserErrorException("no user with this id"))).stream().map
                (
                    (mapper) -> new LikeResponse(
                        mapper.getId(),
                        new UserResponse
                        (
                            mapper.getUentity().getId(),
                            mapper.getUentity().getName(),
                            mapper.getUentity().getImg()
                        )
                    )
                )
            .toList();
    }
    
    public boolean deleteLike(Long userID, Long likeID)
    {
        boolean isLikeOwner = likeRepo.findById(likeID).orElseThrow(
            ()-> new LikeErrorException("no like with this id")).getUentity().getId().equals(userID);
        if(isLikeOwner) likeRepo.deleteById(likeID);
        else return false;
        return true;
    }
}
