package by.andersen.tracker.model;

import by.andersen.tracker.util.LocalDateTimeDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "time")
public class Time {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "employee_id")
    private int employeeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", insertable = false, updatable = false)
    private Employee employee;

    @Column(name = "task_id")
    private int taskId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", insertable = false, updatable = false)
    private Task task;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @Column(name = "time_start")
    private LocalDateTime timeStart;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @Column(name = "time_end")
    private LocalDateTime timeEnd;

    @Column(name = "comment")
    private String comment;

    @Column(name = "deleted")
    private boolean isDeleted;

    public Time() {
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
        if (employee != null) {
            this.employeeId = employee.getId();
        }
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
        if (task != null) {
            this.taskId = task.getId();
        }
    }


    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Time{" +
                "id=" + id +
                ", employeeId=" + employeeId +
                ", task=" + taskId +
                ", timeStart=" + timeStart +
                ", timeEnd=" + timeEnd +
                ", comment='" + comment + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Time time = (Time) o;
        return id == time.id && employeeId == time.employeeId && taskId == time.taskId && isDeleted == time.isDeleted && Objects.equals(timeStart, time.timeStart) && Objects.equals(timeEnd, time.timeEnd) && Objects.equals(comment, time.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employeeId, taskId, timeStart, timeEnd, comment, isDeleted);
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int task) {
        this.taskId = task;
    }

    public LocalDateTime getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(LocalDateTime timeStart) {
        this.timeStart = timeStart;
    }

    public LocalDateTime getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(LocalDateTime timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}