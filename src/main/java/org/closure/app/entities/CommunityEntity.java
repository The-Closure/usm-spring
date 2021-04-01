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


    public CommunityEntity() {
    }

    public CommunityEntity(Long id, String name, String description, String img, List<UserEntity> users, List<PostEntity> posts) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.img = img;
        this.users = users;
        this.posts = posts;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return this.img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public List<UserEntity> getUsers() {
        return this.users;
    }

    public void setUsers(List<UserEntity> users) {
        this.users = users;
    }

    public List<PostEntity> getPosts() {
        return this.posts;
    }

    public void setPosts(List<PostEntity> posts) {
        this.posts = posts;
    }

    public CommunityEntity id(Long id) {
        setId(id);
        return this;
    }

    public CommunityEntity name(String name) {
        setName(name);
        return this;
    }

    public CommunityEntity description(String description) {
        setDescription(description);
        return this;
    }

    public CommunityEntity img(String img) {
        setImg(img);
        return this;
    }

    public CommunityEntity users(List<UserEntity> users) {
        setUsers(users);
        return this;
    }

    public CommunityEntity posts(List<PostEntity> posts) {
        setPosts(posts);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CommunityEntity)) {
            return false;
        }
        CommunityEntity communityEntity = (CommunityEntity) o;
        return Objects.equals(id, communityEntity.id) && Objects.equals(name, communityEntity.name) && Objects.equals(description, communityEntity.description) && Objects.equals(img, communityEntity.img) && Objects.equals(users, communityEntity.users) && Objects.equals(posts, communityEntity.posts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, img, users, posts);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", img='" + getImg() + "'" +
            ", users='" + getUsers() + "'" +
            ", posts='" + getPosts() + "'" +
            "}";
    }
  
}