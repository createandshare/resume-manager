package com.brand.resumemanager.user;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(unique = true, nullable = false)
	private String userName;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private boolean active;
}
