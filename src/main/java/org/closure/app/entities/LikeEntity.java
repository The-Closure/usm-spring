package org.closure.app.entities;

 
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.Entity; 
@Entity
@Table(name = "likes")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class LikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "u_id", nullable = false)
    private UserEntity uentity;
    @ManyToOne
    @JoinColumn(name = "p_id", nullable = false)
    private PostEntity pentity;


    public LikeEntity() {
    }

    public LikeEntity(Long id, UserEntity uentity, PostEntity pentity) {
        this.id = id;
        this.uentity = uentity;
        this.pentity = pentity;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUentity() {
        return this.uentity;
    }

    public void setUentity(UserEntity uentity) {
        this.uentity = uentity;
    }

    public PostEntity getPentity() {
        return this.pentity;
    }

    public void setPentity(PostEntity pentity) {
        this.pentity = pentity;
    }

    public LikeEntity id(Long id) {
        setId(id);
        return this;
    }

    public LikeEntity uentity(UserEntity uentity) {
        setUentity(uentity);
        return this;
    }

    public LikeEntity pentity(PostEntity pentity) {
        setPentity(pentity);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof LikeEntity)) {
            return false;
        }
        LikeEntity likeEntity = (LikeEntity) o;
        return Objects.equals(id, likeEntity.id) && Objects.equals(uentity, likeEntity.uentity) && Objects.equals(pentity, likeEntity.pentity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uentity, pentity);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", uentity='" + getUentity() + "'" +
            ", pentity='" + getPentity() + "'" +
            "}";
    }
  

}