package com.lipop.model;




import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_exam")
public class Exam {
    private int id;
    private Student student;
    private Paper paper;
    private int singleScore;
    private int moreScore;
    private int score;
    private Date examDate;

    @Id
    @GeneratedValue(generator = "_native")
    @GenericGenerator(name = "_native",strategy = "native")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "studentId")
    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @ManyToOne
    @JoinColumn(name = "paperId")
    public Paper getPaper() {
        return paper;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public int getMoreScore() {
        return moreScore;
    }

    public void setMoreScore(int moreScore) {
        this.moreScore = moreScore;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getSingleScore() {
        return singleScore;
    }

    public void setSingleScore(int singleScore) {
        this.singleScore = singleScore;
    }
}
