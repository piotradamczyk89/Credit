package com.inteca.credit.credit;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Credit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String creditName;
    private Double value;
    private Long customerId;

    public Credit(String creditName, Double value, Long customerId) {
        this.creditName = creditName;
        this.value = value;
        this.customerId = customerId;
    }
}
