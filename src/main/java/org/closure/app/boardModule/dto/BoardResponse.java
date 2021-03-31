package org.closure.app.boardModule.dto;

import java.util.Objects;

public class BoardResponse {
    private Long id;
    private String name;
    private String image;
    private String description;

    public BoardResponse() {
    }

    public BoardResponse(Long id, String name, String image, String description) {
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

    public BoardResponse id(Long id) {
        setId(id);
        return this;
    }

    public BoardResponse name(String name) {
        setName(name);
        return this;
    }

    public BoardResponse image(String image) {
        setImage(image);
        return this;
    }

    public BoardResponse description(String description) {
        setDescription(description);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof BoardResponse)) {
            return false;
        }
        BoardResponse boardResponse = (BoardResponse) o;
        return Objects.equals(id, boardResponse.id) && Objects.equals(name, boardResponse.name) && Objects.equals(image, boardResponse.image) && Objects.equals(description, boardResponse.description);
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
