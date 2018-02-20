package com.jhipster.compositekey.web.rest;

import com.jhipster.compositekey.JhipsterApp;

import com.jhipster.compositekey.domain.BusinessBasicIndex;
import com.jhipster.compositekey.domain.Business;
import com.jhipster.compositekey.domain.BasicIndex;
import com.jhipster.compositekey.domain.BusinessBasicIndexId;
import com.jhipster.compositekey.repository.BusinessBasicIndexRepository;
import com.jhipster.compositekey.service.BusinessBasicIndexService;
import com.jhipster.compositekey.service.dto.BusinessBasicIndexDTO;
import com.jhipster.compositekey.service.mapper.BusinessBasicIndexMapper;
import com.jhipster.compositekey.web.rest.errors.ExceptionTranslator;
import com.jhipster.compositekey.service.dto.BusinessBasicIndexCriteria;
import com.jhipster.compositekey.service.BusinessBasicIndexQueryService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static com.jhipster.compositekey.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the BusinessBasicIndexResource REST controller.
 *
 * @see BusinessBasicIndexResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterApp.class)
public class BusinessBasicIndexResourceIntTest {

    private static final Integer DEFAULT_YEAR = 1;
    private static final Integer UPDATED_YEAR = 2;

    private static final Integer DEFAULT_MONTH = 1;
    private static final Integer UPDATED_MONTH = 2;

    private static final Integer DEFAULT_VALUE = 0;
    private static final Integer UPDATED_VALUE = 1;

    @Autowired
    private BusinessBasicIndexRepository businessBasicIndexRepository;

    @Autowired
    private BusinessBasicIndexMapper businessBasicIndexMapper;

    @Autowired
    private BusinessBasicIndexService businessBasicIndexService;

    @Autowired
    private BusinessBasicIndexQueryService businessBasicIndexQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restBusinessBasicIndexMockMvc;

    private BusinessBasicIndex businessBasicIndex;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BusinessBasicIndexResource businessBasicIndexResource = new BusinessBasicIndexResource(businessBasicIndexService, businessBasicIndexQueryService);
        this.restBusinessBasicIndexMockMvc = MockMvcBuilders.standaloneSetup(businessBasicIndexResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BusinessBasicIndex createEntity(EntityManager em) {
        // Add required entity
        Business business = BusinessResourceIntTest.createEntity(em);
        em.persist(business);
        em.flush();
        // Add required entity
        BasicIndex basicIndex = BasicIndexResourceIntTest.createEntity(em);
        em.persist(basicIndex);
        em.flush();
        BusinessBasicIndex businessBasicIndex = new BusinessBasicIndex(business.getId(), basicIndex.getId(), DEFAULT_YEAR)
            .month(DEFAULT_MONTH)
            .value(DEFAULT_VALUE)
            .business(business)
            .basicIndex(basicIndex);
        return businessBasicIndex;
    }

    @Before
    public void initTest() {
        businessBasicIndex = createEntity(em);
    }

    @Test
    @Transactional
    public void createBusinessBasicIndex() throws Exception {
        int databaseSizeBeforeCreate = businessBasicIndexRepository.findAll().size();

        // Create the BusinessBasicIndex
        BusinessBasicIndexDTO businessBasicIndexDTO = businessBasicIndexMapper.toDto(businessBasicIndex);
        restBusinessBasicIndexMockMvc.perform(post("/api/business-basic-indices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(businessBasicIndexDTO)))
            .andExpect(status().isCreated());

        // Validate the BusinessBasicIndex in the database
        List<BusinessBasicIndex> businessBasicIndexList = businessBasicIndexRepository.findAll();
        assertThat(businessBasicIndexList).hasSize(databaseSizeBeforeCreate + 1);
        BusinessBasicIndex testBusinessBasicIndex = businessBasicIndexList.get(businessBasicIndexList.size() - 1);
        assertThat(testBusinessBasicIndex.getId().getYear()).isEqualTo(DEFAULT_YEAR);
        assertThat(testBusinessBasicIndex.getMonth()).isEqualTo(DEFAULT_MONTH);
        assertThat(testBusinessBasicIndex.getValue()).isEqualTo(DEFAULT_VALUE);
    }

    @Test
    @Transactional
    public void createBusinessBasicIndexWithExistingId() throws Exception {
        // Initialize the database
        businessBasicIndexRepository.saveAndFlush(businessBasicIndex);

        int databaseSizeBeforeCreate = businessBasicIndexRepository.findAll().size();

        // Create the BusinessBasicIndex with an existing ID
        BusinessBasicIndexDTO businessBasicIndexDTO = businessBasicIndexMapper.toDto(businessBasicIndex);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBusinessBasicIndexMockMvc.perform(post("/api/business-basic-indices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(businessBasicIndexDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BusinessBasicIndex in the database
        List<BusinessBasicIndex> businessBasicIndexList = businessBasicIndexRepository.findAll();
        assertThat(businessBasicIndexList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkBusinessIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = businessBasicIndexRepository.findAll().size();
        // set the field null
        businessBasicIndex.getId().setBusinessId(null);

        // Create the BusinessBasicIndex, which fails.
        BusinessBasicIndexDTO businessBasicIndexDTO = businessBasicIndexMapper.toDto(businessBasicIndex);

        restBusinessBasicIndexMockMvc.perform(post("/api/business-basic-indices")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(businessBasicIndexDTO)))
                .andExpect(status().isBadRequest());

        List<BusinessBasicIndex> businessBasicIndexList = businessBasicIndexRepository.findAll();
        assertThat(businessBasicIndexList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBasicIndexIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = businessBasicIndexRepository.findAll().size();
        // set the field null
        businessBasicIndex.getId().setBasicIndexId(null);

        // Create the BusinessBasicIndex, which fails.
        BusinessBasicIndexDTO businessBasicIndexDTO = businessBasicIndexMapper.toDto(businessBasicIndex);

        restBusinessBasicIndexMockMvc.perform(post("/api/business-basic-indices")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(businessBasicIndexDTO)))
                .andExpect(status().isBadRequest());

        List<BusinessBasicIndex> businessBasicIndexList = businessBasicIndexRepository.findAll();
        assertThat(businessBasicIndexList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkYearIsRequired() throws Exception {
        int databaseSizeBeforeTest = businessBasicIndexRepository.findAll().size();
        // set the field null
        businessBasicIndex.setYear(null);

        // Create the BusinessBasicIndex, which fails.
        BusinessBasicIndexDTO businessBasicIndexDTO = businessBasicIndexMapper.toDto(businessBasicIndex);

        restBusinessBasicIndexMockMvc.perform(post("/api/business-basic-indices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(businessBasicIndexDTO)))
            .andExpect(status().isBadRequest());

        List<BusinessBasicIndex> businessBasicIndexList = businessBasicIndexRepository.findAll();
        assertThat(businessBasicIndexList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkValueIsRequired() throws Exception {
        int databaseSizeBeforeTest = businessBasicIndexRepository.findAll().size();
        // set the field null
        businessBasicIndex.setValue(null);

        // Create the BusinessBasicIndex, which fails.
        BusinessBasicIndexDTO businessBasicIndexDTO = businessBasicIndexMapper.toDto(businessBasicIndex);

        restBusinessBasicIndexMockMvc.perform(post("/api/business-basic-indices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(businessBasicIndexDTO)))
            .andExpect(status().isBadRequest());

        List<BusinessBasicIndex> businessBasicIndexList = businessBasicIndexRepository.findAll();
        assertThat(businessBasicIndexList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBusinessBasicIndices() throws Exception {
        // Initialize the database
        businessBasicIndexRepository.saveAndFlush(businessBasicIndex);

        // Get all the businessBasicIndexList
        restBusinessBasicIndexMockMvc.perform(get("/api/business-basic-indices?sort=businessId,desc&basicIndexId,desc&year,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].year").value(hasItem(DEFAULT_YEAR)))
            .andExpect(jsonPath("$.[*].month").value(hasItem(DEFAULT_MONTH)))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE)));
    }

    @Test
    @Transactional
    public void getBusinessBasicIndex() throws Exception {
        // Initialize the database
        businessBasicIndexRepository.saveAndFlush(businessBasicIndex);

        // Get the businessBasicIndex
        restBusinessBasicIndexMockMvc.perform(get("/api/business-basic-indices/{businessId}/{basicIndexId}/{year}", businessBasicIndex.getId().getBusinessId(), businessBasicIndex.getId().getBasicIndexId(), businessBasicIndex.getId().getYear()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.year").value(DEFAULT_YEAR))
            .andExpect(jsonPath("$.month").value(DEFAULT_MONTH))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE));
    }

    @Test
    @Transactional
    public void getAllBusinessBasicIndicesByYearIsEqualToSomething() throws Exception {
        // Initialize the database
        businessBasicIndexRepository.saveAndFlush(businessBasicIndex);

        // Get all the businessBasicIndexList where year equals to DEFAULT_YEAR
        defaultBusinessBasicIndexShouldBeFound("year.equals=" + DEFAULT_YEAR);

        // Get all the businessBasicIndexList where year equals to UPDATED_YEAR
        defaultBusinessBasicIndexShouldNotBeFound("year.equals=" + UPDATED_YEAR);
    }

    @Test
    @Transactional
    public void getAllBusinessBasicIndicesByYearIsInShouldWork() throws Exception {
        // Initialize the database
        businessBasicIndexRepository.saveAndFlush(businessBasicIndex);

        // Get all the businessBasicIndexList where year in DEFAULT_YEAR or UPDATED_YEAR
        defaultBusinessBasicIndexShouldBeFound("year.in=" + DEFAULT_YEAR + "," + UPDATED_YEAR);

        // Get all the businessBasicIndexList where year equals to UPDATED_YEAR
        defaultBusinessBasicIndexShouldNotBeFound("year.in=" + UPDATED_YEAR);
    }

    @Test
    @Transactional
    public void getAllBusinessBasicIndicesByYearIsNullOrNotNull() throws Exception {
        // Initialize the database
        businessBasicIndexRepository.saveAndFlush(businessBasicIndex);

        // Get all the businessBasicIndexList where year is not null
        defaultBusinessBasicIndexShouldBeFound("year.specified=true");

        // Get all the businessBasicIndexList where year is null
        defaultBusinessBasicIndexShouldNotBeFound("year.specified=false");
    }

    @Test
    @Transactional
    public void getAllBusinessBasicIndicesByYearIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        businessBasicIndexRepository.saveAndFlush(businessBasicIndex);

        // Get all the businessBasicIndexList where year greater than or equals to DEFAULT_YEAR
        defaultBusinessBasicIndexShouldBeFound("year.greaterOrEqualThan=" + DEFAULT_YEAR);

        // Get all the businessBasicIndexList where year greater than or equals to UPDATED_YEAR
        defaultBusinessBasicIndexShouldNotBeFound("year.greaterOrEqualThan=" + UPDATED_YEAR);
    }

    @Test
    @Transactional
    public void getAllBusinessBasicIndicesByYearIsLessThanSomething() throws Exception {
        // Initialize the database
        businessBasicIndexRepository.saveAndFlush(businessBasicIndex);

        // Get all the businessBasicIndexList where year less than or equals to DEFAULT_YEAR
        defaultBusinessBasicIndexShouldNotBeFound("year.lessThan=" + DEFAULT_YEAR);

        // Get all the businessBasicIndexList where year less than or equals to UPDATED_YEAR
        defaultBusinessBasicIndexShouldBeFound("year.lessThan=" + UPDATED_YEAR);
    }


    @Test
    @Transactional
    public void getAllBusinessBasicIndicesByMonthIsEqualToSomething() throws Exception {
        // Initialize the database
        businessBasicIndexRepository.saveAndFlush(businessBasicIndex);

        // Get all the businessBasicIndexList where month equals to DEFAULT_MONTH
        defaultBusinessBasicIndexShouldBeFound("month.equals=" + DEFAULT_MONTH);

        // Get all the businessBasicIndexList where month equals to UPDATED_MONTH
        defaultBusinessBasicIndexShouldNotBeFound("month.equals=" + UPDATED_MONTH);
    }

    @Test
    @Transactional
    public void getAllBusinessBasicIndicesByMonthIsInShouldWork() throws Exception {
        // Initialize the database
        businessBasicIndexRepository.saveAndFlush(businessBasicIndex);

        // Get all the businessBasicIndexList where month in DEFAULT_MONTH or UPDATED_MONTH
        defaultBusinessBasicIndexShouldBeFound("month.in=" + DEFAULT_MONTH + "," + UPDATED_MONTH);

        // Get all the businessBasicIndexList where month equals to UPDATED_MONTH
        defaultBusinessBasicIndexShouldNotBeFound("month.in=" + UPDATED_MONTH);
    }

    @Test
    @Transactional
    public void getAllBusinessBasicIndicesByMonthIsNullOrNotNull() throws Exception {
        // Initialize the database
        businessBasicIndexRepository.saveAndFlush(businessBasicIndex);

        // Get all the businessBasicIndexList where month is not null
        defaultBusinessBasicIndexShouldBeFound("month.specified=true");

        // Get all the businessBasicIndexList where month is null
        defaultBusinessBasicIndexShouldNotBeFound("month.specified=false");
    }

    @Test
    @Transactional
    public void getAllBusinessBasicIndicesByMonthIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        businessBasicIndexRepository.saveAndFlush(businessBasicIndex);

        // Get all the businessBasicIndexList where month greater than or equals to DEFAULT_MONTH
        defaultBusinessBasicIndexShouldBeFound("month.greaterOrEqualThan=" + DEFAULT_MONTH);

        // Get all the businessBasicIndexList where month greater than or equals to (DEFAULT_MONTH + 1)
        defaultBusinessBasicIndexShouldNotBeFound("month.greaterOrEqualThan=" + (DEFAULT_MONTH + 1));
    }

    @Test
    @Transactional
    public void getAllBusinessBasicIndicesByMonthIsLessThanSomething() throws Exception {
        // Initialize the database
        businessBasicIndexRepository.saveAndFlush(businessBasicIndex);

        // Get all the businessBasicIndexList where month less than or equals to DEFAULT_MONTH
        defaultBusinessBasicIndexShouldNotBeFound("month.lessThan=" + DEFAULT_MONTH);

        // Get all the businessBasicIndexList where month less than or equals to (DEFAULT_MONTH + 1)
        defaultBusinessBasicIndexShouldBeFound("month.lessThan=" + (DEFAULT_MONTH + 1));
    }


    @Test
    @Transactional
    public void getAllBusinessBasicIndicesByValueIsEqualToSomething() throws Exception {
        // Initialize the database
        businessBasicIndexRepository.saveAndFlush(businessBasicIndex);

        // Get all the businessBasicIndexList where value equals to DEFAULT_VALUE
        defaultBusinessBasicIndexShouldBeFound("value.equals=" + DEFAULT_VALUE);

        // Get all the businessBasicIndexList where value equals to UPDATED_VALUE
        defaultBusinessBasicIndexShouldNotBeFound("value.equals=" + UPDATED_VALUE);
    }

    @Test
    @Transactional
    public void getAllBusinessBasicIndicesByValueIsInShouldWork() throws Exception {
        // Initialize the database
        businessBasicIndexRepository.saveAndFlush(businessBasicIndex);

        // Get all the businessBasicIndexList where value in DEFAULT_VALUE or UPDATED_VALUE
        defaultBusinessBasicIndexShouldBeFound("value.in=" + DEFAULT_VALUE + "," + UPDATED_VALUE);

        // Get all the businessBasicIndexList where value equals to UPDATED_VALUE
        defaultBusinessBasicIndexShouldNotBeFound("value.in=" + UPDATED_VALUE);
    }

    @Test
    @Transactional
    public void getAllBusinessBasicIndicesByValueIsNullOrNotNull() throws Exception {
        // Initialize the database
        businessBasicIndexRepository.saveAndFlush(businessBasicIndex);

        // Get all the businessBasicIndexList where value is not null
        defaultBusinessBasicIndexShouldBeFound("value.specified=true");

        // Get all the businessBasicIndexList where value is null
        defaultBusinessBasicIndexShouldNotBeFound("value.specified=false");
    }

    @Test
    @Transactional
    public void getAllBusinessBasicIndicesByValueIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        businessBasicIndexRepository.saveAndFlush(businessBasicIndex);

        // Get all the businessBasicIndexList where value greater than or equals to DEFAULT_VALUE
        defaultBusinessBasicIndexShouldBeFound("value.greaterOrEqualThan=" + DEFAULT_VALUE);

        // Get all the businessBasicIndexList where value greater than or equals to UPDATED_VALUE
        defaultBusinessBasicIndexShouldNotBeFound("value.greaterOrEqualThan=" + UPDATED_VALUE);
    }

    @Test
    @Transactional
    public void getAllBusinessBasicIndicesByValueIsLessThanSomething() throws Exception {
        // Initialize the database
        businessBasicIndexRepository.saveAndFlush(businessBasicIndex);

        // Get all the businessBasicIndexList where value less than or equals to DEFAULT_VALUE
        defaultBusinessBasicIndexShouldNotBeFound("value.lessThan=" + DEFAULT_VALUE);

        // Get all the businessBasicIndexList where value less than or equals to UPDATED_VALUE
        defaultBusinessBasicIndexShouldBeFound("value.lessThan=" + UPDATED_VALUE);
    }


    @Test
    @Transactional
    public void getAllBusinessBasicIndicesByBusinessIsEqualToSomething() throws Exception {
        // Initialize the database
        Business business = BusinessResourceIntTest.createEntity(em);
        em.persist(business);
        em.flush();
        businessBasicIndex.setBusiness(business);
        businessBasicIndexRepository.saveAndFlush(businessBasicIndex);
        Long businessId = business.getId();

        // Get all the businessBasicIndexList where business equals to businessId
        defaultBusinessBasicIndexShouldBeFound("businessId.equals=" + businessId);

        // Get all the businessBasicIndexList where business equals to businessId + 1
        defaultBusinessBasicIndexShouldNotBeFound("businessId.equals=" + (businessId + 1));
    }


    @Test
    @Transactional
    public void getAllBusinessBasicIndicesByBasicIndexIsEqualToSomething() throws Exception {
        // Initialize the database
        BasicIndex basicIndex = BasicIndexResourceIntTest.createEntity(em);
        em.persist(basicIndex);
        em.flush();
        businessBasicIndex.setBasicIndex(basicIndex);
        businessBasicIndexRepository.saveAndFlush(businessBasicIndex);
        Long basicIndexId = basicIndex.getId();

        // Get all the businessBasicIndexList where basicIndex equals to basicIndexId
        defaultBusinessBasicIndexShouldBeFound("basicIndexId.equals=" + basicIndexId);

        // Get all the businessBasicIndexList where basicIndex equals to basicIndexId + 1
        defaultBusinessBasicIndexShouldNotBeFound("basicIndexId.equals=" + (basicIndexId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned
     */
    private void defaultBusinessBasicIndexShouldBeFound(String filter) throws Exception {
        restBusinessBasicIndexMockMvc.perform(get("/api/business-basic-indices?sort=businessId,desc&basicIndexId,desc&year,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].year").value(hasItem(DEFAULT_YEAR)))
            .andExpect(jsonPath("$.[*].month").value(hasItem(DEFAULT_MONTH)))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE)));
    }

    /**
     * Executes the search, and checks that the default entity is not returned
     */
    private void defaultBusinessBasicIndexShouldNotBeFound(String filter) throws Exception {
        restBusinessBasicIndexMockMvc.perform(get("/api/business-basic-indices?sort=businessId,desc&basicIndexId,desc&year,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());
    }


    @Test
    @Transactional
    public void getNonExistingBusinessBasicIndex() throws Exception {
        // Get the businessBasicIndex
        restBusinessBasicIndexMockMvc.perform(get("/api/business-basic-indices/{businessId}/{basicIndexId}/{year}", Long.MAX_VALUE, Long.MAX_VALUE, Integer.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBusinessBasicIndex() throws Exception {
        // Initialize the database
        businessBasicIndexRepository.saveAndFlush(businessBasicIndex);
        int databaseSizeBeforeUpdate = businessBasicIndexRepository.findAll().size();

        // Update the businessBasicIndex
        BusinessBasicIndex updatedBusinessBasicIndex = businessBasicIndexRepository.findOne(businessBasicIndex.getId());
        // Disconnect from session so that the updates on updatedBusinessBasicIndex are not directly saved in db
        em.detach(updatedBusinessBasicIndex);
        updatedBusinessBasicIndex
            .month(UPDATED_MONTH)
            .value(UPDATED_VALUE);
        BusinessBasicIndexDTO businessBasicIndexDTO = businessBasicIndexMapper.toDto(updatedBusinessBasicIndex);

        restBusinessBasicIndexMockMvc.perform(put("/api/business-basic-indices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(businessBasicIndexDTO)))
            .andExpect(status().isOk());

        // Validate the BusinessBasicIndex in the database
        List<BusinessBasicIndex> businessBasicIndexList = businessBasicIndexRepository.findAll();
        assertThat(businessBasicIndexList).hasSize(databaseSizeBeforeUpdate);
        BusinessBasicIndex testBusinessBasicIndex = businessBasicIndexList.get(businessBasicIndexList.size() - 1);
        assertThat(testBusinessBasicIndex.getMonth()).isEqualTo(UPDATED_MONTH);
        assertThat(testBusinessBasicIndex.getValue()).isEqualTo(UPDATED_VALUE);
    }

    @Test
    @Transactional
    public void updateNonExistingBusinessBasicIndex() throws Exception {
        int databaseSizeBeforeUpdate = businessBasicIndexRepository.findAll().size();

        // Create the BusinessBasicIndex
        BusinessBasicIndexDTO businessBasicIndexDTO = businessBasicIndexMapper.toDto(businessBasicIndex);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restBusinessBasicIndexMockMvc.perform(put("/api/business-basic-indices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(businessBasicIndexDTO)))
            .andExpect(status().isCreated());

        // Validate the BusinessBasicIndex in the database
        List<BusinessBasicIndex> businessBasicIndexList = businessBasicIndexRepository.findAll();
        assertThat(businessBasicIndexList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteBusinessBasicIndex() throws Exception {
        // Initialize the database
        businessBasicIndexRepository.saveAndFlush(businessBasicIndex);
        int databaseSizeBeforeDelete = businessBasicIndexRepository.findAll().size();

        // Get the businessBasicIndex
        restBusinessBasicIndexMockMvc.perform(delete("/api/business-basic-indices/{businessId}/{basicIndexId}/{year}", businessBasicIndex.getId().getBusinessId(), businessBasicIndex.getId().getBasicIndexId(), businessBasicIndex.getId().getYear())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<BusinessBasicIndex> businessBasicIndexList = businessBasicIndexRepository.findAll();
        assertThat(businessBasicIndexList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BusinessBasicIndex.class);
        BusinessBasicIndex businessBasicIndex1 = new BusinessBasicIndex(1L, 2L, DEFAULT_YEAR);
        BusinessBasicIndex businessBasicIndex2 = new BusinessBasicIndex(1L, 2L, DEFAULT_YEAR);
        assertThat(businessBasicIndex1).isEqualTo(businessBasicIndex2);
        businessBasicIndex2.getId().setBusinessId(3L);
        assertThat(businessBasicIndex1).isNotEqualTo(businessBasicIndex2);
        businessBasicIndex1.setId(null);
        assertThat(businessBasicIndex1).isNotEqualTo(businessBasicIndex2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BusinessBasicIndexDTO.class);
        BusinessBasicIndexDTO businessBasicIndexDTO1 = new BusinessBasicIndexDTO();
        businessBasicIndexDTO1.setBasicIndexId(1L);
        businessBasicIndexDTO1.setBusinessId(2L);
        businessBasicIndexDTO1.setYear(DEFAULT_YEAR);
        BusinessBasicIndexDTO businessBasicIndexDTO2 = new BusinessBasicIndexDTO();
        assertThat(businessBasicIndexDTO1).isNotEqualTo(businessBasicIndexDTO2);
        businessBasicIndexDTO2.setBasicIndexId(businessBasicIndexDTO1.getBasicIndexId());
        businessBasicIndexDTO2.setBusinessId(businessBasicIndexDTO1.getBusinessId());
        businessBasicIndexDTO2.setYear(businessBasicIndexDTO1.getYear());
        assertThat(businessBasicIndexDTO1).isEqualTo(businessBasicIndexDTO2);
        businessBasicIndexDTO2.setBasicIndexId(2L);
        assertThat(businessBasicIndexDTO1).isNotEqualTo(businessBasicIndexDTO2);
        businessBasicIndexDTO1.setBasicIndexId(null);
        businessBasicIndexDTO1.setBusinessId(null);
        businessBasicIndexDTO1.setYear(null);
        assertThat(businessBasicIndexDTO1).isNotEqualTo(businessBasicIndexDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        BusinessBasicIndexId id = new BusinessBasicIndexId(1L, 2L, DEFAULT_YEAR);
        assertThat(businessBasicIndexMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(businessBasicIndexMapper.fromId(null)).isNull();
    }
}
