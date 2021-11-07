package org.closure.app.likeModule.services;

import java.util.List;
import org.closure.app.UserModule.exceptions.UserErrorException;
import org.closure.app.UserModule.repositories.UserRepo;
import org.closure.app.entities.LikeEntity;
import org.closure.app.likeModule.dto.LikeRequest;
import org.closure.app.likeModule.dto.LikeResponse;
import org.closure.app.likeModule.exceptions.LikeErrorException;
import org.closure.app.likeModule.mapper.LikeMapper;
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
        if(!checkUserLikeOnPost(request.getUserID(), request.getPostID()))
        likeRepo.save(
            new LikeEntity().withPentity(   postRepo.findById(request.getPostID()).orElseThrow(
                ()-> new PostErrorException("no post with this id "))).withUentity(       
                    userRepo.findById(request.getUserID()).orElseThrow(
                        ()-> new UserErrorException("no user with this id"))
                 )
        );
        else throw new LikeErrorException("this user already liked this post");
        return true;
    }

    public List<LikeResponse> getLikesForPost(Long postID)
    {
        return likeRepo.findByPentity(postRepo.findById(postID).orElseThrow(
            ()-> new PostErrorException("no post with this id"))).stream().map
                (LikeMapper.INSTANCE::likeToResponse
                        
                )
            .toList();
    }

    public List<LikeResponse> getLikesForUser(Long userID)
    {
        return likeRepo.findByUentity(userRepo.findById(userID).orElseThrow(
            ()-> new UserErrorException("no user with this id"))).stream().map
                (LikeMapper.INSTANCE::likeToResponse
                )
            .toList();
    }
    
    public boolean deleteLike(Long userID, Long postID)
    {
        // boolean isLikeOwner = postRepo.findById(postID).orElseThrow(
        //     ()-> new PostErrorException("no like with this id")).getLikes().stream().filter((l)-> l.getUentity().getId().equals(userID)).toList().size() == 1;
        // System.out.println("value of critical var "+isLikeOwner);
            // if(isLikeOwner) 
            LikeEntity like = likeRepo.findByPentityAndUentity(postRepo.findById(postID).orElseThrow(() -> new PostErrorException("no post with this id")),userRepo.findById(userID).orElseThrow(()-> new UserErrorException("no user with tthis id") )).orElseThrow(()-> new LikeErrorException("no like for this user on this post"));
            likeRepo.deleteById(like.getId());
        return true;
    }
    public boolean checkUserLikeOnPost(Long userID,Long postID)
    {     
        return userRepo.findById(userID).orElseThrow(()-> new UserErrorException("no user with this id")).getLikes().stream().anyMatch((l)-> l.getPentity().getId().equals(postID)&&l.getUentity().getId().equals(userID));
    }
}
