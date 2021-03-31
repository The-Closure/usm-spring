package org.closure.app.UserModule.models;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;


@Data
@AllArgsConstructor
@NoArgsConstructor
@With
public class UserModel {

    private Long id ;
    private String name ;
    private String email ;
    private String password ;
    private String university ;
    private String img ;
    private String community_name ;
    private Integer  study_year ;
    private Date  start_year ;
    private Date  age ;
}