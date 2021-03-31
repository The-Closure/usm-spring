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
@Table(name = "comments")
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id"
)

public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "u_id", nullable = true)
    private UserEntity uentity;
    @ManyToOne
    @JoinColumn(name = "p_id", nullable = true)
    private PostEntity pentity;
    private String value;


    public CommentEntity() {
    }

    public CommentEntity(Long id, UserEntity uentity, PostEntity pentity, String value) {
        this.id = id;
        this.uentity = uentity;
        this.pentity = pentity;
        this.value = value;
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

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public CommentEntity id(Long id) {
        setId(id);
        return this;
    }

    public CommentEntity uentity(UserEntity uentity) {
        setUentity(uentity);
        return this;
    }

    public CommentEntity pentity(PostEntity pentity) {
        setPentity(pentity);
        return this;
    }

    public CommentEntity value(String value) {
        setValue(value);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CommentEntity)) {
            return false;
        }
        CommentEntity commentEntity = (CommentEntity) o;
        return Objects.equals(id, commentEntity.id) && Objects.equals(uentity, commentEntity.uentity) && Objects.equals(pentity, commentEntity.pentity) && Objects.equals(value, commentEntity.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uentity, pentity, value);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", uentity='" + getUentity() + "'" +
            ", pentity='" + getPentity() + "'" +
            ", value='" + getValue() + "'" +
            "}";
    }

  

}