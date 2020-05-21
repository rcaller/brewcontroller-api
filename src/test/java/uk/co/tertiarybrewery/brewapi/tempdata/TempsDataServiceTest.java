package uk.co.tertiarybrewery.brewapi.tempdata;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import uk.co.tertiarybrewery.brewapi.targettemps.TargetTempsService;

import static junit.framework.TestCase.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TempsDataServiceTest {

    @Mock
    TempsDataDao tempsDataDao;

    @Mock
    TargetTempsService targetTempsService;

    @InjectMocks
    TempsDataService tds;

    @Test
    public void getTempsTest() {
        TempsData td = tds.getTemps();
        assertEquals(0, td.flow.size());
    }
}
