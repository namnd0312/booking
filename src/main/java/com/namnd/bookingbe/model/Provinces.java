package com.namnd.bookingbe.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "provinces")
@Getter
@Setter
public class Provinces {
    @Id
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "name_en")
    private String nameEn;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "full_name_en")
    private String fullNameEn;

    @Column(name = "code_name")
    private String codeName;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Districts> districts;


}
