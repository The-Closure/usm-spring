package org.closure.app.CommunityModule.dto;

import java.util.Objects;

public class CommunityRequest {
    private int id;
    private String name;    

    public CommunityRequest() {
    }

    public CommunityRequest(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CommunityRequest id(int id) {
        setId(id);
        return this;
    }

    public CommunityRequest name(String name) {
        setName(name);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CommunityRequest)) {
            return false;
        }
        CommunityRequest communityRequest = (CommunityRequest) o;
        return id == communityRequest.id && Objects.equals(name, communityRequest.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            "}";
    }

}
