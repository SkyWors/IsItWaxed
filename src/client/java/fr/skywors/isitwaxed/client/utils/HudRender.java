package fr.skywors.isitwaxed.client.utils;

import fr.skywors.isitwaxed.client.IsItWaxedClient;
import fr.skywors.isitwaxed.client.config.Config.HudSide;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;

public class HudRender {
	public static void renderWaxedIcon(DrawContext drawContext, boolean isWaxed) {
		HudSide hudSide = IsItWaxedClient.config.hudSide.get();

		int x = 0;
		int y = drawContext.getScaledWindowHeight() /2;

		switch (hudSide) {
			case LEFT:
				x = 10;

				break;

			case RIGHT:
				x = drawContext.getScaledWindowWidth() -20;

				break;

			default:
				x = (drawContext.getScaledWindowWidth() /2) +6;
				y += 6;

				break;
		}

		Identifier texture = isWaxed
			? Identifier.of("isitwaxed", "textures/gui/waxed.png")
			: Identifier.of("isitwaxed", "textures/gui/not_waxed.png");

		drawContext.drawTexture(
			RenderPipelines.GUI_TEXTURED,
			texture,
			x,
			y,
			0, 0,
			14, 14,
			14, 14
		);
	}
}
