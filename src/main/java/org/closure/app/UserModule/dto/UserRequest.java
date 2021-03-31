package org.closure.app.UserModule.dto;

import java.util.Objects;

public class UserRequest {
    private String name;
    private String email;
    private String password;
    private Long community;

    public UserRequest() {
    }

    public UserRequest(String name, String email, String password, Long community) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.community = community;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getCommunity() {
        return this.community;
    }

    public void setCommunity(Long community) {
        this.community = community;
    }

    public UserRequest name(String name) {
        setName(name);
        return this;
    }

    public UserRequest email(String email) {
        setEmail(email);
        return this;
    }

    public UserRequest password(String password) {
        setPassword(password);
        return this;
    }

    public UserRequest community(Long community) {
        setCommunity(community);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UserRequest)) {
            return false;
        }
        UserRequest userRequest = (UserRequest) o;
        return Objects.equals(name, userRequest.name) && Objects.equals(email, userRequest.email) && Objects.equals(password, userRequest.password) && Objects.equals(community, userRequest.community);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, password, community);
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            ", password='" + getPassword() + "'" +
            ", community='" + getCommunity() + "'" +
            "}";
    }

}
