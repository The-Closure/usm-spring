package org.closure.app.likeModule.dto;

import java.util.Objects;

import org.closure.app.UserModule.dto.UserResponse;


public class LikeResponse {
    Long id;
    UserResponse model;

    public LikeResponse() {
    }

    public LikeResponse(Long id, UserResponse model) {
        this.id = id;
        this.model = model;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserResponse getModel() {
        return this.model;
    }

    public void setModel(UserResponse model) {
        this.model = model;
    }

    public LikeResponse id(Long id) {
        setId(id);
        return this;
    }

    public LikeResponse model(UserResponse model) {
        setModel(model);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof LikeResponse)) {
            return false;
        }
        LikeResponse likeResponse = (LikeResponse) o;
        return Objects.equals(id, likeResponse.id) && Objects.equals(model, likeResponse.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", model='" + getModel() + "'" +
            "}";
    }

}
