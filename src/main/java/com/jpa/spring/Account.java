package com.jpa.spring;


import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

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


}
