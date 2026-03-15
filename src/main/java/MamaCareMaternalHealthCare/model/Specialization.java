package MamaCareMaternalHealthCare.model;

//import java.util.Set;
import java.util.UUID;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="specialization")
public class Specialization {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;

    @ManyToMany(mappedBy = "specializations")
    private List<User> doctors;
    @JsonIgnore

    // Getters and Setters
    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<User> getDoctors() {
        return doctors;
    }
    public void setDoctors(List<User> doctors) {
        this.doctors = doctors;
    }
    
    @ManyToMany(mappedBy = "specializations")
    private List<User> users;
    public List<User> getUsers() {
        return users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }

}
