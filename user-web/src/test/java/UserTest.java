import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.forever.user.web.UserCenterApplication;
import com.forever.user.web.common.ResponseResult;
import com.forever.user.web.vo.req.UserReq;
import com.google.common.collect.Maps;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = UserCenterApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserTest {

    @LocalServerPort
    private int port;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void selectTest() {
        RestTemplate template = getRestTemplate();
        Map<String, Object> query = Maps.newHashMap();
        query.put("page", 1);
        query.put("pageSize", 20);
        ResponseEntity<ResponseResult> result = template.getForEntity("/user/select?page={page}&pageSize={pageSize}", ResponseResult.class, query);
        printResult(result);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void registerTest() throws JsonProcessingException {
        RestTemplate template = getRestTemplate();
        List<UserReq> userReqs = getUserReqs();
        HttpEntity<String> httpEntity = getHttpEntity(objectMapper.writeValueAsString(userReqs));
        ResponseEntity<ResponseResult> result = template.postForEntity("/user/register", httpEntity, ResponseResult.class);
        printResult(result);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void updateTest() {
        RestTemplate restTemplate = getRestTemplate();
        String jsonBody = "{\"id\": 1, \"pwd\": \"bbbbbbupdate\", \"username\": \"22222222\", \"email\": \"111111@mail.com\", \"phone\": 33333332}";
        HttpEntity<String> httpEntity = getHttpEntity(jsonBody);
        ResponseEntity<ResponseResult> responseEntity = restTemplate.postForEntity("/user/modify", httpEntity, ResponseResult.class);
        printResult(responseEntity);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void deleteTest() {
        RestTemplate restTemplate = getRestTemplate();
        String params = "[1,2]";
        HttpEntity<String> httpEntity = getHttpEntity(params);
        ResponseEntity<ResponseResult> responseEntity = restTemplate.postForEntity("/user/delete", httpEntity, ResponseResult.class);
        printResult(responseEntity);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    private HttpEntity<String> getHttpEntity(String jsonBody) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        return new HttpEntity<>(jsonBody, headers);
    }

    private List<UserReq> getUserReqs() {
        List<UserReq> userReqList = new ArrayList<>();
        UserReq userReq = new UserReq();
        userReq.setUsername("11111");
        userReq.setEmail("11111@mail.com");
        userReq.setPhone(11111111111L);
        userReqList.add(userReq);
        userReq = new UserReq();
        userReq.setUsername("22222");
        userReq.setEmail("222222@mail.com");
        userReq.setPhone(222222222222L);
        userReqList.add(userReq);
        return userReqList;
    }

    private void printResult(ResponseEntity<ResponseResult> response) {
        ResponseResult body = response.getBody();
        if (Objects.nonNull(body) && Objects.nonNull(body.getData())) {
            System.out.println(body.getMessage());
            System.out.println(body.getData().toString());
        }
    }

    private RestTemplate getRestTemplate() {
        return restTemplateBuilder.rootUri("http://localhost:" + port).build();
    }
}
