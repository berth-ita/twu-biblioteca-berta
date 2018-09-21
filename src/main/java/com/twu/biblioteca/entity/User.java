package com.twu.biblioteca.entity;

import com.twu.biblioteca.exception.InvalidEmailException;
import com.twu.biblioteca.exception.InvalidLibraryNumberException;
import com.twu.biblioteca.exception.InvalidPhoneNumberException;
import static com.twu.biblioteca.util.ConstantUtils.*;

public class User {

    private String libraryNumber;
    private String password;
    private Book bookCheckedOut;
    private Movie movieCheckedOut;
    private String name;
    private String email;
    private String address;
    private Integer phoneNumber;

    public String getLibraryNumber() {
        return libraryNumber;
    }

    public void setLibraryNumber(String libraryNumber) {
        if(!libraryNumber.matches(LIBRARY_NUMBER_FORMAT)) {
            throw new InvalidLibraryNumberException();
        }
        this.libraryNumber = libraryNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Book getBookCheckedOut() {
        return bookCheckedOut;
    }

    public void setBookCheckedOut(Book bookCheckedOut) {
        this.bookCheckedOut = bookCheckedOut;
    }

    public Movie getMovieCheckedOut() {
        return movieCheckedOut;
    }

    public void setMovieCheckedOut(Movie movieCheckedOut) {
        this.movieCheckedOut = movieCheckedOut;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if(!email.matches(EMAIL_FORMAT)) {
            throw new InvalidEmailException();
        }
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        if(!phoneNumber.toString().matches(PHONE_NUMBER_FORMAT)) {
            throw new InvalidPhoneNumberException();
        }
        this.phoneNumber = phoneNumber;
    }
}
