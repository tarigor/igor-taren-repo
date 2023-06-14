package com.senla.firm.service;

import com.senla.firm.entity.Employer;

import java.util.HashSet;

public interface IEmployerService {
    double getTotalEmployerSalary(HashSet<Employer> employers);
}
