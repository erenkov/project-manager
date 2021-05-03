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

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
class ReleaseMapperTest {

    final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
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
        releaseDto.setFinishDate(dateToString(OffsetDateTime.now().plusMonths(1)));
        releaseDto.setStartDate(dateToString(OffsetDateTime.now().minusMonths(1)));
    }

    @AfterEach
    void after() {
        releaseService.deleteById(releaseEntity.getId());
    }


    private String dateToString(OffsetDateTime dateTime) {
        return dateTime.format(dateFormatter);
    }


    @Test
    void releaseEntityToDto() {
        ReleaseResponseDto releaseDtoTest = releaseMapper.releaseEntityToDto(releaseEntity);
        assertEquals(releaseDtoTest.getName(), releaseEntity.getName());
        assertEquals(releaseDtoTest.getFinishDate(), dateToString(releaseEntity.getFinishDate()));
        assertEquals(releaseDtoTest.getStartDate(), dateToString(releaseEntity.getStartDate()));
    }

    @Test
    void releaseDtoToEntity() {
        ReleaseEntity releaseEntityTest = releaseMapper.releaseDtoToEntity(releaseDto);
        assertEquals(releaseEntityTest.getName(), releaseDto.getName());
        assertEquals(dateToString(releaseEntityTest.getFinishDate()), releaseDto.getFinishDate());
        assertEquals(dateToString(releaseEntityTest.getStartDate()), releaseDto.getStartDate());
    }
}