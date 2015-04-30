package th.co.geniustree.internship.validatethai;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Pattern;
import org.assertj.core.api.Assert;
import org.hibernate.validator.HibernateValidator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

/**
 *
 * @author pramoth
 */
public class BeanValidatorRegexThaiTest {

    private Validator validator;
    
    @Pattern(regexp = "\\p{InThai}+")
    private String thaiWithSpecialChar;
    
    @Pattern(regexp = "[ก-์]")
    private String noSpecialChar;

    public BeanValidatorRegexThaiTest() {
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
        thaiWithSpecialChar="ปราโมทย์";
        assertThat(validator.validate(this)).isEmpty();
    }
    @Test
    public void specialThaiCharShouldPass() {
        thaiWithSpecialChar="ปราโมทย์๑๒๓๔";
        assertThat(validator.validate(this)).isEmpty();
    }
    @Test
    public void englishCaseShouldNotPass() {
        thaiWithSpecialChar="ปราโมทย์x";
        assertThat(validator.validate(this)).isNotEmpty();
    }
   
}
