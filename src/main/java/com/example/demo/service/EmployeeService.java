package com.example.demo.service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.util.DocumentUtil;
import com.example.demo.util.Response;
import com.github.gilbertotorrezan.viacep.se.ViaCEPClient;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class EmployeeService {

    private final EmployeeRepository repository;
    private static final BigDecimal CPF_LIMIT_VALUE = new BigDecimal("100000");
    private static final BigDecimal CNPJ_LIMIT_VALUE = new BigDecimal("500000");


    @Transactional
    public List<Employee> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Optional<Employee> findById(Long id) {
        return repository.findById(id);
    }


    @Transactional
    public Employee createEmployee(Employee employee) {

        try {
            employee.setId(null);
            employee = repository.save(employee);
            employee.setId(employee.getId());
        } catch (ConstraintViolationException ex) {
            ex.getMessage();
        }
        return employee;
    }

    @Transactional
    public Response validateCEP(String zipCode) {
        Response response = new Response();
        try {
            DocumentUtil documentUtil = new DocumentUtil(new ViaCEPClient());
            Response cepResponse = documentUtil.isValidCEP(zipCode);
            if (cepResponse.isValid()) {
                fillResponseWithCEPData(response, cepResponse);
            }
        } catch (IOException ex) {
            response.setValid(false);
            response.setZipCode(zipCode);
        }

        return response;
    }

    private void fillResponseWithCEPData(Response response, Response cepResponse) {
        response.setValid(true);
        response.setSpecialZipCode(cepResponse.getSpecialZipCode());
        response.setZipCode(cepResponse.getZipCode());
        response.setLogradouro(cepResponse.getLogradouro());
        response.setBairro(cepResponse.getBairro());
        response.setCidade(cepResponse.getCidade());
        response.setUf(cepResponse.getUf());
    }


    public void deleteEmployee(Long id) {
        Optional<Employee> employeeIdIsValid = repository.findById(id);

        if (employeeIdIsValid.isEmpty()) {
            throw new EntityNotFoundException("Id Not Found! " + id);
        }
        repository.deleteById(id);
    }

    private Employee documentValidator(Employee employee) {

        Optional<Employee> employee1 = findById(employee.getId());
        String nationalDocument = employee1.get().getNationalRegistrationId();
        BigDecimal SET_CONFIGURE = (nationalDocument.length() == 11) ? CPF_LIMIT_VALUE : CNPJ_LIMIT_VALUE;

        BigDecimal initialValue = employee1.get().getDebtValue();
        BigDecimal resultValue = initialValue.add(employee.getDebtValue());
        employee.setDebtValue(resultValue);

        if (resultValue.compareTo(SET_CONFIGURE) == -1 || resultValue.compareTo(SET_CONFIGURE) == 0) {
            employee = repository.save(employee);
            employee.setId(employee.getId());
            return employee;
        } else {
            throw new IllegalArgumentException();
        }
    }

}
