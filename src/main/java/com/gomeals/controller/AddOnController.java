package com.gomeals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gomeals.model.Addons;
import com.gomeals.service.AddonsService;

@RestController
@CrossOrigin
@RequestMapping("/Addons")
public class AddOnController {

    @Autowired
    AddonsService addonsService;

    @GetMapping("/get/{addonId}")
    public Addons getAddon(@PathVariable("addonId") int id) {
        return addonsService.getAddon(id);
    }

    @PostMapping("/create")
    public String createAddon(@RequestBody Addons addon) {
        return addonsService.createAddon(addon);
    }

    @PutMapping("/update")
    public String updateAddon(@RequestBody Addons addon) {

        return addonsService.updateAddon(addon);
    }

    @DeleteMapping("/delete/{addonId}")
    public String deleteAddon(@PathVariable("addonId") int id) {
        return addonsService.deleteAddon(id);
    }

}
