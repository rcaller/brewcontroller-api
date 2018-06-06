package uk.co.tertiarybrewery.brewapi.targettemps;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
@RunWith(MockitoJUnitRunner.class)
public class TargetTempsServiceTest {
    @Mock
    TargetTempsDao targetTempsDao;
    @Mock
    TargetTempPoint before;
    @Mock
    TargetTempPoint after;

    Optional<TargetTempPoint> beforeOption;
    Optional<TargetTempPoint> afterOption;
    @Before
    public void setUp() {
        beforeOption = Optional.of(before);
        afterOption = Optional.of(after);
    }

    @Test
    public void getTargetTempForFlatStep(){

        Mockito.when(targetTempsDao.getTargetTempPointBefore(anyInt())).thenReturn(beforeOption);
        Mockito.when(targetTempsDao.getTargetTempPointAfter(anyInt())).thenReturn(afterOption);
        Mockito.when(before.getSecondsElapsed()).thenReturn(0f);
        Mockito.when(after.getSecondsElapsed()).thenReturn(10f);
        Mockito.when(before.getTemp()).thenReturn(10f);
        Mockito.when(after.getTemp()).thenReturn(10f);
        TargetTempsService targetTempsService = new TargetTempsService(targetTempsDao);
        assertEquals(10f, targetTempsService.getTargetTemp(10));
    }
    @Test
    public void getTargetTempForIncreasingStep(){

        Mockito.when(targetTempsDao.getTargetTempPointBefore(anyInt())).thenReturn(beforeOption);
        Mockito.when(targetTempsDao.getTargetTempPointAfter(anyInt())).thenReturn(afterOption);
        Mockito.when(before.getSecondsElapsed()).thenReturn(0f);
        Mockito.when(after.getSecondsElapsed()).thenReturn(20f);
        Mockito.when(before.getTemp()).thenReturn(0f);
        Mockito.when(after.getTemp()).thenReturn(20f);
        TargetTempsService targetTempsService = new TargetTempsService(targetTempsDao);
        assertEquals(10f, targetTempsService.getTargetTemp(10));
    }
    @Test
    public void getTargetTempForStopped(){

        Mockito.when(targetTempsDao.getTargetTempPointBefore(anyInt())).thenReturn(beforeOption);
        Mockito.when(targetTempsDao.getTargetTempPointAfter(anyInt())).thenReturn(beforeOption);
        Mockito.when(before.getSecondsElapsed()).thenReturn(0f);
        Mockito.when(before.getTemp()).thenReturn(0f);;
        TargetTempsService targetTempsService = new TargetTempsService(targetTempsDao);
        assertEquals(0f, targetTempsService.getTargetTemp(0));
    }
}
