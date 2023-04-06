package com.jpa.spring;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue
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


    //CascadeType.ALL 는 order
    //    persist(ownerA);
    //    persist(ownerB);
    //    persist(ownerB);
    //    persist(ownerB);
    //    persist(study);
    // =>persist를 각각 해줘야 하는데 CascadeType.ALL  적용하면  persist(study); 한번에 적용된다.
     @OneToMany(mappedBy ="owner", cascade = CascadeType.ALL)
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
