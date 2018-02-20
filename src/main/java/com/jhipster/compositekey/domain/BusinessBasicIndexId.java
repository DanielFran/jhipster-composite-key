package com.jhipster.compositekey.domain;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class BusinessBasicIndexId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "business_id", nullable = false)
    private Long businessId;

    @Column(name = "basic_index_id", nullable = false)
    private Long basicIndexId;

    @Column(name = "jhi_year", nullable = false)
    private Integer year;

    public BusinessBasicIndexId() {
    }

    public BusinessBasicIndexId(Long businessId, Long basicIndexId, Integer year) {
        this.businessId = businessId;
        this.basicIndexId = basicIndexId;
        this.year = year;
    }

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

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BusinessBasicIndexId businessBasicIndexId = (BusinessBasicIndexId) o;
        return Objects.equals(businessId, businessBasicIndexId.businessId)
                && Objects.equals(basicIndexId, businessBasicIndexId.basicIndexId)
                && Objects.equals(year, businessBasicIndexId.year);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + businessId.hashCode();
        result = 31 * result + basicIndexId.hashCode();
        result = 31 * result + year.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "BusinessBasicIndexId{"
                + "businessId=" + getBusinessId()
                + ", basicIndexId=" + getBasicIndexId()
                + ", year=" + getYear()
                + "}";
    }

}
