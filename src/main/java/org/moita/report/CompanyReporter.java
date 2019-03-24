package org.moita.report;

import org.moita.domain.Employee;
import org.moita.filter.EmployeeFilter;
import org.moita.formater.Formater;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class CompanyReporter {

    private final EmployeeFilter filter;
    private final Formater formater;

    public CompanyReporter(EmployeeFilter filter, Formater formater) {
        this.filter = filter;
        this.formater = formater;
    }

    public List<String> generateReportByLevel(List<Employee> employees, int level) {
        List<Employee> filteredEmployees = filter.filterByLevel(employees, level);
        return filteredEmployees.stream()
                .map(employee -> formater.beautify(employee.getName())).collect(toList());
    }
}
