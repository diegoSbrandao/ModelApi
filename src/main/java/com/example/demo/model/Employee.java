package com.example.demo.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "EMPLOYEE")
@Entity
@Builder
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private BigDecimal debtValue;
    private String nationalRegistrationId;

    //    @Pattern(regexp = "^(?!.*(\\d)\\1{7,})\\d{8}$", message = "Sequência de CEP inválido!")
//    @Pattern(regexp = "^(?!.*([0-9&&[^4]])(?:\\1{7}|44444444))\\d{8}$", message = "Sequência de CEP inválido!")
    private String zipCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Employee employee = (Employee) o;
        return getId() != null && Objects.equals(getId(), employee.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
