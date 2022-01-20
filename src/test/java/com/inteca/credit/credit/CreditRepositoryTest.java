package com.inteca.credit.credit;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@DataJpaTest
class CreditRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CreditRepository creditRepository;

    @Test
    void find_by_customerId_then_return_credit() {

        //given
        Credit credit = new Credit();
        credit.setCustomerId(15L);
        entityManager.persist(credit);

        //when
        Credit result = creditRepository.findByCustomerId(15L);

        //then
        assertEquals(credit.getCustomerId(),result.getCustomerId());

    }

    @Test
    void given_credit_with_customerId_20_find_15_should_be_null () {
        //given
        Credit credit = new Credit();
        credit.setCustomerId(20L);
        entityManager.persist(credit);

        //when
        Credit result = creditRepository.findByCustomerId(15L);

        //then
        assertNull(result);
    }
}