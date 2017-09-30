package com.vina.test.demo.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * HelloController Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>九月 26, 2017</pre>
 */
@SpringBootTest(classes = SampleApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloControllerTest extends AbstractTestNGSpringContextTests{


    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * Method: hello()
     */
    @Test
    public void testHello() throws Exception {
        String message = this.restTemplate.getForObject("/hi", String.class);
        assertThat(message).isEqualTo("Hello World");

        ResponseEntity<String> entity = this.restTemplate.getForEntity("/hi", String.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(entity.getBody()).isEqualTo("Hello World");
    }


} 
