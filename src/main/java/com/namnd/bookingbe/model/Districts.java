package com.namnd.bookingbe.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "districts")
@Getter
@Setter
public class Districts {

    @Id
    private String code;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "name_en")
    private String nameEn;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "full_name_en")
    private String fullNameEn;

    @Column(name = "code_name")
    private String codeName;

    @Column(name = "province_code", length = 20)
    private String provinceCodes;

    @OneToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "district_code")
    private List<Wards> wards;
}
