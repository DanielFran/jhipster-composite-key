package com.jhipster.compositekey.service.dto;

import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;

import java.io.Serializable;






/**
 * Criteria class for the BusinessBasicIndex entity. This class is used in BusinessBasicIndexResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /business-basic-indices?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class BusinessBasicIndexCriteria implements Serializable {
    private static final long serialVersionUID = 1L;


    private IntegerFilter year;

    private IntegerFilter month;

    private IntegerFilter value;

    private LongFilter businessId;

    private LongFilter basicIndexId;

    public BusinessBasicIndexCriteria() {
    }

    public IntegerFilter getYear() {
        return year;
    }

    public void setYear(IntegerFilter year) {
        this.year = year;
    }

    public IntegerFilter getMonth() {
        return month;
    }

    public void setMonth(IntegerFilter month) {
        this.month = month;
    }

    public IntegerFilter getValue() {
        return value;
    }

    public void setValue(IntegerFilter value) {
        this.value = value;
    }

    public LongFilter getBusinessId() {
        return businessId;
    }

    public void setBusinessId(LongFilter businessId) {
        this.businessId = businessId;
    }

    public LongFilter getBasicIndexId() {
        return basicIndexId;
    }

    public void setBasicIndexId(LongFilter basicIndexId) {
        this.basicIndexId = basicIndexId;
    }

    @Override
    public String toString() {
        return "BusinessBasicIndexCriteria{" +
            (year != null ? "year=" + year + ", " : "") +
            (month != null ? "month=" + month + ", " : "") +
            (value != null ? "value=" + value + ", " : "") +
            (businessId != null ? "businessId=" + businessId + ", " : "") +
            (basicIndexId != null ? "basicIndexId=" + basicIndexId + ", " : "") +
            "}";
    }

}
