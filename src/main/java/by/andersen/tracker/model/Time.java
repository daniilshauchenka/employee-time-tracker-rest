package by.andersen.tracker.model;

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

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @Column(name = "time_spent")
    private int timeSpent;

    @Column(name = "time_start")
    private LocalDateTime timeStart;

    @Column(name = "time_end")
    private LocalDateTime timeEnd;

    @Column(name = "comment")
    private String comment;

    @Column(name = "deleted")
    private boolean isDeleted;

    public Time(int id, Employee employee, Task task, int timeSpent, LocalDateTime timeStart, LocalDateTime timeEnd, String comment) {
        this.id = id;
        this.employee = employee;
        this.task = task;
        this.timeSpent = timeSpent;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.comment = comment;
    }

    public Time() {

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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public int getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(int timeSpent) {
        this.timeSpent = timeSpent;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Time time = (Time) o;
        return id == time.id && timeSpent == time.timeSpent && Objects.equals(employee, time.employee) && Objects.equals(task, time.task) && Objects.equals(timeStart, time.timeStart) && Objects.equals(timeEnd, time.timeEnd) && Objects.equals(comment, time.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, employee, task, timeSpent, timeStart, timeEnd, comment);
    }

    @Override
    public String toString() {
        return "Time{" +
                "id=" + id +
                ", employee=" + employee +
                ", task=" + task +
                ", timeSpent=" + timeSpent +
                ", timeStart=" + timeStart +
                ", timeEnd=" + timeEnd +
                ", comment='" + comment + '\'' +
                '}';
    }
}