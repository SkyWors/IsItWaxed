package fr.skywors.isitwaxed.client.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import fr.skywors.isitwaxed.client.IsItWaxedClient;

public class ModMenu implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> IsItWaxedClient.config.config.createScreen(parent);
    }
}
