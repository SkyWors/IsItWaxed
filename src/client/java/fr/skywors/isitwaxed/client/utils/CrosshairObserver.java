package fr.skywors.isitwaxed.client.utils;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;

public class CrosshairObserver {
	public static Block getTargetBlock(BlockHitResult crosshairTarget) {
		MinecraftClient minecraftClient = MinecraftClient.getInstance();
		ClientWorld world = minecraftClient.world;
		BlockPos blockpos = crosshairTarget.getBlockPos();
		BlockState blockstate = world.getBlockState(blockpos);

		return blockstate.getBlock();
	}
}
