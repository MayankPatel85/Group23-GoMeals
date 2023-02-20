package com.gomeals.service;

import com.gomeals.model.Addons;

public interface AddonsService {

    public Addons getAddon(int addonId);

    public String createAddon(Addons addon);

    public String updateAddon(Addons addon);

    public String deleteAddon(int addonId);
}
