package org.closure.app.postModule.dto;

import java.util.Objects;

import org.closure.app.UserModule.dto.UserResponse;

public class PostResponse {
    private Long postID;
    private Long userID; 
    private UserResponse uEntity;
    private Long communityID;
    private String title;
    private String value;
    private String attach; 


    public PostResponse() {
    }

    public PostResponse(Long postID, Long userID, UserResponse uEntity, Long communityID, String title, String value, String attach) {
        this.postID = postID;
        this.userID = userID;
        this.uEntity = uEntity;
        this.communityID = communityID;
        this.title = title;
        this.value = value;
        this.attach = attach;
    }

    public Long getPostID() {
        return this.postID;
    }

    public void setPostID(Long postID) {
        this.postID = postID;
    }

    public Long getUserID() {
        return this.userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public UserResponse getUEntity() {
        return this.uEntity;
    }

    public void setUEntity(UserResponse uEntity) {
        this.uEntity = uEntity;
    }

    public Long getCommunityID() {
        return this.communityID;
    }

    public void setCommunityID(Long communityID) {
        this.communityID = communityID;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getAttach() {
        return this.attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public PostResponse postID(Long postID) {
        setPostID(postID);
        return this;
    }

    public PostResponse userID(Long userID) {
        setUserID(userID);
        return this;
    }

    public PostResponse uEntity(UserResponse uEntity) {
        setUEntity(uEntity);
        return this;
    }

    public PostResponse communityID(Long communityID) {
        setCommunityID(communityID);
        return this;
    }

    public PostResponse title(String title) {
        setTitle(title);
        return this;
    }

    public PostResponse value(String value) {
        setValue(value);
        return this;
    }

    public PostResponse attach(String attach) {
        setAttach(attach);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PostResponse)) {
            return false;
        }
        PostResponse postResponse = (PostResponse) o;
        return Objects.equals(postID, postResponse.postID) && Objects.equals(userID, postResponse.userID) && Objects.equals(uEntity, postResponse.uEntity) && Objects.equals(communityID, postResponse.communityID) && Objects.equals(title, postResponse.title) && Objects.equals(value, postResponse.value) && Objects.equals(attach, postResponse.attach);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postID, userID, uEntity, communityID, title, value, attach);
    }

    @Override
    public String toString() {
        return "{" +
            " postID='" + getPostID() + "'" +
            ", userID='" + getUserID() + "'" +
            ", uEntity='" + getUEntity() + "'" +
            ", communityID='" + getCommunityID() + "'" +
            ", title='" + getTitle() + "'" +
            ", value='" + getValue() + "'" +
            ", attach='" + getAttach() + "'" +
            "}";
    }
    

}
