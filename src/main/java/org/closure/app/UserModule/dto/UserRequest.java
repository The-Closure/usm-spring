package org.closure.app.UserModule.dto;

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
public class UserRequest {
    private String name;
    private String email;
    private String password;
    private Long community;

}
