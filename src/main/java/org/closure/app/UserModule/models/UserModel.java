package org.closure.app.UserModule.models;

import java.util.Date;
import java.util.Objects;


public class UserModel {

    private Long id ;
    private String name ;
    private String email ;
    private String password ;
    private String university ;
    private String img ;
    private String community_name ;
    private Integer  study_year ;
    private Date  start_year ;
    private Date  age ;

    public UserModel() {
    }

    public UserModel(Long id, String name, String email, String password, String university, String img, String community_name, Integer study_year, Date start_year, Date age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.university = university;
        this.img = img;
        this.community_name = community_name;
        this.study_year = study_year;
        this.start_year = start_year;
        this.age = age;
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

    public String getUniversity() {
        return this.university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getImg() {
        return this.img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCommunity_name() {
        return this.community_name;
    }

    public void setCommunity_name(String community_name) {
        this.community_name = community_name;
    }

    public Integer getStudy_year() {
        return this.study_year;
    }

    public void setStudy_year(Integer study_year) {
        this.study_year = study_year;
    }

    public Date getStart_year() {
        return this.start_year;
    }

    public void setStart_year(Date start_year) {
        this.start_year = start_year;
    }

    public Date getAge() {
        return this.age;
    }

    public void setAge(Date age) {
        this.age = age;
    }

    public UserModel id(Long id) {
        setId(id);
        return this;
    }

    public UserModel name(String name) {
        setName(name);
        return this;
    }

    public UserModel email(String email) {
        setEmail(email);
        return this;
    }

    public UserModel password(String password) {
        setPassword(password);
        return this;
    }

    public UserModel university(String university) {
        setUniversity(university);
        return this;
    }

    public UserModel img(String img) {
        setImg(img);
        return this;
    }

    public UserModel community_name(String community_name) {
        setCommunity_name(community_name);
        return this;
    }

    public UserModel study_year(Integer study_year) {
        setStudy_year(study_year);
        return this;
    }

    public UserModel start_year(Date start_year) {
        setStart_year(start_year);
        return this;
    }

    public UserModel age(Date age) {
        setAge(age);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UserModel)) {
            return false;
        }
        UserModel userModel = (UserModel) o;
        return Objects.equals(id, userModel.id) && Objects.equals(name, userModel.name) && Objects.equals(email, userModel.email) && Objects.equals(password, userModel.password) && Objects.equals(university, userModel.university) && Objects.equals(img, userModel.img) && Objects.equals(community_name, userModel.community_name) && Objects.equals(study_year, userModel.study_year) && Objects.equals(start_year, userModel.start_year) && Objects.equals(age, userModel.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, password, university, img, community_name, study_year, start_year, age);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            ", password='" + getPassword() + "'" +
            ", university='" + getUniversity() + "'" +
            ", img='" + getImg() + "'" +
            ", community_name='" + getCommunity_name() + "'" +
            ", study_year='" + getStudy_year() + "'" +
            ", start_year='" + getStart_year() + "'" +
            ", age='" + getAge() + "'" +
            "}";
    }

}