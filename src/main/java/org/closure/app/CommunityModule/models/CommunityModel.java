package org.closure.app.CommunityModule.models;

import java.util.Objects;

public class CommunityModel {
    private Long id;
    private String name;
    private String description;
    private String img;

    public CommunityModel() {
    }

    public CommunityModel(Long id, String name, String description, String img) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    public CommunityModel id(Long id) {
        setId(id);
        return this;
    }

    public CommunityModel name(String name) {
        setName(name);
        return this;
    }

    public CommunityModel description(String description) {
        setDescription(description);
        return this;
    }

    public CommunityModel img(String img) {
        setImg(img);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CommunityModel)) {
            return false;
        }
        CommunityModel communityModel = (CommunityModel) o;
        return Objects.equals(id, communityModel.id) && Objects.equals(name, communityModel.name) && Objects.equals(description, communityModel.description) && Objects.equals(img, communityModel.img);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, img);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", img='" + getImg() + "'" +
            "}";
    }

    
}
