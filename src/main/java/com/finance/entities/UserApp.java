package com.finance.entities;

import java.util.Arrays;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email", name = "user_uk"))
public class UserApp {

	public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

	private @Id @GeneratedValue Long id;

	@NotEmpty
	private String name;
	
	@Email(message = "Email should be valid")
	private String email;

	@NotEmpty
	private @JsonIgnore String password;

	private String[] roles;

	public void setPassword(String password) {
		this.password = PASSWORD_ENCODER.encode(password);
	}

	protected UserApp() {
		this.roles = new String[] {"ROLE_USER"};
	}

	public UserApp(String name, String email, String password, String... roles) {

		this.name = name;
		this.email = email;
		this.setPassword(password);
		this.roles = roles;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserApp user = (UserApp) o;
		return Objects.equals(id, user.id) &&
			Objects.equals(name, user.name) &&
			Objects.equals(email, user.email) &&			
			Objects.equals(password, user.password) &&
			Arrays.equals(roles, user.roles);
	}

	@Override
	public int hashCode() {

		int result = Objects.hash(id, name, email, password);
		result = 31 * result + Arrays.hashCode(roles);
		return result;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
		this.email = email;
	}
	public String getPassword() {
		return password;
	}

	public String[] getRoles() {
		return roles;
	}

	public void setRoles(String[] roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User{" +
			"id=" + id +
			", name='" + name + '\'' +
			", email='" + email + '\'' +
			", roles=" + Arrays.toString(roles) +
			'}';
	}
}