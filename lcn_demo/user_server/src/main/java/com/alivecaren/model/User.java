package com.alivecaren.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table
@Data
public class User implements Serializable {
    @Id
    private Long id;
    private String username;
    private String password;

}
