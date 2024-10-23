package com.challenge.sermaluc.usuarios.domain.model.entity;



import com.challenge.sermaluc.usuarios.domain.model.enums.AppUserRole;
import com.challenge.sermaluc.usuarios.domain.model.enums.UserState;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Table(name="users")
public class User {

    @Id
    @Column(name = "id", unique=true)
    private String userId;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name= "status", length = 20)
    @Enumerated(EnumType.STRING)
    private UserState status;

    @Column(name = "token")
    private  String token;

    @Column(name = "name")
    private String name;

    @Column( name= "created")
    private LocalDateTime created;

    @Column( name= "modified")
    private LocalDateTime modified;

    @Column( name= "last_login")
    private LocalDateTime lastLogin;

    @ElementCollection( fetch = FetchType.EAGER)
    List<AppUserRole> appUserRoles;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "user_id")
    private List<Phone> phoneList = new ArrayList<>();

    @PrePersist
    protected  void onCreated() { setUserId(java.util.UUID.randomUUID().toString());}

}
