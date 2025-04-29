package com.birol.user.entity;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "users_devices")
public class UserDeviceIpsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private UserEntity user; // Link to UserEntity

	private String ipAddress;
	private String deviceName; // Optionally track device name (e.g., browser, mobile device)
	private boolean isActive; // Track if the IP/device is currently active

	@Temporal(TemporalType.TIMESTAMP)
	private Date firstLogin;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLogin;

	@PrePersist
	protected void onCreate() {
		if (this.firstLogin == null) {
			this.firstLogin = new Date();
		}
		this.lastLogin = this.firstLogin;
	}

	@PreUpdate
	protected void onUpdate() {
		this.lastLogin = new Date(); // Update the last login timestamp
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Date getFirstLogin() {
		return firstLogin;
	}

	public void setFirstLogin(Date firstLogin) {
		this.firstLogin = firstLogin;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

}
