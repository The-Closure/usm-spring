package org.closure.app.likeModule.dto;

import java.util.Objects;

import org.closure.app.UserModule.dto.UserResponse;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@With
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikeResponse {
    Long id;
    String likerName;
    Long likerId;
    String likerImg;

}
