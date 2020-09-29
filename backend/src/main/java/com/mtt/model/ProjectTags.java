package com.mtt.model;

import javax.persistence.*;

@Entity
@Table(name = "project_tags")
public class ProjectTags extends BaseModel{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    @Id
    private int tagId;

    @ManyToOne(targetEntity = Projects.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "project_id")
    private Projects projects;

    @Column(length = 50)
    private String name;

    @Column(length = 100)
    private String description;

    @Column(length = 50)
    private String example;

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public Projects getProjects() {
        return projects;
    }

    public void setProjects(Projects projects) {
        this.projects = projects;
    }
}
