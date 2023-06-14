package com.senla.firm.service.impl;

import com.senla.firm.entity.Employer;
import com.senla.firm.service.IEmployerService;

import java.util.HashSet;

public class EmployerServiceImpl implements IEmployerService {

    @Override
    public double getTotalEmployerSalary(HashSet<Employer> employers) {
        return employers.stream().mapToDouble(Employer::getSalary).sum();
    }
}
