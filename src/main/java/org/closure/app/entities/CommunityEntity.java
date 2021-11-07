package org.closure.app.entities;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import org.closure.app.CommunityModule.dto.CommunityResponse;

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
@Entity
@Table(name = "communities")
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id"
)
public class CommunityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    private String name;
    private String description;
    private String img;
    @OneToMany(mappedBy = "communinty")
    List<UserEntity> users;
    @OneToMany(mappedBy = "pcommuninty")
    List<PostEntity> posts;
    // @OneToOne(fetch = FetchType.LAZY, optional = true)
    // @JoinColumn(name = "user_id", nullable = true)
    // private UserEntity admin;
    
    public CommunityResponse toCommunityResponse()
    {
        return new CommunityResponse()
            .description(description)
            .img(img)
            .name(name);
    }


}