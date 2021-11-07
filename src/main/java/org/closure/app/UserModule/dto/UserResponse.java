package org.closure.app.UserModule.dto;

import java.util.Objects;

import org.closure.app.CommunityModule.models.CommunityModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@With
public class UserResponse {
    private Long id;
    private String name;
    private String img;
    private String email;
    private CommunityModel communinty;
    private boolean isActivated;

}
