package com.mtt.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "task")
public class Task extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int taskId;

    @OneToOne(targetEntity = Projects.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "project_id")
    private Projects project;

    @OneToOne(targetEntity = ProjectAttachments.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "file_id", nullable = false)
    private int fileId;

    @OneToOne(targetEntity = Transcribers.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "transcriber_id", nullable = false)
    private int transcriberId;

    @Column(name = "task_name", length = 100)
    private String taskName;

    private Long recordingTime;

    @Column(name = "transcription_data",columnDefinition = "TEXT")
    private String transcriptionData;

    @Column(name = "transcription_time")
    private Long transcriptionTime;


    @Size(max = 30)
    private String status;

    @Column(name = "is_approved")
    private boolean isApproved;

    @Column(columnDefinition = "TEXT")
    private String remarks;

    @ManyToOne(targetEntity = Users.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "approved_by")
    private Users approvedBy;

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public Projects getProject() {
        return project;
    }

    public void setProject(Projects project) {
        this.project = project;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public int getTranscriberId() {
        return transcriberId;
    }

    public void setTranscriberId(int transcriberId) {
        this.transcriberId = transcriberId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Long getRecordingTime() {
        return recordingTime;
    }

    public void setRecordingTime(Long recordingTime) {
        this.recordingTime = recordingTime;
    }

    public String getTranscriptionData() {
        return transcriptionData;
    }

    public void setTranscriptionData(String transcriptionData) {
        this.transcriptionData = transcriptionData;
    }

    public Long getTranscriptionTime() {
        return transcriptionTime;
    }

    public void setTranscriptionTime(Long transcriptionTime) {
        this.transcriptionTime = transcriptionTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Users getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(Users approvedBy) {
        this.approvedBy = approvedBy;
    }
}
