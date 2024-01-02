package com.sale.app.repository.impl;

import java.util.ArrayList;
import java.util.List;

import com.sale.app.domain.Employee;

public class EmployeeRepository {
	
	public List<Employee> findAll(){
		
		List<Employee> employees = new ArrayList<>();
		
		employees.add(new Employee("Geofani","Kebayoran Baru"));
		employees.add(new Employee("Arya","Pasar Minggu"));
		employees.add(new Employee("Bella","Ragunan"));
		
		return employees;
	}
}
