package org.closure.app.CommunityModule.dto;

import java.util.Objects;

public class CommunityResponse {
    private String name;
    private String description;
    private String img;

    public CommunityResponse() {
    }

    public CommunityResponse(String name, String description, String img) {
        this.name = name;
        this.description = description;
        this.img = img;
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

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CommunityResponse)) {
            return false;
        }
        CommunityResponse communityResponse = (CommunityResponse) o;
        return Objects.equals(name, communityResponse.name) && Objects.equals(description, communityResponse.description) && Objects.equals(img, communityResponse.img);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, img);
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", img='" + getImg() + "'" +
            "}";
    }
    
}
