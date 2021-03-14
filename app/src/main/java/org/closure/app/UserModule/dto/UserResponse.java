package org.closure.app.UserModule.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@AllArgsConstructor
@NoArgsConstructor
@With
public class UserResponse {
    private Long id;
    private String name;
    private String img;

}
