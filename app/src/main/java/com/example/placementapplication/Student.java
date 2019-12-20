package com.example.placementapplication;

public class Student {
    private String company,student_name;

    public Student(String company, String student_name) {
        this.company = company;
        this.student_name = student_name;
    }

    public Student() {
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "company='" + company + '\'' +
                ", student_name='" + student_name + '\'' +
                '}';
    }
}
