package com.example.demo.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {
    private Boolean valid = false;
    private Boolean specialZipCode = false;
    private String zipCode;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String uf;

    public Boolean isValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
