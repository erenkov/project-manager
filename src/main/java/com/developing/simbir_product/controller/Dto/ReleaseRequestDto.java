package com.developing.simbir_product.controller.Dto;

import com.developing.simbir_product.validators.ReleaseWithDates;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;


@Schema(description = "{releaseDto.schema}")
@ReleaseWithDates
public class ReleaseRequestDto {

    @Schema(description = "{releaseDto.id.schema}")
    private String id;

    @Schema(description = "{releaseDto.name.schema}")
    @NotBlank(message = "{releaseDto.name.notBlank}")
    @Size(max = 50, message = "{releaseDto.name.size}")
    private String name;

    @Schema(description = "{releaseDto.startDate.schema}")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @NotNull(message = "{releaseDto.startDate.notNull}")
    private LocalDateTime startDate;

    @Schema(description = "{releaseDto.finishDate.schema}")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @NotNull(message = "{releaseDto.finishDate.notNull}")
    private LocalDateTime finishDate;

    @Schema(description = "{projectDto.name.schema}")
    @Size(max = 50, message = "{projectDto.name.size}")
    private String projectName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDateTime finishDate) {
        this.finishDate = finishDate;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
