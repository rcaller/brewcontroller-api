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
        Mockito.when(before.getSecondsElapsed()).thenReturn(0.0);
        Mockito.when(after.getSecondsElapsed()).thenReturn(10.0);
        Mockito.when(before.getTemp()).thenReturn(10.0);
        Mockito.when(after.getTemp()).thenReturn(10.0);
        TargetTempsService targetTempsService = new TargetTempsService(targetTempsDao);
        assertEquals(10.0, targetTempsService.getTargetTemp(10));
    }
    @Test
    public void getTargetTempForIncreasingStep(){

        Mockito.when(targetTempsDao.getTargetTempPointBefore(anyInt())).thenReturn(beforeOption);
        Mockito.when(targetTempsDao.getTargetTempPointAfter(anyInt())).thenReturn(afterOption);
        Mockito.when(before.getSecondsElapsed()).thenReturn(0.0);
        Mockito.when(after.getSecondsElapsed()).thenReturn(20.0);
        Mockito.when(before.getTemp()).thenReturn(0.0);
        Mockito.when(after.getTemp()).thenReturn(20.0);
        TargetTempsService targetTempsService = new TargetTempsService(targetTempsDao);
        assertEquals(10.0, targetTempsService.getTargetTemp(10));
    }
    @Test
    public void getTargetTempForStopped(){

        Mockito.when(targetTempsDao.getTargetTempPointBefore(anyInt())).thenReturn(beforeOption);
        Mockito.when(targetTempsDao.getTargetTempPointAfter(anyInt())).thenReturn(beforeOption);
        Mockito.when(before.getSecondsElapsed()).thenReturn(0.0);
        Mockito.when(before.getTemp()).thenReturn(0.0);
        TargetTempsService targetTempsService = new TargetTempsService(targetTempsDao);
        assertEquals(0.0, targetTempsService.getTargetTemp(0));
    }
}
