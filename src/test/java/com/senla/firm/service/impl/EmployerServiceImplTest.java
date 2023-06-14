package com.senla.firm.service.impl;

import com.senla.firm.constant.Position;
import com.senla.firm.departments.*;
import com.senla.firm.entity.Employer;
import com.senla.firm.service.IEmployerService;
import junit.framework.TestCase;

import java.util.HashSet;

public class EmployerServiceImplTest extends TestCase {
    private final IEmployerService employerService = new EmployerServiceImpl();
    private final HashSet<Employer> employers = Department.getEmployers();

    public void setUp() throws Exception {
        super.setUp();
        Department managementEmployer = new Management();
        Department accountingEmployer = new Accounting();
        Department developmentEmployer = new Development();
        Department engineeringEmployer = new Engineering();
        Department financeEmployer = new Finance();
        Department marketingEmployer = new Marketing();
        Department researchEmployer = new Research();
        // CEO, RESEARCH_ENGINEER, SALES, SOFTWARE_DEVELOPER, DEPARTMENT_MANAGER, ACCOUNTANT, COPYWRITER
        managementEmployer.addEmployer(new Employer("firstName1", "lastName1", Position.CEO, 999.9));
        accountingEmployer.addEmployer(new Employer("firstName2", "lastName2", Position.ACCOUNTANT, 433.7));
        developmentEmployer.addEmployer(new Employer("firstName3", "lastName3", Position.SOFTWARE_DEVELOPER, 653.2));
        engineeringEmployer.addEmployer(new Employer("firstName4", "lastName4", Position.DEPARTMENT_MANAGER, 865.8));
        financeEmployer.addEmployer(new Employer("firstName5", "lastName5", Position.SALES, 324.3));
        marketingEmployer.addEmployer(new Employer("firstName6", "lastName6", Position.COPYWRITER, 865.9));
        researchEmployer.addEmployer(new Employer("firstName7", "lastName7", Position.RESEARCH_ENGINEER, 895.2));
    }

    public void testGetTotalEmployerSalary() {
        System.out.println("total sum of salaries -> " + employerService.getTotalEmployerSalary(employers));
    }
}