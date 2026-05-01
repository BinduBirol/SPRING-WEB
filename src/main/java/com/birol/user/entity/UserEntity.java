package com.birol.user.entity;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    private UserRoles role;

    private Boolean isTwoFactorEnabled;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] image;

    @Transient
    private MultipartFile imageMultipart;

    @Transient
    private String fullName;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UserDeviceIpsEntity> devices;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn;

    // ------------------------
    // Lifecycle Hooks
    // ------------------------
    @PrePersist
    protected void onCreate() {
        this.createdOn = new Date();
        this.updatedOn = this.createdOn;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedOn = new Date();
    }

    // ------------------------
    // Business Helper
    // ------------------------
    public String getFullName() {
        return firstName + " " + lastName;
    }

    // ------------------------
    // Getters & Setters
    // ------------------------
    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRoles getRole() {
        return role;
    }

    public void setRole(UserRoles role) {
        this.role = role;
    }

    public Boolean getIsTwoFactorEnabled() {
        return isTwoFactorEnabled;
    }

    public void setIsTwoFactorEnabled(Boolean isTwoFactorEnabled) {
        this.isTwoFactorEnabled = isTwoFactorEnabled;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public MultipartFile getImageMultipart() {
        return imageMultipart;
    }

    public void setImageMultipart(MultipartFile imageMultipart) {
        this.imageMultipart = imageMultipart;
    }

    public List<UserDeviceIpsEntity> getDevices() {
        return devices;
    }

    public void setDevices(List<UserDeviceIpsEntity> devices) {
        this.devices = devices;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }
}