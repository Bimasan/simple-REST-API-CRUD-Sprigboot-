package com.developmentid.crud;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developmentid.crud.db.Employee;
import com.developmentid.crud.db.EmployeeRepository;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	private EmployeeRepository employeeRepository;
	
	
 public EmployeeController(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

@GetMapping("/hello-word")
 public ResponseEntity<String> hello() {
	 return new ResponseEntity<>("Hello", HttpStatus.OK);
 }

@GetMapping
public ResponseEntity<?> getEmployee() {
    return new ResponseEntity<>(employeeRepository.findAll(), HttpStatus.OK);
}

@GetMapping("/{id}")
 public ResponseEntity<?>getEmployeeDetail(@PathVariable Long id){
	Employee employee = employeeRepository.findById(id).orElse(null);
	if (employee==null)
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	return new ResponseEntity<>(employee, HttpStatus.OK);
}
/**

@GetMapping("/{id}")
public ResponseEntity<?> getEmployeeDetail(@PathVariable Long id) {
    Employee employee = employeeRepository.findById(id).orElse(null);
    if (employee==null)
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    return new ResponseEntity<>(employee, HttpStatus.OK);
}
**/

 
@PostMapping
public ResponseEntity<?> postEmployee(@RequestBody Employee employee) {
	employeeRepository.save(employee);
	return new ResponseEntity<>(employee, HttpStatus.CREATED) ;
}

@PutMapping("/{id}")

public ResponseEntity<?> putEmployee(@RequestBody Employee employee, @PathVariable Long id){
employee.setId(id);
employeeRepository.save(employee);
return new ResponseEntity<>(employee, HttpStatus.OK);
}

@DeleteMapping("/{id}")
public ResponseEntity<?>deleteemployee(@PathVariable Long id){
	employeeRepository.deleteById(id);
	return new ResponseEntity<>(HttpStatus.ACCEPTED);
	
}

}
