package com.developing.simbir_product.mappers;

import com.developing.simbir_product.controller.Dto.TaskRequestDto;
import com.developing.simbir_product.controller.Dto.TaskResponseDto;
import com.developing.simbir_product.entity.*;
import com.developing.simbir_product.service.*;
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
class TaskMapperTest {

    final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    ProjectEntity projectEntity;
    UserTaskHistoryEntity userTaskHistoryEntity;
    TaskReleaseHistoryEntity taskReleaseHistoryEntity;
    TaskRequestDto taskDto;
    TaskEntity taskEntity;
    TeamEntity teamEntity;
    UserEntity userEntity;
    ReleaseEntity releaseEntity;
    @Autowired
    TeamService teamService;
    @Autowired
    TaskReleaseHistoryService taskReleaseHistoryService;
    @Autowired
    ReleaseService releaseService;
    @Autowired
    UserTaskHistoryService userTaskHistoryService;
    @Autowired
    UserService userService;
    @Autowired
    TaskService taskService;
    @Autowired
    TaskMapper taskMapper;
    @Autowired
    ProjectService projectService;

    @BeforeEach
    void before() {
        teamEntity = new TeamEntity("team name", "description");
        teamEntity = teamService.addTeam(teamEntity);

        projectEntity = new ProjectEntity("project name",
                "description",
                ProjectStatus.BACKLOG,
                OffsetDateTime.now(),
                OffsetDateTime.now().plusMonths(1),
                null);
        projectEntity.setTeamId(teamEntity);
        projectEntity = projectService.addProjectEntity(projectEntity);

        userEntity = new UserEntity("email",
                "password",
                "firstname",
                "lastname",
                Role.USER);
        userEntity.setTeamId(teamEntity);
        userEntity = userService.addUserEntity(userEntity);

        taskEntity = new TaskEntity("task name",
                TaskStatus.BACKLOG,
                TaskType.FEATURE,
                "description",
                OffsetDateTime.now(),
                OffsetDateTime.now().plusMonths(1),
                OffsetDateTime.now().plusMonths(2),
                100,
                200,
                "comments",
                300);
        taskEntity.setProjectId(projectEntity);

        taskEntity = taskService.addTaskEntity(taskEntity);

        userTaskHistoryEntity = new UserTaskHistoryEntity(OffsetDateTime.parse("2099-01-01T10:00:00+00:00"));
        userTaskHistoryEntity.setTaskId(taskEntity);
        userTaskHistoryEntity.setUserId(userEntity);
        userTaskHistoryEntity = userTaskHistoryService.addUserTaskHistory(userTaskHistoryEntity);

        releaseEntity = new ReleaseEntity("release name",
                OffsetDateTime.now().minusMonths(1),
                OffsetDateTime.now().plusMonths(2));
        releaseEntity = releaseService.addReleaseEntity(releaseEntity);

        taskReleaseHistoryEntity = new TaskReleaseHistoryEntity();
        taskReleaseHistoryEntity.setTaskId(taskEntity);
        taskReleaseHistoryEntity.setReleaseId(releaseEntity);
        taskReleaseHistoryService.addTaskRelease(taskReleaseHistoryEntity);

        taskDto = new TaskRequestDto();
        taskDto.setProjectName("project name");
        taskDto.setEstCosts(100);
        taskDto.setActualCosts(200);
        taskDto.setComments("comments");
        taskDto.setPriority(300);
        taskDto.setStatus("BACKLOG");
        taskDto.setType("FEATURE");
        taskDto.setCreateDate(OffsetDateTime.now().toLocalDateTime());
        taskDto.setDueDate(OffsetDateTime.now().plusMonths(1).toLocalDateTime());
        taskDto.setFinishDate(OffsetDateTime.now().plusMonths(2).toLocalDateTime());
        taskDto.setRelease("release name");
        taskDto.setTeam("team name");
    }

    @AfterEach
    void after() {
        userTaskHistoryService.deleteById(userTaskHistoryEntity.getId());
        userService.deleteById(userEntity.getId());
        taskReleaseHistoryService.deleteById(taskReleaseHistoryEntity.getId());
        taskService.deleteById(taskEntity.getId());
        projectService.deleteById(projectEntity.getId());
        teamService.deleteById(teamEntity.getId());
    }

    private String dateToString(OffsetDateTime dateTime) {
        return dateTime.format(dateFormatter);
    }


    @Test
    void taskEntityToDto() {
        TaskResponseDto testTaskDto = taskMapper.taskEntityToDto(taskEntity);
        assertEquals(taskEntity.getCreateDate().toLocalDateTime(), testTaskDto.getCreateDate());
        assertEquals(taskEntity.getDueDate().toLocalDateTime(), testTaskDto.getDueDate());
        assertEquals(taskEntity.getProjectId().getName(), testTaskDto.getProjectName());
        assertEquals(taskEntity.getName(), testTaskDto.getName());
        assertEquals(taskEntity.getActualCosts(), testTaskDto.getActualCosts());
        assertEquals(taskEntity.getComments(), testTaskDto.getComments());
        assertEquals(taskEntity.getEstCosts(), testTaskDto.getEstCosts());
        assertEquals(taskEntity.getPriority(), testTaskDto.getPriority());
        assertEquals(String.format("%s %s %s",
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getUserNumber()), testTaskDto.getAssigneeName());
        assertEquals(taskEntity.getDescription(), testTaskDto.getDescription());
        assertEquals(String.format("%s (%s - %s)",
                releaseEntity.getName(),
                dateToString(releaseEntity.getStartDate()),
                dateToString(releaseEntity.getFinishDate())), testTaskDto.getRelease());
        assertEquals(teamEntity.getName(), testTaskDto.getTeam());
        assertEquals(taskEntity.getTaskStatus().name(), testTaskDto.getStatus());
        assertEquals(taskEntity.getTaskType().name(), testTaskDto.getType());
        assertEquals(taskEntity.getFinishDate().toLocalDateTime(), testTaskDto.getFinishDate());
    }

    @Test
    void taskDtoToEntity() {
        TaskEntity testTaskEntity = taskMapper.taskDtoToEntity(taskDto);
        assertEquals(taskDto.getType(), testTaskEntity.getTaskType().name());
        assertEquals(taskDto.getStatus(), testTaskEntity.getTaskStatus().name());
        assertEquals(taskDto.getActualCosts(), testTaskEntity.getActualCosts());
        assertEquals(taskDto.getEstCosts(), testTaskEntity.getEstCosts());
        assertEquals(taskDto.getPriority(), testTaskEntity.getPriority());
        assertEquals(taskDto.getDescription(), testTaskEntity.getDescription());
        assertEquals(taskDto.getName(), testTaskEntity.getName());
        assertEquals(taskDto.getComments(), testTaskEntity.getComments());
        assertEquals(taskDto.getProjectName(), testTaskEntity.getProjectId().getName());
        assertEquals(taskDto.getDueDate(), testTaskEntity.getDueDate().toLocalDateTime());
        assertEquals(taskDto.getCreateDate(), testTaskEntity.getCreateDate().toLocalDateTime());
        assertEquals(taskDto.getFinishDate(), testTaskEntity.getFinishDate().toLocalDateTime());
    }
}