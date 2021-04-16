package org.closure.app.CommunityModule.dto;

import java.util.List;
import java.util.Objects;

import org.closure.app.UserModule.dto.UserResponse;

public class CommunityResponse {
    private String name;
    private String description;
    private String img;
    private List<UserResponse> users;


    public CommunityResponse() {
    }

    public CommunityResponse(String name, String description, String img, List<UserResponse> users) {
        this.name = name;
        this.description = description;
        this.img = img;
        this.users = users;
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

    public List<UserResponse> getUsers() {
        return this.users;
    }

    public void setUsers(List<UserResponse> users) {
        this.users = users;
    }

    public CommunityResponse name(String name) {
        setName(name);
        return this;
    }

    public CommunityResponse description(String description) {
        setDescription(description);
        return this;
    }

    public CommunityResponse img(String img) {
        setImg(img);
        return this;
    }

    public CommunityResponse users(List<UserResponse> users) {
        setUsers(users);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CommunityResponse)) {
            return false;
        }
        CommunityResponse communityResponse = (CommunityResponse) o;
        return Objects.equals(name, communityResponse.name) && Objects.equals(description, communityResponse.description) && Objects.equals(img, communityResponse.img) && Objects.equals(users, communityResponse.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, img, users);
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", img='" + getImg() + "'" +
            ", users='" + getUsers() + "'" +
            "}";
    }

    
}
