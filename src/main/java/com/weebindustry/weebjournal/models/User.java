package com.weebindustry.weebjournal.models;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;

/**
 * The warehouse to store every user that exist in weebjournal
 * 
 * 
 * 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
@JsonIgnoreProperties(value = {"password"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    
    @Column(name = "username")
    private String username;

    
    @Column(name = "password")
    private String password;

    @Column(name = "joined_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date joinedDate;

    @Column(name = "email")
    private String email;

    @Column(name = "biography")
    private String biography;

    @Column(name = "displayname")
    private String displayname;

    @Column(name="date_of_birth")
    private Date dateOfBirth;

    @Column(name = "role_id")
    private int roleId;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "users")
    private Set<Post> posts;

    // @ManyToMany(fetch = FetchType.LAZY)
    // @JoinTable(
    //         name = "user_role",
    //         joinColumns = @JoinColumn(name = "user_id"),
    //         inverseJoinColumns = @JoinColumn(name = "role_id")
    // )
    // private Set<Role> roles = new HashSet<>();
    
}