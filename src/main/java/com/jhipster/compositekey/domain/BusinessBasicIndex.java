package com.jhipster.compositekey.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A BusinessBasicIndex.
 */
@Entity
@Table(name = "business_basic_index")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class BusinessBasicIndex implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    @AttributeOverrides({
        @AttributeOverride(name = "businessId", column = @Column(name = "business_id", nullable = false)),
        @AttributeOverride(name = "basicIndexId", column = @Column(name = "basic_index_id", nullable = false)),
        @AttributeOverride(name = "year", column = @Column(name = "jhi_year", nullable = false))
    })
    private BusinessBasicIndexId id;

    @Min(value = 1)
    @Max(value = 12)
    @Column(name = "month")
    private Integer month;

    @NotNull
    @Min(value = 0)
    @Column(name = "jhi_value", nullable = false)
    private Integer value;

    @ManyToOne(optional = false)
    @JoinColumn(name="business_id", insertable = false, updatable = false)
    @NotNull
    private Business business;

    @ManyToOne(optional = false)
    @JoinColumn(name="basic_index_id", insertable = false, updatable = false)
    @NotNull
    private BasicIndex basicIndex;

    public BusinessBasicIndex() {
    }

    public BusinessBasicIndex(Long businessId, Long basicIndexId, Integer year) {
        this.id = new BusinessBasicIndexId(businessId, basicIndexId, year);
    }

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public BusinessBasicIndexId getId() {
        return id;
    }

    public void setId(BusinessBasicIndexId id) {
        this.id = id;
    }

    public Integer getMonth() {
        return month;
    }

    public BusinessBasicIndex month(Integer month) {
        this.month = month;
        return this;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getValue() {
        return value;
    }

    public BusinessBasicIndex value(Integer value) {
        this.value = value;
        return this;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Business getBusiness() {
        return business;
    }

    public BusinessBasicIndex business(Business business) {
        this.business = business;
        return this;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public BasicIndex getBasicIndex() {
        return basicIndex;
    }

    public BusinessBasicIndex basicIndex(BasicIndex basicIndex) {
        this.basicIndex = basicIndex;
        return this;
    }

    public void setBasicIndex(BasicIndex basicIndex) {
        this.basicIndex = basicIndex;
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
        BusinessBasicIndex businessBasicIndex = (BusinessBasicIndex) o;
        if (businessBasicIndex.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), businessBasicIndex.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BusinessBasicIndex{" +
            "id=" + getId() +
            ", month=" + getMonth() +
            ", value=" + getValue() +
            "}";
    }
}
