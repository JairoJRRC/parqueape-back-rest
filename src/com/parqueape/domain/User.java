package com.parqueape.domain;

import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.json.JSONException;
import org.json.JSONObject;

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
	
	@XmlElement(name = "id")
	private Long id;
	@XmlElement(name = "role")
	private EnumRole role;
	@XmlElement(name = "email")
	private String email;
	private String password;
	@XmlElement(name = "isActive")
	private String isActive;
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
