package uk.co.tertiarybrewery.brewapi.MashProfile;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import uk.co.tertiarybrewery.brewapi.mashprofile.MashProfileBuilder;
import uk.co.tertiarybrewery.brewapi.targettemps.TargetTempsDao;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MashProfileBuilderTest {

    @Mock
    TargetTempsDao targetTempsDao;


    @Test
    public void addStartingStep() {
        when(targetTempsDao.getLastTempPointTime()).thenReturn(0);
        MashProfileBuilder mashProfileBuilder = new MashProfileBuilder(targetTempsDao);
        mashProfileBuilder.addStep(5.0f, 0.0f, 1.0f);
        verify(targetTempsDao, times(1)).addTempPoint(0, 0.0f);
        verify(targetTempsDao, times(1)).addTempPoint(300, 0.0f);
    }
}
