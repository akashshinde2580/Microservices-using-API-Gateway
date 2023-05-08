package com.departmentservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.departmentservice.model.Department;


public interface DepartmentRepository extends MongoRepository<Department, Long> {
	public Department findByDepartmentId(Long departmentId);

}
