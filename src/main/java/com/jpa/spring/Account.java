package com.jpa.spring;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
@Data
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String password;


}
