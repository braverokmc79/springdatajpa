package com.jpa.spring;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
public class Account {

    @Id
    @GeneratedValue
    @Column(name = "account_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    @Temporal(TemporalType.TIMESTAMP)
    private Date created=new Date();

    @Transient
    private String yes;

    private String no;


    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="street",
                column = @Column(name="home_street")
            )
    })
    private Address address;



    @OneToMany(mappedBy ="owner")
    private Set<Study> studies=new HashSet<>();


    public void addStudy(Study study){
        this.getStudies().add(study);
        study.setOwner(this);
    }


    public void removeStudy(Study study){
        this.getStudies().remove(study);
        study.setOwner(null);
    }




}
