package org.techtown.mystudyplanner2.etc.DDay;

public class DDay {

    int _id;
    String name;
    int year;
    int month;
    int day;

    public DDay(int _id, String name, int year, int month, int day) {
        this._id = _id;
        this.name = name;
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
