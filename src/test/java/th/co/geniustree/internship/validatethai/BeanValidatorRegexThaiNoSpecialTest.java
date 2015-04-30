/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package th.co.geniustree.internship.validatethai;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Pattern;
import static org.assertj.core.api.Assertions.assertThat;
import org.hibernate.validator.HibernateValidator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pramoth
 */
public class BeanValidatorRegexThaiNoSpecialTest {
     @Pattern(regexp = "[ก-์]+")
    private String noSpecialChar;
    private Validator validator;
    public BeanValidatorRegexThaiNoSpecialTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.byProvider(HibernateValidator.class).configure().buildValidatorFactory();
        validator = factory.getValidator();
    }
    
    @After
    public void tearDown() {
    }

@Test
    public void thaiCharactorShouldPass() {
        noSpecialChar="ปราโมทย์";
        assertThat(validator.validate(this)).isEmpty();
    }
    @Test
    public void specialThaiCharShouldNotPass() {
        noSpecialChar="ปราโมทย์๑๒๓๔";
        assertThat(validator.validate(this)).isNotEmpty();
    }
    @Test
    public void englishCaseShouldNotPass() {
        noSpecialChar="ปราโมทย์x";
        assertThat(validator.validate(this)).isNotEmpty();
    }
   
}
