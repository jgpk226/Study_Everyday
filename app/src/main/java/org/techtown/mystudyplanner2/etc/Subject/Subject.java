package org.techtown.mystudyplanner2.etc.Subject;

public class Subject {
    int _id;
    String subject;

    public Subject(int _id, String subject) {
        this._id = _id;
        this.subject = subject;
    }

    public int get_id() {
        return _id;
    }

    public String getSubject() {
        return subject;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
