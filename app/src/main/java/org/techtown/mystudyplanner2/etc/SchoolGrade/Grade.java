package org.techtown.mystudyplanner2.etc.SchoolGrade;

public class Grade {
    int _id;
    String semester;
    String subjectClassification;
    String subject;
    int learnTime;
    float score;
    String accomplishment;
    int grade;
    float average;
    float Diviation;

    public Grade(int _id, String semester, String subjectClassification, String subject, int learnTime, float score, String accomplishment, int grade, float average, float diviation) {
        this._id = _id;
        this.semester = semester;
        this.subjectClassification = subjectClassification;
        this.subject = subject;
        this.learnTime = learnTime;
        this.score = score;
        this.accomplishment = accomplishment;
        this.grade = grade;
        this.average = average;
        Diviation = diviation;
    }

    public int get_id() {
        return _id;
    }

    public String getSemester() {
        return semester;
    }

    public String getSubjectClassification() {
        return subjectClassification;
    }

    public String getSubject() {
        return subject;
    }

    public int getLearnTime() {
        return learnTime;
    }

    public float getScore() {
        return score;
    }

    public String getAccomplishment() {
        return accomplishment;
    }

    public int getGrade() {
        return grade;
    }

    public float getAverage() {
        return average;
    }

    public float getDiviation() {
        return Diviation;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public void setSubjectClassification(String subjectClassification) {
        this.subjectClassification = subjectClassification;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setLearnTime(int learnTime) {
        this.learnTime = learnTime;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public void setAccomplishment(String accomplishment) {
        this.accomplishment = accomplishment;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setAverage(float average) {
        this.average = average;
    }

    public void setDiviation(float diviation) {
        Diviation = diviation;
    }
}
