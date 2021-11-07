package org.closure.app.commentModule.dto;


import org.closure.app.UserModule.dto.UserResponse;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;


@With
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {
    private Long id;
    private String value;
    private UserResponse uentity;
    private Long postID;

}
