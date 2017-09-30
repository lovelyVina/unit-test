package sample.test.web;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import sample.test.BaseTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * HelloController Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>九月 26, 2017</pre>
 */
public class HelloControllerTest extends BaseTest {

    @Autowired
    private TestRestTemplate restTemplate;


    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: hello()
     */
    @Test
    public void testHello() throws Exception {
        String message = this.restTemplate.getForObject("/hi", String.class);
        assertThat(message).isEqualTo("Hello World");
    }


} 
