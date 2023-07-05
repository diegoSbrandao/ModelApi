package com.example.demo.util;

import com.github.gilbertotorrezan.viacep.se.ViaCEPClient;
import com.github.gilbertotorrezan.viacep.shared.ViaCEPEndereco;

import java.io.IOException;
import java.util.stream.Stream;

public class DocumentUtil {

    public static Response isValidCEP(String zipCode) throws IOException {
        Response response = new Response();
        try {
            ViaCEPEndereco viaCEP = new ViaCEPClient().getEndereco(zipCode);
            if (isValidViaCEP(viaCEP)) {
                checkIfValidFieldsAreFilledWithViaCEPData(response, viaCEP);
            } else if (isValidSpecialCEP(zipCode)) {
                setSpecialCEPData(response, zipCode);
            }
        } catch (IllegalArgumentException e) {
            response.setValid(false);
        } catch (IOException e) {
            throw new IOException();
        }
        return response;
    }

    private static boolean isValidViaCEP(ViaCEPEndereco viaCEP) {
        return viaCEP != null && viaCEP.getCep() != null;
    }

    private static void fillResponseWithViaCEPData(Response response, ViaCEPEndereco viaCEP) {
        response.setValid(true);
        response.setZipCode(viaCEP.getCep());
        response.setLogradouro(viaCEP.getLogradouro());
        response.setBairro(viaCEP.getBairro());
        response.setCidade(viaCEP.getLocalidade());
        response.setUf(viaCEP.getUf());
    }

    private static void checkIfValidFieldsAreFilledWithViaCEPData(Response response, ViaCEPEndereco viaCEP) {
        fillResponseWithViaCEPData(response, viaCEP);
        boolean specialZipCode = Stream.of(
                        response.getLogradouro(),
                        response.getBairro(),
                        response.getCidade(),
                        response.getUf())
                .anyMatch(String::isEmpty);
        response.setSpecialZipCode(specialZipCode);
    }

    private static boolean isValidSpecialCEP(String zipCode) {
        return zipCode.equals("44444444") || zipCode.equals("44444-444");
    }

    private static void setSpecialCEPData(Response response, String zipCode) {
        response.setValid(true);
        response.setZipCode(zipCode);
        response.setSpecialZipCode(true);
    }

}
