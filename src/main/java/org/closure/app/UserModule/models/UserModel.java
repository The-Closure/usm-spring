package org.closure.app.UserModule.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.JsonAdapter;

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

    @JsonIgnore
    private Long id ;
    @JsonProperty(required = true)
    private String name ;
    @JsonProperty(required = true)
    private String email ;
    @JsonProperty(required = true)
    private String password ;
    @JsonProperty(required = true)
    private String university ;
    @JsonProperty(required = true)
    private String img ;
    @JsonProperty(required = true)
    private String community_name ;
    @JsonProperty(required = true)
    private Integer  study_year ;
    @JsonProperty(required = true)
    private Date  start_year ;
    @JsonProperty(required = true)
    private Date  age ;
    // private CommunityModel community;

}