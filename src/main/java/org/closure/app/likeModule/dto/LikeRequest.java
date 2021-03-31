package org.closure.app.likeModule.dto;

import java.util.Objects;

public class LikeRequest {
    Long userID;
    Long postID;

    public LikeRequest() {
    }

    public LikeRequest(Long userID, Long postID) {
        this.userID = userID;
        this.postID = postID;
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

    public LikeRequest userID(Long userID) {
        setUserID(userID);
        return this;
    }

    public LikeRequest postID(Long postID) {
        setPostID(postID);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof LikeRequest)) {
            return false;
        }
        LikeRequest likeRequest = (LikeRequest) o;
        return Objects.equals(userID, likeRequest.userID) && Objects.equals(postID, likeRequest.postID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userID, postID);
    }

    @Override
    public String toString() {
        return "{" +
            " userID='" + getUserID() + "'" +
            ", postID='" + getPostID() + "'" +
            "}";
    }

}
