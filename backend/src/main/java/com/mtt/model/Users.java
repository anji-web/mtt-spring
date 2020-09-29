package com.mtt.model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.*;


@Entity
@NamedQueries(
        value = {

                @NamedQuery(name = "Users.findByAllUsers",query = "select u from Users u")
        }
)
@JsonRootName("Users")
public class Users extends BaseModel {



    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @Id
    private int id;

    @Size(max = 100)
    @Column(name = "email")
    @JsonProperty("email")
    private String email;
    @Size(max = 100)
    private String password;
    @Size(max = 20)
    private String status;

    private boolean isActive;

    @Column(name = "register_date")
    private Date registerDate;

    @OneToOne(targetEntity = Organizations.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "organization_id")
    private Organizations organization;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Roles> role = new HashSet<>();

    @OneToOne( mappedBy = "user")
    @JsonManagedReference
    @JsonProperty("user-profile")
    private UserProfiles userProfile ;


    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getRegisterDate() {

        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Organizations getOrganization() {
        return organization;
    }

    public void setOrganization(Organizations organization) {
        this.organization = organization;
    }

    public Set<Roles> getRole() {
        return role;
    }

    public void setRole(Set<Roles> role) {
        this.role = role;
    }

    public UserProfiles getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfiles userProfile) {
        this.userProfile = userProfile;
    }
}
