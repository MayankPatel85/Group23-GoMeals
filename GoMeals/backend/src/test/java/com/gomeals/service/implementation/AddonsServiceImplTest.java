package com.gomeals.service.implementation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.gomeals.model.Addons;
import com.gomeals.repository.AddonsRepository;

@SpringBootTest
@Transactional
public class AddonsServiceImplTest {

    @InjectMocks
    private AddonsServiceImpl addonsServiceImpl;

    @Mock
    AddonsRepository addonsRepository;

    @Test
    public void testGetAddon() {
        Addons addon = new Addons(1, "Cheese", 9.99f, 1);
        Mockito.when(addonsRepository.findById(1)).thenReturn(
                Optional.of(addon));

        Addons retrievedAddon = addonsServiceImpl.getAddon(addon.getAddonId());
        assertNotNull(retrievedAddon);
    }

    @Test
    public void testCreateAddon() {
        Addons addon = new Addons(9, "Butter", 3.19f, 2);
        String result = addonsServiceImpl.createAddon(addon);

        assertEquals("Addon added successully", result);
    }

    @Test
    public void testUpdateAddon() {
        Addons oldAddOn = new Addons(3, "Avocado", 2.99f, 3);
        Addons newAddOn = new Addons(3, "Mashed Avocado", 3.99f, 3);

        Mockito.when(addonsRepository.findById(3)).thenReturn(Optional.of(oldAddOn));
        Mockito.when(addonsRepository.save(oldAddOn)).thenReturn(oldAddOn);

        String updateStatus = addonsServiceImpl.updateAddon(newAddOn);
        assertEquals("Addon Item updated successfully.", updateStatus);

    }

    @Test
    public void testDeleteAddon() {
        int id = 1;
        doNothing().when(addonsRepository).deleteById(1);
        String result = addonsServiceImpl.deleteAddon(id);
        assertEquals("Addon deleted successully", result);
    }

    @Test
    void testGetAllSupplierAddonsWithValidSupplierId() {

        Integer supplierId = 2;
        List<Addons> expectedSupplierAddons = new ArrayList<>();
        expectedSupplierAddons.add(new Addons(1, "Jalapenos", 6.45f, supplierId));
        expectedSupplierAddons.add(new Addons(2, "Crunchy Onions", 3.25f, supplierId));
        expectedSupplierAddons.add(new Addons(3, "Tandoori Naan", 4.56f, supplierId));
        Mockito.when(addonsRepository.findAllBySupplierId(supplierId)).thenReturn(expectedSupplierAddons);

        List<Addons> actualSupplierAddons = addonsServiceImpl.getAllSupplierAddons(supplierId);

        assertEquals(expectedSupplierAddons, actualSupplierAddons);
    }

    @Test
    void testGetAllSupplierAddonsWithInvalidSupplierId() {
        int supplierId = 1;
        List<Addons> expectedSupplierAddons = new ArrayList<>();
        Mockito.when(addonsRepository.findAllBySupplierId(supplierId)).thenReturn(expectedSupplierAddons);
        List<Addons> actualSupplierAddons = addonsServiceImpl.getAllSupplierAddons(supplierId);
        assertNull(actualSupplierAddons);
    }

    @Test
    void testGetAllSupplierAddonsWithNullSupplierId() {
        int supplierId = 0;
        List<Addons> actualSupplierAddons = addonsServiceImpl.getAllSupplierAddons(supplierId);
        assertNull(actualSupplierAddons);
    }
}
