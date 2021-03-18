package org.closure.app.commentModule.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@AllArgsConstructor
@NoArgsConstructor
@With
public class CommentResponse {
    private String value;
    private Long userID;
    private Long postID;
}
