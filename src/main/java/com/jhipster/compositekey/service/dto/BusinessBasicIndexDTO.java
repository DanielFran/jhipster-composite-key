package com.jhipster.compositekey.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the BusinessBasicIndex entity.
 */
public class BusinessBasicIndexDTO implements Serializable {

    @NotNull
    private Long businessId;

    @NotNull
    private Long basicIndexId;

    @NotNull
    private Integer year;

    @Min(value = 1)
    @Max(value = 12)
    private Integer month;

    @NotNull
    @Min(value = 0)
    private Integer value;

    private String businessName;

    private String basicIndexName;

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public Long getBasicIndexId() {
        return basicIndexId;
    }

    public void setBasicIndexId(Long basicIndexId) {
        this.basicIndexId = basicIndexId;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBasicIndexName() {
        return basicIndexName;
    }

    public void setBasicIndexName(String basicIndexName) {
        this.basicIndexName = basicIndexName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BusinessBasicIndexDTO businessBasicIndexDTO = (BusinessBasicIndexDTO) o;
        if (businessBasicIndexDTO.getBusinessId() == null && businessBasicIndexDTO.getBasicIndexId() == null && businessBasicIndexDTO.getYear() == null) {
            return false;
        }
        if (getBusinessId() == null && getBasicIndexId() == null && getYear() == null) {
            return false;
        }
        return Objects.equals(getBusinessId(), businessBasicIndexDTO.getBusinessId())
                && Objects.equals(getBasicIndexId(), businessBasicIndexDTO.getBasicIndexId())
                && Objects.equals(getYear(), businessBasicIndexDTO.getYear());
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + Objects.hashCode(this.businessId);
        result = 31 * result + Objects.hashCode(this.basicIndexId);
        result = 31 * result + Objects.hashCode(this.year);
        return result;
    }

    @Override
    public String toString() {
        return "BusinessBasicIndexDTO{" +
            "businessId=" + getBusinessId() +
            ", basicIndexId()=" + getBasicIndexId() +
            ", year=" + getYear() +
            ", month=" + getMonth() +
            ", value=" + getValue() +
            ", businessName=" + getBusinessName() +
            ", basicIndexName=" + getBasicIndexName() +
            "}";
    }
}
