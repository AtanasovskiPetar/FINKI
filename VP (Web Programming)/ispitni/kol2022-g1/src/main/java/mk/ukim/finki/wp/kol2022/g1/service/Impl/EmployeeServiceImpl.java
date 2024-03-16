package mk.ukim.finki.wp.kol2022.g1.service.Impl;

import mk.ukim.finki.wp.kol2022.g1.model.Employee;
import mk.ukim.finki.wp.kol2022.g1.model.EmployeeType;
import mk.ukim.finki.wp.kol2022.g1.model.Skill;
import mk.ukim.finki.wp.kol2022.g1.model.exceptions.InvalidEmployeeIdException;
import mk.ukim.finki.wp.kol2022.g1.model.exceptions.InvalidSkillIdException;
import mk.ukim.finki.wp.kol2022.g1.repository.EmployeeRepository;
import mk.ukim.finki.wp.kol2022.g1.repository.SkillRepository;
import mk.ukim.finki.wp.kol2022.g1.service.EmployeeService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService, UserDetailsService {

    private final EmployeeRepository employeeRepository;
    private final SkillRepository skillRepository;
    private final PasswordEncoder passwordEncoder;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, SkillRepository skillRepository, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.skillRepository = skillRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<Employee> listAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElseThrow(InvalidEmployeeIdException::new);
    }

    @Override
    public Employee create(String name, String email, String password, EmployeeType type, List<Long> skillId, LocalDate employmentDate) {
        List<Skill> skills = this.skillRepository.findAllById(skillId);
        Employee employee = new Employee(name, email, passwordEncoder.encode(password), type, skills, employmentDate);
        return this.employeeRepository.save(employee);
    }

    @Override
    public Employee update(Long id, String name, String email, String password, EmployeeType type, List<Long> skillId, LocalDate employmentDate) {
        Employee employee = findById(id);
        employee.setName(name);
        employee.setEmail(email);
        if (!password.equals(employee.getPassword())){
            password = passwordEncoder.encode(password);
        }
        employee.setPassword(password);
        employee.setType(type);
        List<Skill> skills = this.skillRepository.findAllById(skillId);
        employee.setSkills(skills);
        employee.setEmploymentDate(employmentDate);
        return this.employeeRepository.save(employee);
    }

    @Override
    public Employee delete(Long id) {
        Employee employee = findById(id);
        this.employeeRepository.delete(employee);
        return employee;
    }

    @Override
    public List<Employee> filter(Long skillId, Integer yearsOfService) {
        if (skillId == null && yearsOfService == null){
            return listAll();
        }
        else if (skillId == null){
            LocalDate nowDate = LocalDate.now();
            int year = nowDate.getYear()-yearsOfService;
            LocalDate date = LocalDate.of(year, nowDate.getMonthValue(), nowDate.getDayOfMonth());
            return employeeRepository.findAllByEmploymentDateBefore(date);
        }
        else if (yearsOfService == null){
            Skill skill = skillRepository.findById(skillId).orElseThrow(InvalidSkillIdException::new);
            return employeeRepository.findAllBySkillsContaining(skill);
        }
        else{
            Skill skill = skillRepository.findById(skillId).orElseThrow(InvalidSkillIdException::new);
            LocalDate nowDate = LocalDate.now();
            int year = nowDate.getYear()-yearsOfService;
            LocalDate date = LocalDate.of(year, nowDate.getMonthValue(), nowDate.getDayOfMonth());
            return employeeRepository.findAllBySkillsContainingAndEmploymentDateBefore(skill, date);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByEmail(username);
        return new org.springframework.security.core.userdetails.User(
                employee.getEmail(),
                employee.getPassword(),
                Collections.singletonList(employee.getType())
        );
    }
}
