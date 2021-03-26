package org.closure.app.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import org.closure.app.UserModule.dto.UserResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@With
@Table(name = "user")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class UserEntity {
    /**
     * attrs area
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Column(name = "name")
    private String name;
    private String email;
    private String password;
    private String university;
    @Column(name = "specialize")
    private String community_name;
    private Integer study_year;
    @JsonFormat(pattern = "yyyy-mm")
    private Date start_year;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date age;
    @JsonIgnore
    private boolean flag;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date created_at;
    private String img;

    /**
     * relational attrs
     */
    @ManyToOne
    @JoinColumn(name = "com_id", nullable = true)
    private CommunityEntity communinty;

    @OneToMany(mappedBy = "uEntity" ,cascade = CascadeType.ALL , orphanRemoval = true)
    List<PostEntity> posts;

    @OneToMany(mappedBy = "uentity" , cascade = CascadeType.ALL , orphanRemoval = true)
    List<LikeEntity> likes;
    
    @ManyToMany(cascade ={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "users_in_board", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
        @JoinColumn(name = "board_id") })
    List<BoardEntity> boards;

    @OneToMany(mappedBy = "uEntity"  , cascade = CascadeType.ALL ,orphanRemoval = true)
    List<CommentEntity> comments;

    // @OneToOne(mappedBy = "admin")
    // private CommunityEntity comm_admin;
    /**
     * constructors
     */

     public UserResponse toUserResponse()
     {
         return new UserResponse()
            .withId(id)
            .withImg(img)
            .withName(name);
     }
}