package com.jhipster.compositekey.web.rest;

import com.jhipster.compositekey.JhipsterApp;

import com.jhipster.compositekey.domain.BasicIndex;
import com.jhipster.compositekey.repository.BasicIndexRepository;
import com.jhipster.compositekey.service.BasicIndexService;
import com.jhipster.compositekey.service.dto.BasicIndexDTO;
import com.jhipster.compositekey.service.mapper.BasicIndexMapper;
import com.jhipster.compositekey.web.rest.errors.ExceptionTranslator;

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
 * Test class for the BasicIndexResource REST controller.
 *
 * @see BasicIndexResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterApp.class)
public class BasicIndexResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_NICKNAME = "AAAAAAAAAA";
    private static final String UPDATED_NICKNAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private BasicIndexRepository basicIndexRepository;

    @Autowired
    private BasicIndexMapper basicIndexMapper;

    @Autowired
    private BasicIndexService basicIndexService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restBasicIndexMockMvc;

    private BasicIndex basicIndex;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BasicIndexResource basicIndexResource = new BasicIndexResource(basicIndexService);
        this.restBasicIndexMockMvc = MockMvcBuilders.standaloneSetup(basicIndexResource)
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
    public static BasicIndex createEntity(EntityManager em) {
        BasicIndex basicIndex = new BasicIndex()
            .name(DEFAULT_NAME)
            .nickname(DEFAULT_NICKNAME)
            .description(DEFAULT_DESCRIPTION);
        return basicIndex;
    }

    @Before
    public void initTest() {
        basicIndex = createEntity(em);
    }

    @Test
    @Transactional
    public void createBasicIndex() throws Exception {
        int databaseSizeBeforeCreate = basicIndexRepository.findAll().size();

        // Create the BasicIndex
        BasicIndexDTO basicIndexDTO = basicIndexMapper.toDto(basicIndex);
        restBasicIndexMockMvc.perform(post("/api/basic-indices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(basicIndexDTO)))
            .andExpect(status().isCreated());

        // Validate the BasicIndex in the database
        List<BasicIndex> basicIndexList = basicIndexRepository.findAll();
        assertThat(basicIndexList).hasSize(databaseSizeBeforeCreate + 1);
        BasicIndex testBasicIndex = basicIndexList.get(basicIndexList.size() - 1);
        assertThat(testBasicIndex.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testBasicIndex.getNickname()).isEqualTo(DEFAULT_NICKNAME);
        assertThat(testBasicIndex.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createBasicIndexWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = basicIndexRepository.findAll().size();

        // Create the BasicIndex with an existing ID
        basicIndex.setId(1L);
        BasicIndexDTO basicIndexDTO = basicIndexMapper.toDto(basicIndex);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBasicIndexMockMvc.perform(post("/api/basic-indices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(basicIndexDTO)))
            .andExpect(status().isBadRequest());

        // Validate the BasicIndex in the database
        List<BasicIndex> basicIndexList = basicIndexRepository.findAll();
        assertThat(basicIndexList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = basicIndexRepository.findAll().size();
        // set the field null
        basicIndex.setName(null);

        // Create the BasicIndex, which fails.
        BasicIndexDTO basicIndexDTO = basicIndexMapper.toDto(basicIndex);

        restBasicIndexMockMvc.perform(post("/api/basic-indices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(basicIndexDTO)))
            .andExpect(status().isBadRequest());

        List<BasicIndex> basicIndexList = basicIndexRepository.findAll();
        assertThat(basicIndexList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNicknameIsRequired() throws Exception {
        int databaseSizeBeforeTest = basicIndexRepository.findAll().size();
        // set the field null
        basicIndex.setNickname(null);

        // Create the BasicIndex, which fails.
        BasicIndexDTO basicIndexDTO = basicIndexMapper.toDto(basicIndex);

        restBasicIndexMockMvc.perform(post("/api/basic-indices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(basicIndexDTO)))
            .andExpect(status().isBadRequest());

        List<BasicIndex> basicIndexList = basicIndexRepository.findAll();
        assertThat(basicIndexList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkDescriptionIsRequired() throws Exception {
        int databaseSizeBeforeTest = basicIndexRepository.findAll().size();
        // set the field null
        basicIndex.setDescription(null);

        // Create the BasicIndex, which fails.
        BasicIndexDTO basicIndexDTO = basicIndexMapper.toDto(basicIndex);

        restBasicIndexMockMvc.perform(post("/api/basic-indices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(basicIndexDTO)))
            .andExpect(status().isBadRequest());

        List<BasicIndex> basicIndexList = basicIndexRepository.findAll();
        assertThat(basicIndexList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBasicIndices() throws Exception {
        // Initialize the database
        basicIndexRepository.saveAndFlush(basicIndex);

        // Get all the basicIndexList
        restBasicIndexMockMvc.perform(get("/api/basic-indices?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(basicIndex.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].nickname").value(hasItem(DEFAULT_NICKNAME.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }

    @Test
    @Transactional
    public void getBasicIndex() throws Exception {
        // Initialize the database
        basicIndexRepository.saveAndFlush(basicIndex);

        // Get the basicIndex
        restBasicIndexMockMvc.perform(get("/api/basic-indices/{id}", basicIndex.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(basicIndex.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.nickname").value(DEFAULT_NICKNAME.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingBasicIndex() throws Exception {
        // Get the basicIndex
        restBasicIndexMockMvc.perform(get("/api/basic-indices/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBasicIndex() throws Exception {
        // Initialize the database
        basicIndexRepository.saveAndFlush(basicIndex);
        int databaseSizeBeforeUpdate = basicIndexRepository.findAll().size();

        // Update the basicIndex
        BasicIndex updatedBasicIndex = basicIndexRepository.findOne(basicIndex.getId());
        // Disconnect from session so that the updates on updatedBasicIndex are not directly saved in db
        em.detach(updatedBasicIndex);
        updatedBasicIndex
            .name(UPDATED_NAME)
            .nickname(UPDATED_NICKNAME)
            .description(UPDATED_DESCRIPTION);
        BasicIndexDTO basicIndexDTO = basicIndexMapper.toDto(updatedBasicIndex);

        restBasicIndexMockMvc.perform(put("/api/basic-indices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(basicIndexDTO)))
            .andExpect(status().isOk());

        // Validate the BasicIndex in the database
        List<BasicIndex> basicIndexList = basicIndexRepository.findAll();
        assertThat(basicIndexList).hasSize(databaseSizeBeforeUpdate);
        BasicIndex testBasicIndex = basicIndexList.get(basicIndexList.size() - 1);
        assertThat(testBasicIndex.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testBasicIndex.getNickname()).isEqualTo(UPDATED_NICKNAME);
        assertThat(testBasicIndex.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingBasicIndex() throws Exception {
        int databaseSizeBeforeUpdate = basicIndexRepository.findAll().size();

        // Create the BasicIndex
        BasicIndexDTO basicIndexDTO = basicIndexMapper.toDto(basicIndex);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restBasicIndexMockMvc.perform(put("/api/basic-indices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(basicIndexDTO)))
            .andExpect(status().isCreated());

        // Validate the BasicIndex in the database
        List<BasicIndex> basicIndexList = basicIndexRepository.findAll();
        assertThat(basicIndexList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteBasicIndex() throws Exception {
        // Initialize the database
        basicIndexRepository.saveAndFlush(basicIndex);
        int databaseSizeBeforeDelete = basicIndexRepository.findAll().size();

        // Get the basicIndex
        restBasicIndexMockMvc.perform(delete("/api/basic-indices/{id}", basicIndex.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<BasicIndex> basicIndexList = basicIndexRepository.findAll();
        assertThat(basicIndexList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BasicIndex.class);
        BasicIndex basicIndex1 = new BasicIndex();
        basicIndex1.setId(1L);
        BasicIndex basicIndex2 = new BasicIndex();
        basicIndex2.setId(basicIndex1.getId());
        assertThat(basicIndex1).isEqualTo(basicIndex2);
        basicIndex2.setId(2L);
        assertThat(basicIndex1).isNotEqualTo(basicIndex2);
        basicIndex1.setId(null);
        assertThat(basicIndex1).isNotEqualTo(basicIndex2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BasicIndexDTO.class);
        BasicIndexDTO basicIndexDTO1 = new BasicIndexDTO();
        basicIndexDTO1.setId(1L);
        BasicIndexDTO basicIndexDTO2 = new BasicIndexDTO();
        assertThat(basicIndexDTO1).isNotEqualTo(basicIndexDTO2);
        basicIndexDTO2.setId(basicIndexDTO1.getId());
        assertThat(basicIndexDTO1).isEqualTo(basicIndexDTO2);
        basicIndexDTO2.setId(2L);
        assertThat(basicIndexDTO1).isNotEqualTo(basicIndexDTO2);
        basicIndexDTO1.setId(null);
        assertThat(basicIndexDTO1).isNotEqualTo(basicIndexDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(basicIndexMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(basicIndexMapper.fromId(null)).isNull();
    }
}
