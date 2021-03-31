package org.closure.app.commentModule.dto;

import java.util.Objects;

public class CommentResponse {
    private Long id;
    private String value;
    private Long userID;
    private Long postID;

    public CommentResponse() {
    }

    public CommentResponse(Long id, String value, Long userID, Long postID) {
        this.id = id;
        this.value = value;
        this.userID = userID;
        this.postID = postID;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getUserID() {
        return this.userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getPostID() {
        return this.postID;
    }

    public void setPostID(Long postID) {
        this.postID = postID;
    }

    public CommentResponse id(Long id) {
        setId(id);
        return this;
    }

    public CommentResponse value(String value) {
        setValue(value);
        return this;
    }

    public CommentResponse userID(Long userID) {
        setUserID(userID);
        return this;
    }

    public CommentResponse postID(Long postID) {
        setPostID(postID);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CommentResponse)) {
            return false;
        }
        CommentResponse commentResponse = (CommentResponse) o;
        return Objects.equals(id, commentResponse.id) && Objects.equals(value, commentResponse.value) && Objects.equals(userID, commentResponse.userID) && Objects.equals(postID, commentResponse.postID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, userID, postID);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", value='" + getValue() + "'" +
            ", userID='" + getUserID() + "'" +
            ", postID='" + getPostID() + "'" +
            "}";
    }

}
