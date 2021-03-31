package org.closure.app.postModule.dto;

import java.util.Objects;

public class PostRequest {
    private String title;
    private String value;
    private String attach;

    public PostRequest() {
    }

    public PostRequest(String title, String value, String attach) {
        this.title = title;
        this.value = value;
        this.attach = attach;
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

    public PostRequest title(String title) {
        setTitle(title);
        return this;
    }

    public PostRequest value(String value) {
        setValue(value);
        return this;
    }

    public PostRequest attach(String attach) {
        setAttach(attach);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PostRequest)) {
            return false;
        }
        PostRequest postRequest = (PostRequest) o;
        return Objects.equals(title, postRequest.title) && Objects.equals(value, postRequest.value) && Objects.equals(attach, postRequest.attach);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, value, attach);
    }

    @Override
    public String toString() {
        return "{" +
            " title='" + getTitle() + "'" +
            ", value='" + getValue() + "'" +
            ", attach='" + getAttach() + "'" +
            "}";
    }

    
}
