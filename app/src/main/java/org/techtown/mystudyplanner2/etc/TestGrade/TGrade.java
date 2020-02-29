package org.techtown.mystudyplanner2.etc.TestGrade;

public class TGrade {
    int _id;
    String testName;
    String date;
    String subject;
    int score;
    int perfect;

    public TGrade(int _id, String testName, String date, String subject, int score, int perfect) {
        this._id = _id;
        this.testName = testName;
        this.date = date;
        this.subject = subject;
        this.score = score;
        this.perfect = perfect;
    }

    public int get_id() {
        return _id;
    }

    public String getTestName() {
        return testName;
    }

    public String getDate() {
        return date;
    }

    public String getSubject() {
        return subject;
    }

    public int getScore() {
        return score;
    }

    public int getPerfect() {
        return perfect;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setPerfect(int perfect) {
        this.perfect = perfect;
    }
}
