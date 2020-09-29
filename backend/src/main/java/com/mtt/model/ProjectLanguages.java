package com.mtt.model;

import javax.persistence.*;

@Entity
@Table(name="project_language")
public class ProjectLanguages extends BaseModel {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id")
    @Id
    private int languageId;

    @Column(name = "language_name", length = 100)
    private String languageName;

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }
}
