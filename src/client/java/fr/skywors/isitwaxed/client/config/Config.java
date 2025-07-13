package fr.skywors.isitwaxed.client.config;

import fr.skywors.isitwaxed.IsItWaxed;
import net.replaceitem.reconfigure.api.ConfigTab;
import net.replaceitem.reconfigure.api.Property;
import net.replaceitem.reconfigure.api.serializer.Serializers;

public class Config {
	public final net.replaceitem.reconfigure.api.Config config = net.replaceitem.reconfigure.api.Config.builder(IsItWaxed.MOD_ID).serializer(Serializers.JSON).build();

	public final ConfigTab main = config.createTab("main").build();
	public final ConfigTab design = config.createTab("design").build();

	public final Void killswitch = main.createHeadline("killswitch");
	public final Property<Boolean> enabled = main.createBooleanProperty("enabled").defaultValue(true).asToggleButton().build();

	public final Void positions = main.createHeadline("positions");
	public final Property<HudSide> hudSide = main.createEnumProperty("hud_side", HudSide.class).defaultValue(HudSide.CENTER).asCyclingButton().build();

	public final Void features = main.createHeadline("features");
	public final Property<Boolean> isAxeNeeded = main.createBooleanProperty("is_axe_needed").defaultValue(false).asToggleButton().build();

	public final Void designs = design.createHeadline("design");
	public final Property<Integer> colorWaxed = design.createIntegerProperty("color_waxed").defaultValue(-549112).asColorPicker().build();
	public final Property<Integer> colorNotWaxed = design.createIntegerProperty("color_notwaxed").defaultValue(-855678891).asColorPicker().build();
	public final Property<Boolean> textShadow = design.createBooleanProperty("text_shadow").defaultValue(true).asToggleButton().build();

	public enum HudSide {
		LEFT,
		RIGHT,
		CENTER,
		TOP_LEFT,
		TOP_RIGHT,
		TOP_CENTER,
		BOTTOM_LEFT,
		BOTTOM_RIGHT
	}

	public void save() {
		this.config.save();
	}

	public void load() {
		this.config.load();
	}
}
