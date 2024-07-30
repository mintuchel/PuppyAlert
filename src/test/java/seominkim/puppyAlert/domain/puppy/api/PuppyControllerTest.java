package seominkim.puppyAlert.domain.puppy.api;

import jakarta.persistence.EntityManager;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import seominkim.puppyAlert.domain.host.entity.Host;
import seominkim.puppyAlert.domain.menu.entity.Menu;
import seominkim.puppyAlert.domain.puppy.entity.Puppy;
import seominkim.puppyAlert.global.entity.Location;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class PuppyControllerTest {

    /*
    @Autowired private MockMvc mockMvc;
    @Autowired private EntityManager em;

    @BeforeEach
    public void beforeMockMvcTest(){
        Host host = new Host();
        host.setHostId("Ronaldo");
        host.setName("호날두");
        host.setNickName("임마나는사실수비수야");
        host.setPassword("7");
        host.setBirth(LocalDate.now());
        host.setAddress("레알 마드리드");
        host.setDetailAddress("산티아고 베르나베우");
        host.setLocation(new Location(100.12, 100.44));
        host.setPhoneNumber("010-4822-3636");

        Puppy puppy = new Puppy();
        puppy.setPuppyId("Messi");
        puppy.setName("메시");
        puppy.setNickName("요리조리비사이로막가드리블러");
        puppy.setPassword("10");
        puppy.setBirth(LocalDate.now());
        puppy.setAddress("바르셀로나");
        puppy.setDetailAddress("캄프누");
        puppy.setLocation(new Location(200.3562254, 200.1241));
        puppy.setPhoneNumber("010-1111-2222");

        em.persist(host);
        em.persist(puppy);

        Menu menu = new Menu();
        menu.setMenuName("testMenu");
        menu.setImageURL("testURL");

        // 엔티티 영속
        // 1차 캐시에 저장
        em.persist(menu);
    }

    @Test
    @Transactional
    @Rollback
    public void getMatchHistoryAPITest() throws Exception {
        String foodRequestContent = "{ " +
                "\"hostId\": \"Ronaldo\", " +
                "\"menuName\": \"testMenu\", " +
                "\"requestTime\": \"2024-07-30T12:34:56\", " +
                "\"status\": \"READY\" " +
                "}";

        mockMvc.perform(post("/api/v1/host/food")
                .contentType(MediaType.APPLICATION_JSON)
                .content(foodRequestContent))
                .andExpect(jsonPath("$.foodId").value(10))
                .andExpect(jsonPath("$.imageURL").value("testMenu"));
    }
 */
}