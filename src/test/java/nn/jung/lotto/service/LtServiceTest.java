package nn.jung.lotto.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LtServiceTest {


    @Autowired
    LtService service;


    @Test
    void testdd() throws IOException {
        service.collectData("1022");
    }

    @Test
    void testDraw() throws IOException {
        service.drawLt();
    }
}
