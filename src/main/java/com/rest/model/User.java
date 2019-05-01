package com.rest.model;

public class User {

    private int id;
    private String name;
    private String gender;
    private String dateOfBirth;
    private String email;
    private String phone;
    private String website;
    private String address;
    private String status;
    private String link;
    private String avatarLink;

    public User() {
    }

    public User(int id, String name, String gender, String dateOfBirth, String email, String phone, String website,
                String address, String status, String link, String avatarLink) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phone = phone;
        this.website = website;
        this.address = address;
        this.status = status;
        this.link = link;
        this.avatarLink = avatarLink;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getAvatarLink() {
        return avatarLink;
    }

    public void setAvatarLink(String avatarLink) {
        this.avatarLink = avatarLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.getId() && name.equals(user.getName()) && gender.equals(user.getGender())
                && dateOfBirth.equals(user.getDateOfBirth()) && email.equals(user.getEmail())
                && phone.equals(user.getPhone()) && website.equals(user.getWebsite()) && address.equals(user.getAddress())
                && status.equals(user.getStatus()) && link.equals(user.getLink()) && avatarLink.equals(user.getAvatarLink());
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + id;
        result = 31 * result + name.hashCode();
        result = 31 * result + gender.hashCode();
        result = 31 * result + dateOfBirth.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + phone.hashCode();
        result = 31 * result + website.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + link.hashCode();
        result = 31 * result + avatarLink.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return String.format("\nUser: [\n\t\tId - %s\n\t\tName - %s\n\t\tGender - %s\n\t\tDate of birth - %s\n\t\tEmail - %s\n\t\t" +
                        "Phone - %s\n\t\tWebsite - %s\n\t\tAddress - %s\n\t\tStatus - %s\n\t\tLink - %s\n\t\tAvatar - %s\t\t]",
                id, name, gender, dateOfBirth, email, phone, website, address, status, link, avatarLink);
    }
}
