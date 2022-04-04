package daisy.springbootgettingstarted;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest
@ExtendWith(OutputCaptureExtension.class) // 추가
class SampleControllerTest {

    @MockBean
    SampleService sampleService;

    @Autowired
    MockMvc mockMvc;

    @Test
        // 메서드 파라미터 추가
    void hello(CapturedOutput capturedOutput) throws Exception {
        when(sampleService.getName()).thenReturn("titu");

        mockMvc.perform(get("/hello"))
                .andExpect(content().string("hello titu"));

        assertThat(capturedOutput.toString())
                .contains("hihi")
                .contains("skip");
    }
}