package com.parqueape.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.json.JSONException;
import org.json.JSONObject;


@Entity
@Table(name = "TB_USER")
@XmlRootElement
public class User {
	
	public User() {
	}	

	public static User create(
		EnumRole role,
		String email,
		String password,
		Date createAt
	) {
		User obj = new User();
		obj.role = role;
		obj.email = email;
		obj.password = password;
		obj.isActive = "1";
		obj.createAt = createAt;
		return obj;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@XmlElement(name = "id")
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@XmlElement(name = "role")
	private EnumRole role;
	
	@Column(length=50)
	@XmlElement(name = "email")
	private String email;
	
	@Column(length=50)
	private String password;
	
	@Column(length=1, columnDefinition="CHAR")
	@XmlElement(name = "isActive")
	private String isActive;
	
	@Temporal(TemporalType.TIMESTAMP)
	@XmlElement(name = "createAt")
	private Date createAt;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EnumRole getRole() {
		return role;
	}

	public void setRole(EnumRole role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
    
    @Override
    public String toString() {
            try {
            	
            	JSONObject obj = new JSONObject();
            	
                obj.put("id", id);
                obj.put("role", role);
                obj.put("email", email);
                obj.put("isActive", isActive);
                obj.put("createAt", createAt.toString());
                
                    return new JSONObject().put("data", obj).toString();
            } catch (JSONException e) {
                    return null;
            }
    }
}
