package org.techtown.mystudyplanner2.etc.NationGrade;

public class NGrade {
    int _id;
    String testName;
    String subjectClassification;
    String subject;
    int originalScore;
    int standardScore;
    int grade;
    Float percentage;

    public NGrade(int _id, String testName, String subjectClassification, String subject, int originalScore, int standardScore, int grade, float percentage) {
        this._id = _id;
        this.testName = testName;
        this.subjectClassification = subjectClassification;
        this.subject = subject;
        this.originalScore = originalScore;
        this.standardScore = standardScore;
        this.grade = grade;
        this.percentage = percentage;
    }

    public int get_id() {
        return _id;
    }

    public String getTestName() {
        return testName;
    }

    public String getSubjectClassification() {
        return subjectClassification;
    }

    public String getSubject() {
        return subject;
    }

    public int getOriginalScore() {
        return originalScore;
    }

    public int getStandardScore() {
        return standardScore;
    }

    public int getGrade() {
        return grade;
    }

    public float getPercentage() {
        return percentage;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public void setSubjectClassification(String subjectClassification) {
        this.subjectClassification = subjectClassification;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setOriginalScore(int originalScore) {
        this.originalScore = originalScore;
    }

    public void setStandardScore(int standardScore) {
        this.standardScore = standardScore;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }
}
