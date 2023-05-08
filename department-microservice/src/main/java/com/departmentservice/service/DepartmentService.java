package com.departmentservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.departmentservice.controller.DepartmentController;
import com.departmentservice.model.Department;
import com.departmentservice.repository.DepartmentRepository;
import com.departmentservice.util.SequenceGenaratorService;






@Service
public class DepartmentService {

	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private SequenceGenaratorService sequenceGenaratorService;

	public Department addDepartmenet(Department department) {
		
		department.setDepartmentId(sequenceGenaratorService.generateSequence(Department.SEQUENCE_NAME));
		return departmentRepository.save(department);
	}	
	
	public Department updateDepartment(long departmentId, Department department) {
		Department updateDepartment=departmentRepository.findByDepartmentId(departmentId);
		updateDepartment.setDepartmentName(department.getDepartmentName());
		updateDepartment.setDepartmentAddress(department.getDepartmentAddress());
		updateDepartment.setDepartmentCode(department.getDepartmentCode());
		return departmentRepository.save(updateDepartment);
	}

	public Department getDepartmentById(long departmentId) {
//		return departmentRepository.findById(departmentId)
		return departmentRepository.findByDepartmentId(departmentId);		
	}

	public void deleteDepartment(long departmentId) {
	   departmentRepository.deleteById(departmentId);	
	}

	public List<Department> getAllDepartments() {
		return departmentRepository.findAll();
	}

}
