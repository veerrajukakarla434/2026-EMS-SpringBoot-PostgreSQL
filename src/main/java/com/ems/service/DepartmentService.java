package com.ems.service;

import com.ems.dto.DepartmentRequestDTO;
import com.ems.dto.DepartmentResponseDTO;
import com.ems.util.PageResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DepartmentService {

    DepartmentResponseDTO createDepartment(DepartmentRequestDTO requestDTO);

    DepartmentResponseDTO getDepartmentById(Long id);

    PageResponse<DepartmentResponseDTO> getAllDepartments(Pageable pageable);

    PageResponse<DepartmentResponseDTO> searchDepartments(String search, Pageable pageable);

    DepartmentResponseDTO updateDepartment(Long id, DepartmentRequestDTO requestDTO);

    void deleteDepartment(Long id);

    List<DepartmentResponseDTO> getAllDepartmentsList();
}