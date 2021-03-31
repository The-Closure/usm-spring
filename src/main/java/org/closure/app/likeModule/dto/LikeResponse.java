package org.closure.app.likeModule.dto;

import org.closure.app.UserModule.dto.UserResponse;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.With;
import lombok.NoArgsConstructor;
import lombok.With;

@Getter @Setter 
@RequiredArgsConstructor
@ToString 
@AllArgsConstructor
@EqualsAndHashCode
public class LikeResponse {
    Long id;
    UserResponse model;
}
