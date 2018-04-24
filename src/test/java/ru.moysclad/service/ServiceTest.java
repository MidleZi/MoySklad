package ru.moysclad.service;

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
import ru.moysclad.model.Account;
import ru.moysclad.utils.ResponseViewData;
import ru.moysclad.view.View;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MyApplication.class})
@WebAppConfiguration(value = "src/main/resources")
@Transactional
@DirtiesContext
public class ServiceTest {

    RestTemplate restTemplate = new RestTemplate();
    String patternURL = "http://localhost:8888/bankaccount";


    @Test
    public void create(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        View body = new View();
        body.name = 00006L;

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
        body.name = 00003L;
        body.sum = 15000L;

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
    public void withdraw(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        View body = new View();
        body.name = 00002L;
        body.sum = 1000L;

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
    public void balance(){
        ResponseEntity<ResponseViewData> responseEntity =
                restTemplate.exchange(patternURL + "/00001", HttpMethod.GET, null,
                        new ParameterizedTypeReference<ResponseViewData>(){
                        });
        ResponseViewData responseView = responseEntity.getBody();
        Assert.assertNotNull(responseView);

        Object data = responseView.getData();
        Assert.assertNotNull(data);

        String waitingResponse = "{id=1, name=1, sum=1000}";
        Assert.assertEquals(waitingResponse, data.toString());
    }

    @Test
    public void delete(){
        ResponseEntity<ResponseViewData> responseEntity =
                restTemplate.exchange(patternURL + "/00004", HttpMethod.DELETE, null,
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
