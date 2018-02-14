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

    private Long id;

    @NotNull
    private Integer year;

    @Min(value = 1)
    @Max(value = 12)
    private Integer month;

    @NotNull
    @Min(value = 0)
    private Integer value;

    private Long businessId;

    private String businessName;

    private Long basicIndexId;

    private String basicIndexName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public Long getBasicIndexId() {
        return basicIndexId;
    }

    public void setBasicIndexId(Long basicIndexId) {
        this.basicIndexId = basicIndexId;
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
        if(businessBasicIndexDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), businessBasicIndexDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BusinessBasicIndexDTO{" +
            "id=" + getId() +
            ", year=" + getYear() +
            ", month=" + getMonth() +
            ", value=" + getValue() +
            "}";
    }
}
