package com.ems.service.impl;


import com.ems.dto.DepartmentRequestDTO;
import com.ems.dto.DepartmentResponseDTO;
import com.ems.entity.Department;
import com.ems.exception.ResourceNotFoundException;
import com.ems.exception.DuplicateResourceException;
import com.ems.repository.DepartmentRepository;
import com.ems.service.DepartmentService;
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
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final MapperUtil mapperUtil;

    @Override
    public DepartmentResponseDTO createDepartment(DepartmentRequestDTO requestDTO) {
        log.info("Creating new department: {}", requestDTO.getName());

        if (departmentRepository.existsByName(requestDTO.getName())) {
            throw new DuplicateResourceException("Department with name '" + requestDTO.getName() + "' already exists");
        }

        Department department = mapperUtil.toDepartmentEntity(requestDTO);
        Department savedDepartment = departmentRepository.save(department);

        log.info("Department created successfully with ID: {}", savedDepartment.getId());
        return mapperUtil.toDepartmentResponseDTO(savedDepartment);
    }

    @Override
    @Transactional(readOnly = true)
    public DepartmentResponseDTO getDepartmentById(Long id) {
        log.info("Fetching department with ID: {}", id);

        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with ID: " + id));

        return mapperUtil.toDepartmentResponseDTO(department);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<DepartmentResponseDTO> getAllDepartments(Pageable pageable) {
        log.info("Fetching all departments - Page: {}, Size: {}", pageable.getPageNumber(), pageable.getPageSize());

        Page<Department> departmentPage = departmentRepository.findAll(pageable);
        List<DepartmentResponseDTO> dtoList = departmentPage.getContent().stream()
                .map(mapperUtil::toDepartmentResponseDTO)
                .collect(Collectors.toList());

        return PageResponse.of(departmentPage, dtoList);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<DepartmentResponseDTO> searchDepartments(String search, Pageable pageable) {
        log.info("Searching departments with keyword: {}", search);

        Page<Department> departmentPage = departmentRepository.searchDepartments(search, pageable);
        List<DepartmentResponseDTO> dtoList = departmentPage.getContent().stream()
                .map(mapperUtil::toDepartmentResponseDTO)
                .collect(Collectors.toList());

        return PageResponse.of(departmentPage, dtoList);
    }

    @Override
    public DepartmentResponseDTO updateDepartment(Long id, DepartmentRequestDTO requestDTO) {
        log.info("Updating department with ID: {}", id);

        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with ID: " + id));

        if (!department.getName().equals(requestDTO.getName()) &&
                departmentRepository.existsByName(requestDTO.getName())) {
            throw new DuplicateResourceException("Department with name '" + requestDTO.getName() + "' already exists");
        }

        mapperUtil.updateDepartmentEntity(department, requestDTO);
        Department updatedDepartment = departmentRepository.save(department);

        log.info("Department updated successfully with ID: {}", id);
        return mapperUtil.toDepartmentResponseDTO(updatedDepartment);
    }

    @Override
    public void deleteDepartment(Long id) {
        log.info("Deleting department with ID: {}", id);

        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with ID: " + id));

        departmentRepository.delete(department);
        log.info("Department deleted successfully with ID: {}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DepartmentResponseDTO> getAllDepartmentsList() {
        log.info("Fetching all departments list");

        return departmentRepository.findAll().stream()
                .map(mapperUtil::toDepartmentResponseDTO)
                .collect(Collectors.toList());
    }
}
