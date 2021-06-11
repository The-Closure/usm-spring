package org.closure.app.UserModule.dto;

import java.util.Objects;

import org.closure.app.CommunityModule.models.CommunityModel;

public class UserResponse {
    private Long id;
    private String name;
    private String img;
    private CommunityModel communinty;

    public UserResponse() {
    }

    public UserResponse(Long id, String name, String img, CommunityModel communinty) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.communinty = communinty;
    }
    public UserResponse(Long id, String name, String img) {
        this.id = id;
        this.name = name;
        this.img = img;
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

    public String getImg() {
        return this.img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public CommunityModel getCommuninty() {
        return this.communinty;
    }

    public void setCommuninty(CommunityModel communinty) {
        this.communinty = communinty;
    }

    public UserResponse id(Long id) {
        setId(id);
        return this;
    }

    public UserResponse name(String name) {
        setName(name);
        return this;
    }

    public UserResponse img(String img) {
        setImg(img);
        return this;
    }

    public UserResponse communinty(CommunityModel communinty) {
        setCommuninty(communinty);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UserResponse)) {
            return false;
        }
        UserResponse userResponse = (UserResponse) o;
        return Objects.equals(id, userResponse.id) && Objects.equals(name, userResponse.name) && Objects.equals(img, userResponse.img) && Objects.equals(communinty, userResponse.communinty);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, img, communinty);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", img='" + getImg() + "'" +
            ", communinty='" + getCommuninty() + "'" +
            "}";
    }
    
}
