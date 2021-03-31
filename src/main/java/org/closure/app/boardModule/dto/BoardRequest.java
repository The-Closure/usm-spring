package org.closure.app.boardModule.dto;

import java.util.Objects;

public class BoardRequest {
    private Long id;
    private String name;

    public BoardRequest() {
    }

    public BoardRequest(Long id, String name) {
        this.id = id;
        this.name = name;
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

    public BoardRequest id(Long id) {
        setId(id);
        return this;
    }

    public BoardRequest name(String name) {
        setName(name);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof BoardRequest)) {
            return false;
        }
        BoardRequest boardRequest = (BoardRequest) o;
        return Objects.equals(id, boardRequest.id) && Objects.equals(name, boardRequest.name);
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
