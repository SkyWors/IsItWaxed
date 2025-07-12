package fr.skywors.isitwaxed.client;

import fr.skywors.isitwaxed.IsItWaxed;
import fr.skywors.isitwaxed.client.config.Config;
import fr.skywors.isitwaxed.client.utils.CrosshairObserver;
import fr.skywors.isitwaxed.client.utils.HudRender;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.OxidizableBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;

public class IsItWaxedClient implements ClientModInitializer {

	public static Config config = new Config();

	@Override
	public void onInitializeClient() {
		config.load();

		HudElementRegistry.addLast(Identifier.of(IsItWaxed.MOD_ID), (drawContext, tickDelta) -> {
			MinecraftClient minecraftClient = MinecraftClient.getInstance();
			ClientWorld world = minecraftClient.world;

			if (
				world != null
				&& minecraftClient.player != null
				&& minecraftClient.crosshairTarget instanceof BlockHitResult crosshairTarget
				&& (
					(minecraftClient.player.getMainHandStack().getItem() instanceof AxeItem)
					|| minecraftClient.player.getMainHandStack().getItem().equals(Items.HONEYCOMB)
				)
			) {
				Block block = CrosshairObserver.getTargetBlock(crosshairTarget);

				if (block.getName().toString().toLowerCase().contains("waxed")) {
					HudRender.renderWaxedIcon(drawContext, true);
				} else {
					if (block instanceof OxidizableBlock) {
						HudRender.renderWaxedIcon(drawContext, false);
					}
				}
			}
		});
	}
}
