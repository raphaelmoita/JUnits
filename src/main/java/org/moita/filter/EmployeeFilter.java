package org.moita.filter;

import org.moita.domain.Employee;

import java.util.List;
import java.util.stream.Collectors;

public class EmployeeFilter {

    public List<Employee> filterByLevel(List<Employee> list, int level) {
        return list.stream().filter(e -> level == e.getLevel()).collect(Collectors.toList());
    }
}
