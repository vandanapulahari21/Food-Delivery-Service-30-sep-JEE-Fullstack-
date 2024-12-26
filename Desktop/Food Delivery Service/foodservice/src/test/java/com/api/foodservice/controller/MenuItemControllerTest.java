package com.api.foodservice.controller;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import com.api.foodservice.entity.MenuItems;
import com.api.foodservice.exceptions.MenuItemNotFoundException;
import com.api.foodservice.service.MenuItemService;
public class MenuItemControllerTest {
    @InjectMocks
    private MenuItemController menuItemController;
    @Mock
    private MenuItemService menuItemService;
    @Autowired
    private MockMvc mockMvc;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testGetAllMenuItems() {
        int restaurantId = 1;
        MenuItems menuItem1 = new MenuItems();
        MenuItems menuItem2 = new MenuItems();
        List<MenuItems> menuItems = Arrays.asList(menuItem1, menuItem2);

        when(menuItemService.getAllMenuItems(restaurantId)).thenReturn(menuItems);

        ResponseEntity<List<MenuItems>> response = menuItemController.getAllMenuItems(restaurantId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        verify(menuItemService, times(1)).getAllMenuItems(restaurantId);
    }
    @Test
    public void testAddMenuItem() {
        MenuItems menuItem = new MenuItems();

        ResponseEntity<String> response = menuItemController.addMenuItem(menuItem);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("OK", response.getBody());
        verify(menuItemService, times(1)).addMenuItem(menuItem);
    }
    @Test
    public void testUpdateMenuItem() {
        MenuItems menuItem = new MenuItems();

        when(menuItemService.updateMenuItem(menuItem)).thenReturn(true);

        HttpStatus response = menuItemController.updateMenuItem(menuItem);

        assertEquals(HttpStatus.OK, response);
        verify(menuItemService, times(1)).updateMenuItem(menuItem);
    }
    @Test
    public void testUpdateMenuItem_NotModified() {
        MenuItems menuItem = new MenuItems();

        when(menuItemService.updateMenuItem(menuItem)).thenReturn(false);

        HttpStatus response = menuItemController.updateMenuItem(menuItem);

        assertEquals(HttpStatus.NOT_MODIFIED, response);
        verify(menuItemService, times(1)).updateMenuItem(menuItem);
    }
    @Test
    public void testHandleMenuItemNotFound() {
        MenuItemNotFoundException ex = new MenuItemNotFoundException(1);

        ResponseEntity<String> response = menuItemController.handleMenuItemNotFound(ex);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ex.getMessage(), response.getBody());
    }
}