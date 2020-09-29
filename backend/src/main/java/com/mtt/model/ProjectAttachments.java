package com.mtt.model;

import javax.persistence.*;

@Entity
@Table(name = "project_attachment")
public class ProjectAttachments extends BaseModel {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;

    @ManyToOne(targetEntity = Projects.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "project_id")
    private Projects projects;

    @Column(name = "file_path", columnDefinition = "TEXT")
    private String filePath;

    @Column(name = "created_by")
    private int createdBy;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Projects getProjects() {
        return projects;
    }

    public void setProjects(Projects projects) {
        this.projects = projects;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }
}
