package life.pifrans.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;

@MappedSuperclass
public abstract class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "Campo obrigatório!")
	private String name;
	private String lastName;
	private String gender;

	@NotNull(message = "Campo obrigatório!")
	@Length(min = 5, max = 120, message = "A idade deve ser entre 5 e 120 anos!")
	private Integer age;

	@NotEmpty(message = "Campo obrigatório!")
	@Email(message = "E-mail inválido!")
	@Column(unique = true)
	private String email;

	@JsonIgnore
	private String password;

	@Column(name = "current_access")
	@Temporal(TemporalType.TIMESTAMP)
	private Date currentAccess;

	@Column(name = "last_access")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastAccess;

	@Column(name = "is_active")
	private boolean isActive;

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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
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

	public Date getCurrentAccess() {
		return currentAccess;
	}

	public void setCurrentAccess(Date currentAccess) {
		this.currentAccess = currentAccess;
	}

	public Date getLastAccess() {
		return lastAccess;
	}

	public void setLastAccess(Date lastAccess) {
		this.lastAccess = lastAccess;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
