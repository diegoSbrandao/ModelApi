package com.example.demo;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import com.example.demo.util.DocumentUtil;
import com.example.demo.util.Response;
import com.github.gilbertotorrezan.viacep.se.ViaCEPClient;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class DocumentUtilTest {

    @Test
    public void testIsValidCEP_CorrectCEP() throws IOException {
        String cep = "01001-000";

        DocumentUtil documentUtil = new DocumentUtil(new ViaCEPClient(), zipCode1 -> false);
        Response response = documentUtil.isValidCEP(cep);

        assertTrue(response.isValid());
        assertEquals(cep, response.getZipCode());
    }

    @Test
    public void testIsValidCEP_IncorrectCEP() throws IOException {
        String cep = "12345-678";

        DocumentUtil documentUtil = new DocumentUtil(new ViaCEPClient(), zipCode1 -> false);
        Response response = documentUtil.isValidCEP(cep);

        assertFalse(response.isValid());
        assertNull(response.getZipCode());
    }


}
