package com.itsol.domain;
import com.itsol.domain.enumeration.CommonStatus;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "control")
public class Control implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "control_code")
    private String controlCode;

    @Column(name = "control_name")
    private String controlName;

    @Column(name = "region_code")
    private String regionCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private CommonStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getControlCode() {
        return controlCode;
    }

    public Control controlCode(String controlCode){
        this.controlCode = controlCode;
        return this;
    }

    public void setControlCode(String controlCode) {
        this.controlCode = controlCode;
    }

    public String getControlName() {
        return controlName;
    }

    public Control controlName(String controlName){
        this.controlName = controlName;
        return this;
    }

    public void setControlName(String controlName) {
        this.controlName = controlName;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public Control regionCode(String regionCode) {
        this.regionCode = regionCode;
        return this;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public CommonStatus getStatus() {
        return status;
    }

    public Control status(CommonStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(CommonStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Control)) {
            return false;
        }
        return id != null && id.equals(((Control) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Control{" +
            "id=" + id +
            ", controlCode='" + controlCode + '\'' +
            ", controlName='" + controlName + '\'' +
            ", regionCode='" + regionCode + '\'' +
            ", status=" + status +
            '}';
    }
}
