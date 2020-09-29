package com.mtt.model;

import javax.persistence.*;

@Entity
@Table(name = "project_admins")
public class ProjectAdmins extends BaseModel{

    @Column(name = "project_admin_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(targetEntity = Projects.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "project_id")
    private Projects projects;

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
}
