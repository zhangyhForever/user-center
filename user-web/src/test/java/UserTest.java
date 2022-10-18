import com.fasterxml.jackson.databind.ObjectMapper;
import com.forever.user.web.UserCenterApplication;
import com.forever.user.web.common.ResponseResult;
import com.forever.user.web.vo.req.UserQuery;
import com.forever.user.web.vo.req.UserReq;
import com.forever.user.web.vo.resp.UserResp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = UserCenterApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserTest {

    @LocalServerPort
    private int port;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    private ObjectMapper objectMapper = new ObjectMapper();

    private List<UserReq> userDOS() {
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

    @Test
    void registerTest() {
        RestTemplate template = restTemplateBuilder.rootUri("http://localhost:" + port).build();
        UserQuery userReq = new UserQuery();
        userReq.setPage(1);
        userReq.setPageSize(20);
        ResponseEntity<ResponseResult> result = template.exchange(RequestEntity.options("/user/select", userReq).build(), ResponseResult.class);
        ResponseResult body = result.getBody();
        if (Objects.nonNull(body) && body.getSuccess()) {
            List<UserResp> data = (List<UserResp>) body.getData();
            data.forEach(System.out::println);
        }
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void updateTest() {
        UserReq userReq = new UserReq();
        userReq.setId(1L);
        userReq.setPwd("aaaaaaaaa");
        userReq.setUsername("111112");
        userReq.setEmail("111112@mail.com");
        userReq.setPhone(111111111112L);
    }

    @Test
    void deleteTest() {
//        userController.delUser(Lists.newArrayList(1L,2L));
    }

    @Test
    void selectTest() {
        UserQuery userReq = new UserQuery();
        userReq.setPage(1);
        userReq.setPageSize(20);
//        userReq.setUsername("11111");
//        userReq.setEmail("11111@mail.com");
//        userReq.setPhone(11111111111L);

//        ResponseResult<List<UserResp>> responseResult = userController.selectUser(userReq);
//        if (responseResult.getSuccess()) {
//            List<UserResp> userResps = responseResult.getData();
//            userResps.forEach(System.out::println);
//        }
    }
}
