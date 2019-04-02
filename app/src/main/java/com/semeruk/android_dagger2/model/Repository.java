package com.semeruk.android_dagger2.model;

import com.google.gson.annotations.SerializedName;

public class Repository {

    @SerializedName("name")
    String name;

    @SerializedName("full_name")
    String fullName;

    @SerializedName("description")
    String description;

    public Repository(String name, String fullName, String description) {
        this.name = name;
        this.fullName = fullName;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return name + ", " + fullName + ", " + description;
    }
}