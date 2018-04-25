package ru.moysclad.controller;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import ru.moysclad.MyApplication;
import ru.moysclad.utils.Response;
import ru.moysclad.utils.ResponseViewData;
import ru.moysclad.view.View;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MyApplication.class})
@WebAppConfiguration(value = "src/main/resources")
@Transactional
@DirtiesContext
public class ControllerTest {

    RestTemplate restTemplate = new RestTemplate();
    String patternURL = "http://localhost:8888/bankaccount";

    @Test
    public void create(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        View body = new View();
        body.id = "00006";
        HttpEntity entity = new HttpEntity<>(body, headers);

        ResponseEntity<ResponseViewData> responseEntity =
                restTemplate.exchange(patternURL + "/create", HttpMethod.POST, entity,
                        new ParameterizedTypeReference<ResponseViewData>(){
                        });

        ResponseViewData responseView = responseEntity.getBody();
        Assert.assertNotNull(responseView);

        Object data = responseView.getData();
        Assert.assertNotNull(data);

        String waitingResponse = "success";
        Assert.assertEquals(waitingResponse, data.toString());
    }

    @Test
    public void deposit(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        View body = new View();
        body.id = "00004";
        body.sum = 10000L;
        HttpEntity entity = new HttpEntity<>(body, headers);

        ResponseEntity<ResponseViewData> responseEntity =
                restTemplate.exchange(patternURL + "/deposit", HttpMethod.PUT, entity,
                        new ParameterizedTypeReference<ResponseViewData>(){
                        });

        ResponseViewData responseView = responseEntity.getBody();
        Assert.assertNotNull(responseView);

        Object data = responseView.getData();
        Assert.assertNotNull(data);

        String waitingResponse = "success";
        Assert.assertEquals(waitingResponse, data.toString());
    }

    @Test
    public void withdraw(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        View body = new View();
        body.id = "00004";
        body.sum = 10000L;
        HttpEntity entity = new HttpEntity<>(body, headers);

        ResponseEntity<ResponseViewData> responseEntity =
                restTemplate.exchange(patternURL + "/withdraw", HttpMethod.PUT, entity,
                        new ParameterizedTypeReference<ResponseViewData>(){
                        });

        ResponseViewData responseView = responseEntity.getBody();
        Assert.assertNotNull(responseView);

        Object data = responseView.getData();
        Assert.assertNotNull(data);

        String waitingResponse = "success";
        Assert.assertEquals(waitingResponse, data.toString());
    }

    @Test
    public void balance(){
        ResponseEntity<ResponseViewData> responseEntity =
                restTemplate.exchange(patternURL + "/00001", HttpMethod.GET, null,
                        new ParameterizedTypeReference<ResponseViewData>(){
                        });
        ResponseViewData responseView = responseEntity.getBody();
        Assert.assertNotNull(responseView);

        Object data = responseView.getData();
        Assert.assertNotNull(data);

        String waitingResponse = "{id=00001, sum=1000}";
        Assert.assertEquals(waitingResponse, data.toString());
    }

    @Test
    public void delete(){
        ResponseEntity<ResponseViewData> responseEntity =
                restTemplate.exchange(patternURL + "/00005", HttpMethod.DELETE, null,
                        new ParameterizedTypeReference<ResponseViewData>(){
                        });
        ResponseViewData responseView = responseEntity.getBody();
        Assert.assertNotNull(responseView);

        Object data = responseView.getData();
        Assert.assertNotNull(data);

        String waitingResponse = "success";
        Assert.assertEquals(waitingResponse, data.toString());
    }
}
