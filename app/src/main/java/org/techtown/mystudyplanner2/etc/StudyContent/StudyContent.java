package org.techtown.mystudyplanner2.etc.StudyContent;

public class StudyContent {
    int _id;
    String subject;
    String content;
    int hour, min, sec;

    public StudyContent(int _id, String subject, String content, int hour, int min, int sec) {
        this._id = _id;
        this.subject = subject;
        this.content = content;
        this.hour = hour;
        this.min = min;
        this.sec = sec;
    }

    public int get_id() {
        return _id;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public int getHour() {
        return hour;
    }

    public int getMin() {
        return min;
    }

    public int getSec() {
        return sec;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setSec(int sec) {
        this.sec = sec;
    }
}
