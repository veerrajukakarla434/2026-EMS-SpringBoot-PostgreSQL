package com.ems.service.impl;


import com.ems.dto.EmployeeRequestDTO;
import com.ems.dto.EmployeeResponseDTO;
import com.ems.entity.Department;
import com.ems.entity.Employee;
import com.ems.exception.DuplicateResourceException;
import com.ems.exception.ResourceNotFoundException;
import com.ems.repository.DepartmentRepository;
import com.ems.repository.EmployeeRepository;
import com.ems.service.EmployeeService;
import com.ems.util.MapperUtil;
import com.ems.util.PageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final MapperUtil mapperUtil;

    @Override
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO requestDTO) {
        log.info("Creating new employee: {}", requestDTO.getEmail());

        if (employeeRepository.existsByEmail(requestDTO.getEmail())) {
            throw new DuplicateResourceException("Employee with email '" + requestDTO.getEmail() + "' already exists");
        }

        Employee employee = mapperUtil.toEmployeeEntity(requestDTO);

        if (requestDTO.getDepartmentId() != null) {
            Department department = departmentRepository.findById(requestDTO.getDepartmentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Department not found with ID: " + requestDTO.getDepartmentId()));
            employee.setDepartment(department);
        }

        Employee savedEmployee = employeeRepository.save(employee);

        log.info("Employee created successfully with ID: {}", savedEmployee.getId());
        return mapperUtil.toEmployeeResponseDTO(savedEmployee);
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeResponseDTO getEmployeeById(Long id) {
        log.info("Fetching employee with ID: {}", id);

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + id));

        return mapperUtil.toEmployeeResponseDTO(employee);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<EmployeeResponseDTO> getAllEmployees(Pageable pageable) {
        log.info("Fetching all employees - Page: {}, Size: {}", pageable.getPageNumber(), pageable.getPageSize());

        Page<Employee> employeePage = employeeRepository.findAll(pageable);
        List<EmployeeResponseDTO> dtoList = employeePage.getContent().stream()
                .map(mapperUtil::toEmployeeResponseDTO)
                .collect(Collectors.toList());

        return PageResponse.of(employeePage, dtoList);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<EmployeeResponseDTO> searchEmployees(String search, Pageable pageable) {
        log.info("Searching employees with keyword: {}", search);

        Page<Employee> employeePage = employeeRepository.searchEmployees(search, pageable);
        List<EmployeeResponseDTO> dtoList = employeePage.getContent().stream()
                .map(mapperUtil::toEmployeeResponseDTO)
                .collect(Collectors.toList());

        return PageResponse.of(employeePage, dtoList);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<EmployeeResponseDTO> filterEmployees(Long departmentId, String position, String search, Pageable pageable) {
        log.info("Filtering employees - Department: {}, Position: {}, Search: {}", departmentId, position, search);

        Page<Employee> employeePage = employeeRepository.filterEmployees(departmentId, position, search, pageable);
        List<EmployeeResponseDTO> dtoList = employeePage.getContent().stream()
                .map(mapperUtil::toEmployeeResponseDTO)
                .collect(Collectors.toList());

        return PageResponse.of(employeePage, dtoList);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<EmployeeResponseDTO> getEmployeesByDepartment(Long departmentId, Pageable pageable) {
        log.info("Fetching employees for department ID: {}", departmentId);

        if (!departmentRepository.existsById(departmentId)) {
            throw new ResourceNotFoundException("Department not found with ID: " + departmentId);
        }

        Page<Employee> employeePage = employeeRepository.findByDepartmentId(departmentId, pageable);
        List<EmployeeResponseDTO> dtoList = employeePage.getContent().stream()
                .map(mapperUtil::toEmployeeResponseDTO)
                .collect(Collectors.toList());

        return PageResponse.of(employeePage, dtoList);
    }

    @Override
    public EmployeeResponseDTO updateEmployee(Long id, EmployeeRequestDTO requestDTO) {
        log.info("Updating employee with ID: {}", id);

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + id));

        if (!employee.getEmail().equals(requestDTO.getEmail()) &&
                employeeRepository.existsByEmail(requestDTO.getEmail())) {
            throw new DuplicateResourceException("Employee with email '" + requestDTO.getEmail() + "' already exists");
        }

        mapperUtil.updateEmployeeEntity(employee, requestDTO);

        if (requestDTO.getDepartmentId() != null) {
            Department department = departmentRepository.findById(requestDTO.getDepartmentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Department not found with ID: " + requestDTO.getDepartmentId()));
            employee.setDepartment(department);
        } else {
            employee.setDepartment(null);
        }

        Employee updatedEmployee = employeeRepository.save(employee);

        log.info("Employee updated successfully with ID: {}", id);
        return mapperUtil.toEmployeeResponseDTO(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {
        log.info("Deleting employee with ID: {}", id);

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + id));

        employeeRepository.delete(employee);
        log.info("Employee deleted successfully with ID: {}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeResponseDTO> getAllEmployeesList() {
        log.info("Fetching all employees list");

        return employeeRepository.findAll().stream()
                .map(mapperUtil::toEmployeeResponseDTO)
                .collect(Collectors.toList());
    }
}