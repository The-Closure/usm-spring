package org.closure.app.UserModule.models;

import java.util.Date;


import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;


@With
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
    // private CommunityModel community;

}