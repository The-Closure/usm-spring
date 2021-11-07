package org.closure.app.CommunityModule.dto;

import java.util.List;

import org.closure.app.UserModule.dto.UserResponse;

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
public class CommunityResponse {
    private String name;
    private String description;
    private String img;
    private List<UserResponse> users;

}
