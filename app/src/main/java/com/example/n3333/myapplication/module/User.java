package com.example.n3333.myapplication.module;

import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableField;

public class User extends BaseObservable {

    public String name, email, profileImage, about;

    // profile meta fields are ObservableField, will update the UI
    // whenever a new value is set
    public ObservableField<Long> numberOfFollowers = new ObservableField<>();
    public ObservableField<Long> numberOfPosts = new ObservableField<>();
    public ObservableField<Long> numberOfFollowing = new ObservableField<>();

    public User() {

    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
//       notifyPropertyChanged(B);
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public User setProfileImage(String profileImage) {
        this.profileImage = profileImage;
        return this;
    }

    public String getAbout() {
        return about;
    }

    public User setAbout(String about) {
        this.about = about;
        return this;
    }

    public ObservableField<Long> getNumberOfFollowers() {
        return numberOfFollowers;
    }

    public User setNumberOfFollowers(ObservableField<Long> numberOfFollowers) {
        this.numberOfFollowers = numberOfFollowers;
        return this;
    }

    public ObservableField<Long> getNumberOfPosts() {
        return numberOfPosts;
    }

    public User setNumberOfPosts(ObservableField<Long> numberOfPosts) {
        this.numberOfPosts = numberOfPosts;
        return this;
    }

    public ObservableField<Long> getNumberOfFollowing() {
        return numberOfFollowing;
    }

    public User setNumberOfFollowing(ObservableField<Long> numberOfFollowing) {
        this.numberOfFollowing = numberOfFollowing;
        return this;
    }
}
