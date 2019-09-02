package com.finance.entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.hateoas.Identifiable;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "accounts")
@EntityListeners(AuditingEntityListener.class)
public class Account implements Identifiable<Long> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	private String name;
	
	@NotNull
	private int priority;
	
	@NotNull
	private String owner;
	
	private @Version @JsonIgnore Long version;

	private @ManyToOne UserApp user;

	private Account() {}

	public Account(String name, int priority, String owner, UserApp user) {
		this.name = name;
		this.priority = priority;
		this.owner = owner;
		this.user = user;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Account account = (Account) o;
		return Objects.equals(id, account.id) &&
			Objects.equals(name, account.name) &&
			Objects.equals(priority, account.priority) &&
			Objects.equals(owner, account.owner) &&
			Objects.equals(version, account.version) &&
			Objects.deepEquals(user, account.user);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, name, priority, owner, version, user);
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

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
	
	public UserApp getUser() {
		return user;
	}

	public void setUser(UserApp user) {
		this.user = user;
	}
	
	@Override
	public String toString() {
		return "Account{" +
			"id=" + id +
			", name='" + name + '\'' +
			", priority='" + priority + '\'' +
			", owner='" + owner + '\'' +
			", version=" + version +
			", user=" + user +
			'}';
	}
}
