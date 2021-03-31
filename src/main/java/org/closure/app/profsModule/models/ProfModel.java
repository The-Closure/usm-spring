package org.closure.app.profsModule.models;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@AllArgsConstructor
@NoArgsConstructor
@With
public class ProfModel {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String spec;
    private Date created_at;
    private String image;
}
