package org.closure.app.UserModule.dto;

import java.util.Objects;

public class UserResponse {
    private Long id;
    private String name;
    private String img;

    public UserResponse() {
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

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UserResponse)) {
            return false;
        }
        UserResponse userResponse = (UserResponse) o;
        return Objects.equals(id, userResponse.id) && Objects.equals(name, userResponse.name) && Objects.equals(img, userResponse.img);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, img);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", img='" + getImg() + "'" +
            "}";
    }

}
