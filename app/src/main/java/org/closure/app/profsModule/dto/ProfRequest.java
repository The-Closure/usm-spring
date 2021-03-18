package org.closure.app.profsModule.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@AllArgsConstructor
@NoArgsConstructor
@With
public class ProfRequest {
    private String name;
    private String email;
    private String password;
}
