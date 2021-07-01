package org.closure.app.postModule.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.closure.app.UserModule.dto.UserResponse;
import org.closure.app.commentModule.dto.CommentResponse;
import org.closure.app.likeModule.dto.LikeResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@With
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostResponse {
    private Long postID;
    private Long userID; 
    private UserResponse uEntity;
    private Long communityID;
    private String title;
    private String value;
    private String attach; 
    private boolean likeState;
    private int likesCount;
    private List<LikeResponse> likes;
    private List<CommentResponse> comments;
}
