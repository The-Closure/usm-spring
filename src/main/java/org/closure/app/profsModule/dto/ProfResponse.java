package org.closure.app.profsModule.dto;

import java.util.Date;
import java.util.Objects;



public class ProfResponse {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String spec;
    private Date created_at;
    private String image;

    public ProfResponse() {
    }

    public ProfResponse(Long id, String name, String email, String password, String spec, Date created_at, String image) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.spec = spec;
        this.created_at = created_at;
        this.image = image;
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

    public String getSpec() {
        return this.spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public Date getCreated_at() {
        return this.created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ProfResponse id(Long id) {
        setId(id);
        return this;
    }

    public ProfResponse name(String name) {
        setName(name);
        return this;
    }

    public ProfResponse email(String email) {
        setEmail(email);
        return this;
    }

    public ProfResponse password(String password) {
        setPassword(password);
        return this;
    }

    public ProfResponse spec(String spec) {
        setSpec(spec);
        return this;
    }

    public ProfResponse created_at(Date created_at) {
        setCreated_at(created_at);
        return this;
    }

    public ProfResponse image(String image) {
        setImage(image);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ProfResponse)) {
            return false;
        }
        ProfResponse profResponse = (ProfResponse) o;
        return Objects.equals(id, profResponse.id) && Objects.equals(name, profResponse.name) && Objects.equals(email, profResponse.email) && Objects.equals(password, profResponse.password) && Objects.equals(spec, profResponse.spec) && Objects.equals(created_at, profResponse.created_at) && Objects.equals(image, profResponse.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password, spec, created_at, image);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            ", password='" + getPassword() + "'" +
            ", spec='" + getSpec() + "'" +
            ", created_at='" + getCreated_at() + "'" +
            ", image='" + getImage() + "'" +
            "}";
    }

}
