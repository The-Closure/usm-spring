package org.closure.app.commentModule.dto;

import java.util.Objects;

public class CommentRequest {
    private String value;

    public CommentRequest() {
    }

    public CommentRequest(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public CommentRequest value(String value) {
        setValue(value);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CommentRequest)) {
            return false;
        }
        CommentRequest commentRequest = (CommentRequest) o;
        return Objects.equals(value, commentRequest.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public String toString() {
        return "{" +
            " value='" + getValue() + "'" +
            "}";
    }

}
