package org.closure.app.entities;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
@Entity
@Table(name = "profs")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class ProfsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String spec;
    private Date created_at;
    private String image;
    @ManyToMany(mappedBy = "profs")
    private List<BoardEntity> boards;

 

    public ProfsEntity() {
    }

    public ProfsEntity(Long id, String name, String email, String password, String spec, Date created_at, String image, List<BoardEntity> boards) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.spec = spec;
        this.created_at = created_at;
        this.image = image;
        this.boards = boards;
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

    public List<BoardEntity> getBoards() {
        return this.boards;
    }

    public void setBoards(List<BoardEntity> boards) {
        this.boards = boards;
    }

    public ProfsEntity id(Long id) {
        setId(id);
        return this;
    }

    public ProfsEntity name(String name) {
        setName(name);
        return this;
    }

    public ProfsEntity email(String email) {
        setEmail(email);
        return this;
    }

    public ProfsEntity password(String password) {
        setPassword(password);
        return this;
    }

    public ProfsEntity spec(String spec) {
        setSpec(spec);
        return this;
    }

    public ProfsEntity created_at(Date created_at) {
        setCreated_at(created_at);
        return this;
    }

    public ProfsEntity image(String image) {
        setImage(image);
        return this;
    }

    public ProfsEntity boards(List<BoardEntity> boards) {
        setBoards(boards);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ProfsEntity)) {
            return false;
        }
        ProfsEntity profsEntity = (ProfsEntity) o;
        return Objects.equals(id, profsEntity.id) && Objects.equals(name, profsEntity.name) && Objects.equals(email, profsEntity.email) && Objects.equals(password, profsEntity.password) && Objects.equals(spec, profsEntity.spec) && Objects.equals(created_at, profsEntity.created_at) && Objects.equals(image, profsEntity.image) && Objects.equals(boards, profsEntity.boards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password, spec, created_at, image, boards);
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
            ", boards='" + getBoards() + "'" +
            "}";
    }

}