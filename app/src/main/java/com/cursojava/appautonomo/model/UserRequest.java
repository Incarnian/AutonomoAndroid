package com.cursojava.appautonomo.model;

public class UserRequest {
    private String email;
    private String name;
    private String password;

    private UserRequest(Builder builder) {
        this.email = builder.email;
        this.name = builder.name;
        this.password = builder.password;
    }

    public static class Builder {
        private String email;
        private String name;
        private String password;

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public UserRequest build() {
            return new UserRequest(this);
        }

    }


}
