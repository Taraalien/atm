package com.software.atm.common;


import lombok.Data;
import lombok.Generated;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@EntityListeners({AuditingEntityListener.class})
@MappedSuperclass
public class BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    @Column(name = "version")
    private Integer version;


    @CreatedBy
    @Column(name = "created_by")
    private String createdBy;


    @CreatedDate
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;


    @LastModifiedBy
    @Column(name = "lastModified_by")
    private String lastModifiedBy;


    @LastModifiedDate
    @Column(name = "lastModified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;



}
