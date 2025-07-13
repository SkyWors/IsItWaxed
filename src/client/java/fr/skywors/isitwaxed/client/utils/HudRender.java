package fr.skywors.isitwaxed.client.utils;

import fr.skywors.isitwaxed.client.IsItWaxedClient;
import fr.skywors.isitwaxed.client.config.Config.HudSide;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class HudRender {
	public static void renderWaxedIcon(DrawContext drawContext, MinecraftClient minecraftClient, boolean isWaxed) {
		HudSide hudSide = HudSide.CENTER;
		int WaxedColor = -549112;
		int NotWaxedColor = -855678891;
		boolean textShadow = true;

		if (IsItWaxedClient.reconfigureEnabled) {
			hudSide = IsItWaxedClient.config.hudSide.get();
			WaxedColor = IsItWaxedClient.config.colorWaxed.get();
			NotWaxedColor = IsItWaxedClient.config.colorNotWaxed.get();
			textShadow = IsItWaxedClient.config.textShadow.get();
		}

		int iconSize = 14;

		int x = 0;
		int y = 0;

		switch (hudSide) {
			case LEFT:
				x = 10;
				y = drawContext.getScaledWindowHeight() /2;
				break;

			case RIGHT:
				x = drawContext.getScaledWindowWidth() - (10 + iconSize);
				y = drawContext.getScaledWindowHeight() /2;
				break;

			case TOP_LEFT:
				x = 10;
				y = 10;
				break;

			case TOP_RIGHT:
				x = drawContext.getScaledWindowWidth() - (10 + iconSize);
				y = 10;
				break;

			case TOP_CENTER:
				x = (drawContext.getScaledWindowWidth() /2) - (iconSize /2);
				y = 10;
				break;

			case BOTTOM_LEFT:
				x = 10;
				y = drawContext.getScaledWindowHeight() - (10 + iconSize);
				break;

			case BOTTOM_RIGHT:
				x = drawContext.getScaledWindowWidth() - (10 + iconSize);
				y = drawContext.getScaledWindowHeight() - (10 + iconSize);
				break;

			default:
				x = ((drawContext.getScaledWindowWidth() /2) - (iconSize /2)) +12;
				y = ((drawContext.getScaledWindowHeight() /2) - (iconSize /2)) +12;
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
			iconSize, iconSize,
			iconSize, iconSize
		);

		String text = isWaxed ? Text.translatable("isitwaxed.hud.text.waxed").getString() : Text.translatable("isitwaxed.hud.text.not_waxed").getString();
        int textX = x + iconSize +4;
        int textY = y + ((iconSize - minecraftClient.textRenderer.fontHeight) /2) +1;
        int textWidth = minecraftClient.textRenderer.getWidth(text);

		if (textX + textWidth > drawContext.getScaledWindowWidth()) {
			textX = x - textWidth -4;
		}

		drawContext.drawText(
			minecraftClient.textRenderer,
			text,
			textX,
			textY,
			isWaxed ? WaxedColor : NotWaxedColor,
			textShadow
		);
	}
}
