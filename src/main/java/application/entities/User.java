package application.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "jostroffOHI")
//@Component //Newly commented after Baeldung JPA Entity.
@AllArgsConstructor
public @Getter @Setter class User {
    
    @Id
//  @GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue
	@Column(name = "id")
    private long id;
    
	@Column(name = "name")
    private final String name;
	
	@Column(name = "email")
    private final String email;
    
    public User() {
        this.name = "";
        this.email = "";
    }
    
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
    
    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", email=" + email + '}';
    }
}
