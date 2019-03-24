package org.moita.report;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.moita.domain.Employee;
import org.moita.filter.EmployeeFilter;
import org.moita.formater.Formater;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.moita.domain.Employee.of;

@RunWith(MockitoJUnitRunner.class)
public class CompanyReporterTest {

    @Captor
    private ArgumentCaptor<List<Employee>> captorListEmployee;
    @Captor
    private ArgumentCaptor<Integer> captorLevel;

    @Spy
    private Formater formater;

    @Mock
    private EmployeeFilter employeeFilter;

    @InjectMocks
    private CompanyReporter companyReporter;

    @Before
    public void setUp() {

        // disable filtering
        when(employeeFilter.filterByLevel(anyList(), anyInt()))
                .thenAnswer(i -> i.getArguments()[0]);

        when(formater.beautify("Raphael"))
                .thenAnswer(i -> i.getArgument(0) + " :)");

    }

    @Test
    public void shouldGenerateFullReportWhenCalled() {

        // given
        when(employeeFilter.filterByLevel(anyList(), anyInt()))
                .thenAnswer(i -> i.getArgument(0));

        // or
        //when(employeeFilter.filterByLevel(anyList(), anyInt()))
        //        .then(returnsFirstArg());
        // or
        //doAnswer(returnsFirstArg())
        //        .when(employeeFilter).filterByLevel(anyList(), anyInt());

        int expectedNoOfReports = 4;

        List<Employee> employees = Arrays.asList(
                of("Raphael", "dev", 9),
                of("Mari", "qa", 8),
                of("Ze", "qa", 5),
                of("Maria", "dev", 7));

        // when
        List<String> report = companyReporter.generateReportByLevel(employees, 9);

        // then
        verify(employeeFilter).filterByLevel(captorListEmployee.capture(), captorLevel.capture());
        assertEquals(employees, captorListEmployee.getValue());
        assertEquals(Integer.valueOf(9), captorLevel.getValue());

        assertEquals(expectedNoOfReports, report.size());
        assertTrue(report.contains("Raphael :)"));
        assertTrue(report.contains("MARI"));
    }

    @Test
    public void shouldGenerateReporteBylevelWhenCalled() {

        // given
        int expectedNoOfReports = 1;

        List<Employee> employees = Arrays.asList(
                of("Raphael", "dev", 9),
                of("Mari", "qa", 8),
                of("Ze", "qa", 5),
                of("Maria", "dev", 7));

        given(employeeFilter.filterByLevel(employees, 9))
                .willReturn(singletonList(
                        of("Raphael", "dev", 9)));

        // when
        List<String> report = companyReporter.generateReportByLevel(employees, 9);

        // then
        assertEquals(expectedNoOfReports, report.size());
        assertTrue(report.contains("Raphael :)"));
    }
}
