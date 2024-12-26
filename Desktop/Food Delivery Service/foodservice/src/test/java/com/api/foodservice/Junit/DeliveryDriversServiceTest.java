package com.api.foodservice.Junit;
import com.api.foodservice.entity.DeliveryDrivers;
import com.api.foodservice.repository.DeliveryDriverRepository;
import com.api.foodservice.repository.OrdersRepository;
import com.api.foodservice.service.DeliveryDriverService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class DeliveryDriversServiceTest
{
    @InjectMocks
    private DeliveryDriverService deliveryDriversService;
 
    @Mock
    private DeliveryDriverRepository deliveryDriversRepository;
 
    @Mock
    private OrdersRepository ordersRepository;
 
    @Test
    public void testGetAllDeliveryDrivers()
    {
        DeliveryDrivers driver1 = new DeliveryDrivers();
        driver1.setDriverId(1);
        driver1.setDriverName("John Doe");
        DeliveryDrivers driver2 = new DeliveryDrivers();
        driver2.setDriverId(2);
        driver2.setDriverName("Jane Doe");
        List<DeliveryDrivers> driversList = new ArrayList<>();
        driversList.add(driver1);
        driversList.add(driver2);
        when(deliveryDriversRepository.findAll()).thenReturn(driversList);
        List<DeliveryDrivers> result = deliveryDriversService.getAllDeliveryDrivers();
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getDriverName());
        assertEquals("Jane Doe", result.get(1).getDriverName());
        verify(deliveryDriversRepository, times(1)).findAll();
    }
 
    @Test
    public void testGetAllDeliveryDrivers_EmptyList()
    {
        List<DeliveryDrivers> emptyList = new ArrayList<>();
        when(deliveryDriversRepository.findAll()).thenReturn(emptyList);
        List<DeliveryDrivers> result = deliveryDriversService.getAllDeliveryDrivers();
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(deliveryDriversRepository, times(1)).findAll();
    }
 
    @Test
    public void testGetDeliveryDriversById_Found()
    {
        int driverId = 1;
        DeliveryDrivers driver = new DeliveryDrivers();
        driver.setDriverId(driverId);
        driver.setDriverName("John Doe");
        when(deliveryDriversRepository.findById(driverId)).thenReturn(Optional.of(driver));
        DeliveryDrivers result = deliveryDriversService.getDeliveryDriversById(driverId);
        assertNotNull(result);
        assertEquals(driverId, result.getDriverId());
        assertEquals("John Doe", result.getDriverName());
        verify(deliveryDriversRepository, times(1)).findById(driverId);
    }
    @Test
    public void testGetDeliveryDriversById_NotFound()
    {
        int driverId = 999;
        when(deliveryDriversRepository.findById(driverId)).thenReturn(Optional.empty());
        DeliveryDrivers result = deliveryDriversService.getDeliveryDriversById(driverId);
        assertNull(result);
        verify(deliveryDriversRepository, times(1)).findById(driverId);
    }
}