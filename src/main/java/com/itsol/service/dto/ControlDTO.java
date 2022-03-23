package com.itsol.service.dto;

import com.itsol.domain.enumeration.CommonStatus;
import java.io.Serializable;


/**
 * A DTO for the {@link com.itsol.domain.Control} entity.
 */
public class ControlDTO implements Serializable {

    private Long id;

    private String controlCode;

    private String controlName;

    private String regionCode;

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

    public void setControlCode(String controlCode) {
        this.controlCode = controlCode;
    }

    public String getControlName() {
        return controlName;
    }

    public void setControlName(String controlName) {
        this.controlName = controlName;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public CommonStatus getStatus() {
        return status;
    }

    public void setStatus(CommonStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ControlDTO)) {
            return false;
        }

        return id != null && id.equals(((ControlDTO) o).id);
    }
    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ControlDTO{" +
            "id=" + id +
            ", controlCode='" + controlCode + '\'' +
            ", controlName='" + controlName + '\'' +
            ", regionCode='" + regionCode + '\'' +
            ", status='" + status + '\'' +
            '}';
    }
}
