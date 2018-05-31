package uk.co.tertiarybrewery.brewapi.targettemps;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

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
    @Test
    public void getTargetTempForFlatStep(){

        Mockito.when(targetTempsDao.getTargetTempPointBefore(anyInt())).thenReturn(before);
        Mockito.when(targetTempsDao.getTargetTempPointAfter(anyInt())).thenReturn(after);
        Mockito.when(before.getSecondsElapsed()).thenReturn(0f);
        Mockito.when(after.getSecondsElapsed()).thenReturn(10f);
        Mockito.when(before.getTemp()).thenReturn(10f);
        Mockito.when(after.getTemp()).thenReturn(10f);
        TargetTempsService targetTempsService = new TargetTempsService(targetTempsDao);
        assertEquals(10f, targetTempsService.getTargetTemp(10));
    }
    @Test
    public void getTargetTempForIncreasingStep(){

        Mockito.when(targetTempsDao.getTargetTempPointBefore(anyInt())).thenReturn(before);
        Mockito.when(targetTempsDao.getTargetTempPointAfter(anyInt())).thenReturn(after);
        Mockito.when(before.getSecondsElapsed()).thenReturn(0f);
        Mockito.when(after.getSecondsElapsed()).thenReturn(20f);
        Mockito.when(before.getTemp()).thenReturn(0f);
        Mockito.when(after.getTemp()).thenReturn(20f);
        TargetTempsService targetTempsService = new TargetTempsService(targetTempsDao);
        assertEquals(10f, targetTempsService.getTargetTemp(10));
    }
}
