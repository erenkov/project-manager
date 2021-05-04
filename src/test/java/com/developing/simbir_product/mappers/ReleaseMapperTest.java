package com.developing.simbir_product.mappers;

import com.developing.simbir_product.controller.Dto.ReleaseRequestDto;
import com.developing.simbir_product.controller.Dto.ReleaseResponseDto;
import com.developing.simbir_product.entity.ReleaseEntity;
import com.developing.simbir_product.service.ReleaseService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
class ReleaseMapperTest {

    ReleaseRequestDto releaseDto;
    ReleaseEntity releaseEntity;

    @Autowired
    ReleaseMapper releaseMapper;
    @Autowired
    ReleaseService releaseService;


    @BeforeEach
    void before() {
        releaseEntity = new ReleaseEntity("release name",
                OffsetDateTime.now().minusMonths(1),
                OffsetDateTime.now().plusMonths(2));
        releaseEntity = releaseService.addReleaseEntity(releaseEntity);

        releaseDto = new ReleaseRequestDto();
        releaseDto.setName("release name");
        releaseDto.setFinishDate(LocalDateTime.now().plusMonths(1));
        releaseDto.setStartDate(LocalDateTime.now().minusMonths(1));
    }

    @AfterEach
    void after() {
        releaseService.deleteById(releaseEntity.getId());
    }


    @Test
    void releaseEntityToDto() {
        ReleaseResponseDto releaseDtoTest = releaseMapper.releaseEntityToDto(releaseEntity);
        assertEquals(releaseDtoTest.getName(), releaseEntity.getName());
        assertEquals(releaseDtoTest.getFinishDate(), releaseEntity.getFinishDate().toLocalDateTime());
        assertEquals(releaseDtoTest.getStartDate(), releaseEntity.getStartDate().toLocalDateTime());
    }

    @Test
    void releaseDtoToEntity() {
        ReleaseEntity releaseEntityTest = releaseMapper.releaseDtoToEntity(releaseDto);
        assertEquals(releaseEntityTest.getName(), releaseDto.getName());
        assertEquals(releaseEntityTest.getFinishDate().toLocalDateTime(), releaseDto.getFinishDate());
        assertEquals(releaseEntityTest.getStartDate().toLocalDateTime(), releaseDto.getStartDate());
    }
}