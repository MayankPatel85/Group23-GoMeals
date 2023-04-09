package com.gomeals.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.gomeals.model.Addons;
import com.gomeals.repository.AddonsRepository;
import com.gomeals.service.AddonsService;

import jakarta.transaction.Transactional;

import java.util.List;

@Service
public class AddonsServiceImpl implements AddonsService {

    @Autowired
    AddonsRepository addonsRepository;

    @Override
    @Transactional
    public Addons getAddon(int addonId) {
        return addonsRepository.findById(addonId).orElse(null);
    }

    @Override
    @Transactional
    public String createAddon(Addons addon) {
        addonsRepository.save(addon);
        return "Addon added successully";
    }

    @Override
    @Transactional
    public String updateAddon(Addons addon) {
        Addons latestAddon = addonsRepository.findById(addon.getAddonId()).orElse(null);
        latestAddon.setItem(addon.getItem());
        latestAddon.setPrice(addon.getPrice());
        addonsRepository.save(latestAddon);
        return "Addon Item updated successfully.";
    }

    @Override
    @Transactional
    public String deleteAddon(int addonId) {
        addonsRepository.deleteById(addonId);
        return "Addon deleted successully";
    }

    @Override
    public List<Addons> getAllSupplierAddons(int supplierId) {
        List<Addons> supplierAddons = addonsRepository.findAllBySupplierId(supplierId);
        return supplierAddons;
    }

}
