package org.closure.app.likeModule.models;

import java.util.Objects;

public class LikeModel {
    Long userID;
    Long postID;

    public LikeModel() {
    }

    public LikeModel(Long userID, Long postID) {
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

    public LikeModel userID(Long userID) {
        setUserID(userID);
        return this;
    }

    public LikeModel postID(Long postID) {
        setPostID(postID);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof LikeModel)) {
            return false;
        }
        LikeModel likeModel = (LikeModel) o;
        return Objects.equals(userID, likeModel.userID) && Objects.equals(postID, likeModel.postID);
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
