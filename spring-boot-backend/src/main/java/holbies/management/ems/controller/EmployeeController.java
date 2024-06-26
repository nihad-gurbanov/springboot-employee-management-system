package holbies.management.ems.controller;

import holbies.management.ems.dto.EmployeeDto;
import holbies.management.ems.service.EmployeeService;
import lombok.AllArgsConstructor;
//import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/employees")
//@CrossOrigin("*")
public class EmployeeController {
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping({"{id}"})
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long employeeId) {
        EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok(employeeDto);
    }

//    @GetMapping("/") is mapping to /api/employees/.
//    However, this might conflict with the @GetMapping("{id}")
//    for fetching a single employee by ID (/api/employees/{id}).
//    To avoid conflicts, it's better to specify a different path
//    for fetching all employees, such as /api/employees/all
//    or simply remove the leading slash in @GetMapping("/").

    @GetMapping("")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        List<EmployeeDto> employeeDtos = employeeService.getAllEmployees();
        return ResponseEntity.ok(employeeDtos);
    }

    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long employeeId, @RequestBody EmployeeDto updatedEmployeeDto) {
        EmployeeDto employeeDto = employeeService.updateEmployee(employeeId, updatedEmployeeDto);

        return ResponseEntity.ok(employeeDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId) {
        employeeService.deleteEmployee(employeeId);

        return ResponseEntity.ok("Employee deleted successfully!");
    }
}
