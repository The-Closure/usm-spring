package org.closure.app.profsModule.dto;

import java.util.Objects;

public class ProfRequest {
    private String name;
    private String email;
    private String password;
    private String spec;

    public ProfRequest() {
    }

    public ProfRequest(String name, String email, String password, String spec) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.spec = spec;
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

    public ProfRequest name(String name) {
        setName(name);
        return this;
    }

    public ProfRequest email(String email) {
        setEmail(email);
        return this;
    }

    public ProfRequest password(String password) {
        setPassword(password);
        return this;
    }

    public ProfRequest spec(String spec) {
        setSpec(spec);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ProfRequest)) {
            return false;
        }
        ProfRequest profRequest = (ProfRequest) o;
        return Objects.equals(name, profRequest.name) && Objects.equals(email, profRequest.email) && Objects.equals(password, profRequest.password) && Objects.equals(spec, profRequest.spec);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, password, spec);
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            ", password='" + getPassword() + "'" +
            ", spec='" + getSpec() + "'" +
            "}";
    }

}
