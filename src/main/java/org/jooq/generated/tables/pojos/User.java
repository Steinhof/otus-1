/*
 * This file is generated by jOOQ.
 */
package org.jooq.generated.tables.pojos;


import java.io.Serializable;

import org.jooq.JSON;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Integer id;
    private final String  login;
    private final String  firstName;
    private final String  lastName;
    private final Integer age;
    private final JSON    interests;
    private final String  city;
    private final String  password;
    private final String  gender;

    public User(User value) {
        this.id = value.id;
        this.login = value.login;
        this.firstName = value.firstName;
        this.lastName = value.lastName;
        this.age = value.age;
        this.interests = value.interests;
        this.city = value.city;
        this.password = value.password;
        this.gender = value.gender;
    }

    public User(
        Integer id,
        String  login,
        String  firstName,
        String  lastName,
        Integer age,
        JSON    interests,
        String  city,
        String  password,
        String  gender
    ) {
        this.id = id;
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.interests = interests;
        this.city = city;
        this.password = password;
        this.gender = gender;
    }

    /**
     * Getter for <code>otus.user.id</code>.
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Getter for <code>otus.user.login</code>.
     */
    public String getLogin() {
        return this.login;
    }

    /**
     * Getter for <code>otus.user.first_name</code>.
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Getter for <code>otus.user.last_name</code>.
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Getter for <code>otus.user.age</code>.
     */
    public Integer getAge() {
        return this.age;
    }

    /**
     * Getter for <code>otus.user.interests</code>.
     */
    public JSON getInterests() {
        return this.interests;
    }

    /**
     * Getter for <code>otus.user.city</code>.
     */
    public String getCity() {
        return this.city;
    }

    /**
     * Getter for <code>otus.user.password</code>.
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Getter for <code>otus.user.gender</code>.
     */
    public String getGender() {
        return this.gender;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("User (");

        sb.append(id);
        sb.append(", ").append(login);
        sb.append(", ").append(firstName);
        sb.append(", ").append(lastName);
        sb.append(", ").append(age);
        sb.append(", ").append(interests);
        sb.append(", ").append(city);
        sb.append(", ").append(password);
        sb.append(", ").append(gender);

        sb.append(")");
        return sb.toString();
    }
}
