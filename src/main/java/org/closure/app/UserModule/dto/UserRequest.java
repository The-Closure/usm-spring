package org.closure.app.UserModule.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.With;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter @Setter 
@RequiredArgsConstructor
@ToString 
@EqualsAndHashCode
@AllArgsConstructor
public class UserRequest {
    private String name;
    private String email;
    private String password;
    private Long community;
}
