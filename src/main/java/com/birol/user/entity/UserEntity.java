package com.birol.user.entity;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "users")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	@Transient
	private String fullName;

	@Column(nullable = false)
	private String email;

	@Enumerated(EnumType.STRING)
	private UserRoles role;

	private Boolean isTwoFactorEnabled;

	@Transient
	private MultipartFile imageMultipart;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] image;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<UserDeviceIpsEntity> devices;

	// Timestamps for created and updated time
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedOn;

	@PrePersist
	protected void onCreate() {
		this.createdOn = new Date();
		this.updatedOn = this.createdOn; // Set updatedOn to createdOn at the time of creation
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedOn = new Date(); // Update updatedOn at the time of update
	}

	// Getters and setters (or Lombok annotations for automatic generation)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public List<UserDeviceIpsEntity> getDevices() {
		return devices;
	}

	public void setDevices(List<UserDeviceIpsEntity> devices) {
		this.devices = devices;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getFullName() {

		fullName = firstName + " " + lastName;

		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

}
