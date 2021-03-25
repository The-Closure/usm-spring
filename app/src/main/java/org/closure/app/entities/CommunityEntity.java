package org.closure.app.entities;

import java.util.List;
 

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
    // @OneToOne(fetch = FetchType.LAZY, optional = true)
    // @JoinColumn(name = "user_id", nullable = true)
    // private UserEntity admin;
    private String img;
    @OneToMany(mappedBy = "communinty")
    List<UserEntity> users;
    @OneToMany(mappedBy = "pcommuninty")
    List<PostEntity> posts;

    public CommunityResponse toCommunityResponse()
    {
        return new CommunityResponse()
            .withDescription(description)
            .withImg(img)
            .withName(name);
    }

    public Object fromResponse(CommunityResponse findById) {
        return null;
    }

}