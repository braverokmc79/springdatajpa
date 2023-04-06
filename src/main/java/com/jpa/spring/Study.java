package com.jpa.spring;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Entity
@Data
public class Study {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account owner;


}
