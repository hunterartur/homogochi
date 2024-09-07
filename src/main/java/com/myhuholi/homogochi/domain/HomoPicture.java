package com.myhuholi.homogochi.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "hmg_picture")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HomoPicture {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "state_sysname")
    private String stateSysName;

    @Column(name = "filename")
    private String pictureFileName;

    @Column(name = "default_flag")
    private Boolean defaultFlag;
}
