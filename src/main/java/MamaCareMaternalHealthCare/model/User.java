package MamaCareMaternalHealthCare.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.JoinTable;
import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table (name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(unique = true)
    private String email;

    @JsonIgnore
    private String password;

    @Enumerated(EnumType.STRING)
    private EUserType role;

    private String phoneNumber;

    // Many Users to one location
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    // one user to one profile
    @OneToOne(mappedBy = "user")
    @JsonManagedReference
    private Profile profile;

    //Doctor Many to Many Specialization
    @ManyToMany
    @JoinTable(
        name = "doctor_specialization",
        joinColumns = @JoinColumn(name = "doctor_id"),
        inverseJoinColumns = @JoinColumn(name = "specialization_id")
    )
    private List<Specialization> specializations = new ArrayList<>();

    // Getters and Setters

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
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
    public EUserType getRole() {
        return role;
    }
    public void setRole(EUserType role) {
        this.role = role;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }
    public Profile getProfile() {
        return profile;
    }
    public void setProfile(Profile profile) {
        this.profile = profile;
    }
    public List<Specialization> getSpecializations() {
        return specializations;
    }
    public void setSpecializations(List<Specialization> specializations) {
        this.specializations = specializations;
    }

}
