package com.tmh.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToMany(mappedBy = "user")
	private List<Order> orders;
	
	@Column(name = "full_name")
	@NotEmpty
	private String fullName;
	
	@Column(name = "email")
	@NotEmpty
	@Email
	private String email;
	
	@Column(name = "password")
	@NotEmpty
	@Size(min = 5, max = 30)
	private String password;
	
	@Column(name = "phone")
	@NotEmpty
	private String phone;
	
	@Column(name = "address")
	@NotEmpty
	private String address;
	
	@Column(name = "role")
	@NotNull
	@Min(0)
	@Max(1)
	private Integer role;
	
	@Column(name = "created_at")
	@CreationTimestamp
	private LocalDateTime createDateTime;
	
	@Column(name = "updated_at")
	@UpdateTimestamp
	private LocalDateTime updateDateTime;
	
	public String getRoleString() {
		if (this.role == 1) {
			return "ADMIN";
		} else {
			return "USER";
		}
	}
	
}