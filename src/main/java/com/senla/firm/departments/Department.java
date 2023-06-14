package com.senla.firm.departments;

import com.senla.firm.entity.Employer;

import java.util.HashSet;

public abstract class Department {
    protected static HashSet<Employer> employers = new HashSet<>();

    public static HashSet<Employer> getEmployers() {
        return employers;
    }

    public void addEmployer(Employer employer) {
        employers.add(employer);
    }
}
