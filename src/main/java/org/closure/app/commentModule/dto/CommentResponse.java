package org.closure.app.commentModule.dto;

import java.util.Objects;

import org.closure.app.UserModule.dto.UserResponse;
import org.closure.app.UserModule.models.UserModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;


@Data
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
