package uk.co.tertiarybrewery.brewapi.uploadcontroller;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.net.URL;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UploadControllerIntegrationTest {


    @Autowired
    private MockMvc mvc;

    @Test
    public void uploadXML() throws Exception {
        URL url = Resources.getResource("upload.xml");
        String xml = Resources.toString(url, Charsets.UTF_8);
        MockMultipartFile firstFile = new MockMultipartFile("file", "upload.xml", "text/xml", xml.getBytes());

        MvcResult result = mvc.perform(MockMvcRequestBuilders.fileUpload("/upload").file(firstFile)).andExpect(status().isOk()).andExpect(content().string("")).andReturn();

    }

}
