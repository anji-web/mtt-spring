package com.mtt.model;

import javax.persistence.*;

@Entity
public class Projects extends BaseModel {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "project_id")
    @Id
    private int projectId;

    @Column(name = "project_name", length = 100)
    private String projectName;

    @Column(name = "time_live_task")
    private int timeLiveTask;

    @Column(name = "project_description", columnDefinition = "TEXT")
    private String projectDescription;

    @OneToOne(targetEntity = ProjectLanguages.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "language_id")
    private ProjectLanguages projectLanguage;

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getTimeLiveTask() {
        return timeLiveTask;
    }

    public void setTimeLiveTask(int timeLiveTask) {
        this.timeLiveTask = timeLiveTask;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public ProjectLanguages getProjectLanguage() {
        return projectLanguage;
    }

    public void setProjectLanguage(ProjectLanguages projectLanguage) {
        this.projectLanguage = projectLanguage;
    }
}
