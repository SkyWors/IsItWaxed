package fr.skywors.isitwaxed.client.config;

import fr.skywors.isitwaxed.IsItWaxed;
import net.replaceitem.reconfigure.api.ConfigTab;
import net.replaceitem.reconfigure.api.Property;
import net.replaceitem.reconfigure.api.serializer.Serializers;

public class Config {
	public final net.replaceitem.reconfigure.api.Config config = net.replaceitem.reconfigure.api.Config.builder(IsItWaxed.MOD_ID).serializer(Serializers.JSON).build();

	public final ConfigTab tab = config.createDefaultTab().build();

	public final Property<HudSide> hudSide = tab.createEnumProperty("hud_side", HudSide.class).defaultValue(HudSide.CENTER).asCyclingButton().build();

	public enum HudSide {
		LEFT,
		RIGHT,
		CENTER
	}

	public void save() {
		this.config.save();
	}

	public void load() {
		this.config.load();
	}
}
