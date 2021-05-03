package com.developing.simbir_product.mappers;

import com.developing.simbir_product.controller.Dto.TaskRequestDto;
import com.developing.simbir_product.controller.Dto.TaskResponseDto;
import com.developing.simbir_product.entity.*;
import com.developing.simbir_product.service.*;
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

        ProjectEntity projectEntity = new ProjectEntity("project name",
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

        UserTaskHistoryEntity userTaskHistoryEntity =
                new UserTaskHistoryEntity(OffsetDateTime.parse("2099-01-01T10:00:00+00:00"));
        userTaskHistoryEntity.setTaskId(taskEntity);
        userTaskHistoryEntity.setUserId(userEntity);
        userTaskHistoryService.addUserTaskHistory(userTaskHistoryEntity);

        releaseEntity = new ReleaseEntity("release name",
                OffsetDateTime.now().minusMonths(1),
                OffsetDateTime.now().plusMonths(2));
        releaseEntity = releaseService.addReleaseEntity(releaseEntity);

        TaskReleaseHistoryEntity taskReleaseHistoryEntity = new TaskReleaseHistoryEntity();
        taskReleaseHistoryEntity.setTaskId(taskEntity);
        taskReleaseHistoryEntity.setReleaseId(releaseEntity);
        taskReleaseHistoryService.addTaskRelease(taskReleaseHistoryEntity);

        taskDto = new TaskRequestDto();
        taskDto.setProject("project name");
        taskDto.setEstCosts(100);
        taskDto.setActualCosts(200);
        taskDto.setComments("comments");
        taskDto.setPriority(300);
        taskDto.setStatus("BACKLOG");
        taskDto.setType("FEATURE");
        taskDto.setCreateDate(dateToString(OffsetDateTime.now()));
        taskDto.setDueDate(dateToString(OffsetDateTime.now().plusMonths(1)));
        taskDto.setFinishDate(dateToString(OffsetDateTime.now().plusMonths(2)));
        taskDto.setRelease("release name");
        taskDto.setTeam("team name");
    }

    private String dateToString(OffsetDateTime dateTime) {
        return dateTime.format(dateFormatter);
    }


    @Test
    void taskEntityToDto() {
        TaskResponseDto testTaskDto = taskMapper.taskEntityToDto(taskEntity);
        assertEquals(testTaskDto.getCreateDate(), taskEntity.getCreateDate().format(dateFormatter));
        assertEquals(testTaskDto.getDueDate(), taskEntity.getDueDate().format(dateFormatter));
        assertEquals(testTaskDto.getProject(), taskEntity.getProjectId().getName());
        assertEquals(testTaskDto.getName(), taskEntity.getName());
        assertEquals(testTaskDto.getActualCosts(), taskEntity.getActualCosts());
        assertEquals(testTaskDto.getComments(), taskEntity.getComments());
        assertEquals(testTaskDto.getEstCosts(), taskEntity.getEstCosts());
        assertEquals(testTaskDto.getPriority(), taskEntity.getPriority());
        assertEquals(testTaskDto.getAssigneeName(), String.format("%s %s %s",
                userEntity.getFirstName(),
                userEntity.getFirstName(),
                userEntity.getUserNumber()));
        assertEquals(testTaskDto.getDescription(), taskEntity.getDescription());
        assertEquals(testTaskDto.getRelease(), String.format("%s (%s - %s)",
                releaseEntity.getName(),
                dateToString(releaseEntity.getStartDate()),
                dateToString(releaseEntity.getFinishDate())));
        assertEquals(testTaskDto.getTeam(), teamEntity.getName());
        assertEquals(testTaskDto.getStatus(), taskEntity.getTaskStatus().name());
        assertEquals(testTaskDto.getType(), taskEntity.getTaskType().name());
        assertEquals(testTaskDto.getFinishDate(), dateToString(taskEntity.getFinishDate()));
    }

    @Test
    void taskDtoToEntity() {
        TaskEntity testTaskEntity = taskMapper.taskDtoToEntity(taskDto);
        assertEquals(testTaskEntity.getTaskType().name(), taskDto.getType());
        assertEquals(testTaskEntity.getTaskStatus().name(), taskDto.getStatus());
        assertEquals(testTaskEntity.getActualCosts(), taskDto.getActualCosts());
        assertEquals(testTaskEntity.getEstCosts(), taskDto.getEstCosts());
        assertEquals(testTaskEntity.getPriority(), taskDto.getPriority());
        assertEquals(testTaskEntity.getDescription(), taskDto.getDescription());
        assertEquals(testTaskEntity.getName(), taskDto.getName());
        assertEquals(testTaskEntity.getComments(), taskDto.getComments());
        assertEquals(testTaskEntity.getProjectId().getName(), taskDto.getProject());
        assertEquals(dateToString(testTaskEntity.getDueDate()), taskDto.getDueDate());
        assertEquals(dateToString(testTaskEntity.getCreateDate()), taskDto.getCreateDate());
        assertEquals(dateToString(testTaskEntity.getFinishDate()), taskDto.getFinishDate());
    }
}