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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "jhi_year", nullable = false)
    private Integer year;

    @Min(value = 1)
    @Max(value = 12)
    @Column(name = "month")
    private Integer month;

    @NotNull
    @Min(value = 0)
    @Column(name = "jhi_value", nullable = false)
    private Integer value;

    @ManyToOne(optional = false)
    @NotNull
    private Business business;

    @ManyToOne(optional = false)
    @NotNull
    private BasicIndex basicIndex;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public BusinessBasicIndex year(Integer year) {
        this.year = year;
        return this;
    }

    public void setYear(Integer year) {
        this.year = year;
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
            ", year=" + getYear() +
            ", month=" + getMonth() +
            ", value=" + getValue() +
            "}";
    }
}
