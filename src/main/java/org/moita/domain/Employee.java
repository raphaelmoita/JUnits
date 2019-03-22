package org.moita.domain;

import java.util.Objects;

public class Employee {

    private String name;
    private String job;
    private int level;

    public static Employee of(String name, String job, int level) {
        return new Employee(name, job, level);
    }

    public Employee(String name, String job, int level) {
        this.name = name;
        this.job = job;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return level == employee.level &&
                Objects.equals(name, employee.name) &&
                Objects.equals(job, employee.job);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, job, level);
    }
}
