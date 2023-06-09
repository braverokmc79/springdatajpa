package com.jpa.spring;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Study {

    @Id
    @GeneratedValue
    @Column(name="study_id")
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account owner;


}
