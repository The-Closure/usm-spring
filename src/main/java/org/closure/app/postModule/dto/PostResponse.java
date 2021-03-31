package org.closure.app.postModule.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@AllArgsConstructor
@NoArgsConstructor
@With
public class PostResponse {
    private Long postID;
    private Long userID; 
    private Long communityID;
    private String title;
    private String value;
    private String attach; 
}
