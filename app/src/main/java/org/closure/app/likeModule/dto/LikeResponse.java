package org.closure.app.likeModule.dto;

import org.closure.app.UserModule.dto.UserResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@AllArgsConstructor
@NoArgsConstructor
@With
public class LikeResponse {
    Long id;
    UserResponse model;
}
