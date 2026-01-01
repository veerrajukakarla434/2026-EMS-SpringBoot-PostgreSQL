package com.ems.util;


import com.ems.dto.*;
import com.ems.entity.Department;
import com.ems.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class MapperUtil {

    // Department mappings
    public DepartmentResponseDTO toDepartmentResponseDTO(Department department) {
        if (department == null) {
            return null;
        }

        DepartmentResponseDTO dto = new DepartmentResponseDTO();
        dto.setId(department.getId());
        dto.setName(department.getName());
        dto.setDescription(department.getDescription());
        dto.setLocation(department.getLocation());
        dto.setEmployeeCount(department.getEmployees() != null ? department.getEmployees().size() : 0);
        dto.setCreatedAt(department.getCreatedAt());
        dto.setUpdatedAt(department.getUpdatedAt());

        return dto;
    }

    public DepartmentSummaryDTO toDepartmentSummaryDTO(Department department) {
        if (department == null) {
            return null;
        }

        DepartmentSummaryDTO dto = new DepartmentSummaryDTO();
        dto.setId(department.getId());
        dto.setName(department.getName());
        dto.setLocation(department.getLocation());
        dto.setEmployeeCount(department.getEmployees() != null ? department.getEmployees().size() : 0);

        return dto;
    }

    public Department toDepartmentEntity(DepartmentRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Department department = new Department();
        department.setName(dto.getName());
        department.setDescription(dto.getDescription());
        department.setLocation(dto.getLocation());

        return department;
    }

    public void updateDepartmentEntity(Department department, DepartmentRequestDTO dto) {
        if (department == null || dto == null) {
            return;
        }

        department.setName(dto.getName());
        department.setDescription(dto.getDescription());
        department.setLocation(dto.getLocation());
    }

    // Employee mappings
    public EmployeeResponseDTO toEmployeeResponseDTO(Employee employee) {
        if (employee == null) {
            return null;
        }

        EmployeeResponseDTO dto = new EmployeeResponseDTO();
        dto.setId(employee.getId());
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        dto.setEmail(employee.getEmail());
        dto.setPhone(employee.getPhone());
        dto.setPosition(employee.getPosition());
        dto.setSalary(employee.getSalary());
        dto.setHireDate(employee.getHireDate());
        dto.setDepartment(toDepartmentSummaryDTO(employee.getDepartment()));
        dto.setCreatedAt(employee.getCreatedAt());
        dto.setUpdatedAt(employee.getUpdatedAt());

        return dto;
    }

    public EmployeeSummaryDTO toEmployeeSummaryDTO(Employee employee) {
        if (employee == null) {
            return null;
        }

        EmployeeSummaryDTO dto = new EmployeeSummaryDTO();
        dto.setId(employee.getId());
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        dto.setEmail(employee.getEmail());
        dto.setPosition(employee.getPosition());
        dto.setDepartmentName(employee.getDepartment() != null ? employee.getDepartment().getName() : null);

        return dto;
    }

    public Employee toEmployeeEntity(EmployeeRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Employee employee = new Employee();
        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setEmail(dto.getEmail());
        employee.setPhone(dto.getPhone());
        employee.setPosition(dto.getPosition());
        employee.setSalary(dto.getSalary());
        employee.setHireDate(dto.getHireDate());

        return employee;
    }

    public void updateEmployeeEntity(Employee employee, EmployeeRequestDTO dto) {
        if (employee == null || dto == null) {
            return;
        }

        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setEmail(dto.getEmail());
        employee.setPhone(dto.getPhone());
        employee.setPosition(dto.getPosition());
        employee.setSalary(dto.getSalary());
        employee.setHireDate(dto.getHireDate());
    }
}
