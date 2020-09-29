package com.mtt.model;

import javax.persistence.*;

@Entity
public class Transcribers extends BaseModel {
    @Column(name = "transcriber_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(targetEntity = Users.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private Users users;

    @OneToOne(targetEntity = Projects.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "project_id")
    private Projects project;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Projects getProject() {
        return project;
    }

    public void setProject(Projects project) {
        this.project = project;
    }
}
