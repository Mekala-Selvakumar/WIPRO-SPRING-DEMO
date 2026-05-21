package com.springcore.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Employee {
	private int employeeId;
	private String employeeName;
	private int salary;
	
	@Autowired
	private Department  department;
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	@Override
	public String toString() {
		return String.format("Employee [employeeId=%s, employeeName=%s, salary=%s, department=%s]", employeeId,
				employeeName, salary, department);
	}
	
	

}
