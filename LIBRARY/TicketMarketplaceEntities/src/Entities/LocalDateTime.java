package Entities; // atau sesuaikan sesuai package kamu

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "localDateTime", propOrder = {
    "year",
    "month",
    "day",
    "hour",
    "minute",
    "second"
})
public class LocalDateTime {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;

    // Getter & Setter
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public int getMonth() { return month; }
    public void setMonth(int month) { this.month = month; }

    public int getDay() { return day; }
    public void setDay(int day) { this.day = day; }

    public int getHour() { return hour; }
    public void setHour(int hour) { this.hour = hour; }

    public int getMinute() { return minute; }
    public void setMinute(int minute) { this.minute = minute; }

    public int getSecond() { return second; }
    public void setSecond(int second) { this.second = second; }
}
