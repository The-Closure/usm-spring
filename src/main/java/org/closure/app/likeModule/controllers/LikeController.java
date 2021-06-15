package org.closure.app.likeModule.controllers;

import java.util.List;

import org.closure.app.likeModule.dto.LikeRequest;
import org.closure.app.likeModule.dto.LikeResponse;
import org.closure.app.likeModule.services.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping(path = "/v2/api/likes")
public class LikeController {
    
    @Autowired
    LikeService likeService;

    @PostMapping(value="/addlike")
    public boolean addLike(@RequestBody LikeRequest request) 
    {
        return likeService.addLike(request);
    }
    
    @GetMapping(value="/getlikesforpost")
    public List<LikeResponse> getLikesForPost(@RequestParam(name = "postID") Long postID) 
    {
        return likeService.getLikesForPost(postID);
    }

    @GetMapping(value="/getlikesforuser")
    public List<LikeResponse> getLikesForUser(@RequestParam(name = "userID") Long userID) 
    {
        return likeService.getLikesForUser(userID);
    }

    @DeleteMapping(value = "/deletelike")
   public boolean deleteLike
   (
       @RequestParam(name = "userID") Long userID, 
       @RequestParam(name = "postID") Long postID
   )
   {
       return likeService.deleteLike(userID, postID);
   }
    

}
