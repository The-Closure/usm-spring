package org.closure.app.boardModule.models;

import java.util.Objects;

public class BoardModel {
    private Long id;
    private String name;
    private String image;
    private String description;

    public BoardModel() {
    }

    public BoardModel(Long id, String name, String image, String description) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
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

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BoardModel id(Long id) {
        setId(id);
        return this;
    }

    public BoardModel name(String name) {
        setName(name);
        return this;
    }

    public BoardModel image(String image) {
        setImage(image);
        return this;
    }

    public BoardModel description(String description) {
        setDescription(description);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof BoardModel)) {
            return false;
        }
        BoardModel boardModel = (BoardModel) o;
        return Objects.equals(id, boardModel.id) && Objects.equals(name, boardModel.name) && Objects.equals(image, boardModel.image) && Objects.equals(description, boardModel.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, image, description);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", image='" + getImage() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }

}
