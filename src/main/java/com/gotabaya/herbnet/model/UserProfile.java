package com.gotabaya.herbnet.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity(name = "user_profile")
@Getter
@Setter
@RequiredArgsConstructor
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProfileID")
    private Long profileId;

    @OneToOne
    @JoinColumn(name = "userid", referencedColumnName = "userid")
    private User user;

    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    private String location;
    @Column(name = "profilepictureurl")
    private String profilePictureURL;
}
