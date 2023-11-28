package Model;

import java.time.LocalDate;
import java.util.Arrays;

public class MyDate {
  private int day, month, year;

  public MyDate(int day, int month, int year) {
    set(day, month, year);
  }

  public MyDate(int day, String monthName, int year) {
    set(day, convertToMonthNumber(monthName), year);
  }

  public MyDate() {
    LocalDate today = LocalDate.now();
    set(today.getDayOfMonth(), today.getMonthValue(), today.getYear());
  }

  public void set(int day, int month, int year) {
    if (month < 1) {
      month = 1;
    }
    if (month > 12) {
      month = 12;
    }
    this.month = month;
    if (year < 0) {
      year = -year;
    }
    this.year = year;
    if (day < 1) {
      day = 1;
    }
    if (day > numberOfDaysInMonth()) {
      day = numberOfDaysInMonth();
    }
    this.day = day;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public int getDay() {
    return day;
  }

  public int getMonth() {
    return month;
  }

  public int getYear() {
    return year;
  }

  public boolean isLeapYear() {
    return ((year % 400 == 0) || (year % 4 == 0 && year % 100 != 0));
  }

  public int numberOfDaysInMonth() {
    if (month == 2) {
      if (isLeapYear()) {
        return 29;
      }
      else {
        return 28;
      }
    }
    else if (Arrays.asList(1, 3, 5, 7, 8, 10, 12).contains(month)) {
      return 31;
    }
    else {
      return 30;
    }
  }

  public int yearsBetween(MyDate other) { //the smaller date will be in variables 1
    int x1 = other.day, x2 = day, y1 = other.month, y2 = month, z1 = other.year, z2 = year;
    if (isBefore(other)) {
      x1 = day;
      y1 = month;
      z1 = year;
      x2 = other.day;
      y2 = other.month;
      z2 = other.year;
    }
    if (y2 > y1 || y2 == y1 && x2 >= x1){
      return z2 - z1;
    }
    return (z2 - z1) -1;
  }

  public void stepForwardOneDay() {
    day++;
    if (day > numberOfDaysInMonth()) {
      day = 1;
      month++;
      if (month == 13) {
        month = 1;
        year++;
      }
    }
  }

  public void stepForwardManyDays(int x) {
    for (int i = 0; i < x; i++) {
      stepForwardOneDay();
    }
  }

  public boolean isBefore(MyDate other) {
    int totalDaysOne = getYear() * 10000 + getMonth() * 100 + getDay();
    int totalDatsTwo =
        other.getYear() * 10000 + other.getMonth() * 100 + other.getDay();
    return (totalDaysOne < totalDatsTwo);
  }

  public String dayOfWeek() {
    int q, m, k, j, h;
    q = day;
    m = month;
    if (m < 3) {
      m += 12;
      k = (year - 1) % 100;
      j = (year - 1) / 100;
    } //If month is jan or feb, add 12 to month, process year as - 1
    else {
      k = year % 100;
      j = year / 100;
    } //Else process everything normally
    h = (q + (13 * (m + 1)) / 5 + k + (k / 4) + (j / 4) + 5 * j) % 7;
    switch (h) {
      case 0:
        return "Saturday";
      case 1:
        return "Sunday";
      case 2:
        return "Monday";
      case 3:
        return "Tuesday";
      case 4:
        return "Wednesday";
      case 5:
        return "Thursday";
      case 6:
        return "Friday";
      default:
        return "Error";
    }
  }

  public String getMonthName() {
    switch (month) {
      case 1:
        return "January";
      case 2:
        return "February";
      case 3:
        return "March";
      case 4:
        return "April";
      case 5:
        return "May";
      case 6:
        return "June";
      case 7:
        return "July";
      case 8:
        return "August";
      case 9:
        return "September";
      case 10:
        return "October";
      case 11:
        return "November";
      case 12:
        return "December";
      default:
        return "incorrect month detected";
    }
  }

  public static int convertToMonthNumber(String monthName) {
    switch (monthName) {
      case "February":
        return 2;
      case "March":
        return 3;
      case "April":
        return 4;
      case "May":
        return 5;
      case "June":
        return 6;
      case "July":
        return 7;
      case "August":
        return 8;
      case "September":
        return 9;
      case "October":
        return 10;
      case "November":
        return 11;
      case "December":
        return 12;
      default:
        return 1;
    }
  }

  public String toString() {
    String all = "";
    if (day < 10) {
      all += "0" + day;
    }
    else {
      all += day;
    }
    if (month < 10) {
      all += "/0" + month;
    }
    else {
      all += "/" + month;
    }
    if (year < 10) {
      all += "/000" + year;
    }
    else if (year < 100) {
      all += "/00" + year;
    }
    else if (year < 1000) {
      all += "/0" + year;
    }
    else {
      all += "/" + year;
    }
    return all;
  }

  public boolean equals(Object obj) {
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    MyDate other = (MyDate) obj;
    return day == other.day && month == other.month && year == other.year;
  }

  public MyDate copy() {
    MyDate copy = new MyDate(day, month, year);
    return copy;
  }
}
