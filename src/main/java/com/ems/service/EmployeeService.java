package com.ems.service;


import com.ems.dto.EmployeeRequestDTO;
import com.ems.dto.EmployeeResponseDTO;
import com.ems.util.PageResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {

    EmployeeResponseDTO createEmployee(EmployeeRequestDTO requestDTO);

    EmployeeResponseDTO getEmployeeById(Long id);

    PageResponse<EmployeeResponseDTO> getAllEmployees(Pageable pageable);

    PageResponse<EmployeeResponseDTO> searchEmployees(String search, Pageable pageable);

    PageResponse<EmployeeResponseDTO> filterEmployees(
            Long departmentId,
            String position,
            String search,
            Pageable pageable
    );

    PageResponse<EmployeeResponseDTO> getEmployeesByDepartment(Long departmentId, Pageable pageable);

    EmployeeResponseDTO updateEmployee(Long id, EmployeeRequestDTO requestDTO);

    void deleteEmployee(Long id);

    List<EmployeeResponseDTO> getAllEmployeesList();
}