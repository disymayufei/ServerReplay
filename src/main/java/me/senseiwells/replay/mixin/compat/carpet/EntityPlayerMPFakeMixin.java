package me.senseiwells.replay.mixin.compat.carpet;

import carpet.patches.EntityPlayerMPFake;
import com.mojang.authlib.GameProfile;
import me.senseiwells.replay.config.ReplayConfig;
import me.senseiwells.replay.util.LevelUtils;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ClientInformation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EntityPlayerMPFake.class)
public class EntityPlayerMPFakeMixin extends ServerPlayer {
	public EntityPlayerMPFakeMixin(MinecraftServer minecraftServer, ServerLevel serverLevel, GameProfile gameProfile, ClientInformation clientInformation) {
		super(minecraftServer, serverLevel, gameProfile, clientInformation);
	}

	@Override
	public int requestedViewDistance() {
		if (ReplayConfig.getFixCarpetBotViewDistance()) {
			return LevelUtils.getViewDistance(this.serverLevel());
		}
		return super.requestedViewDistance();
	}
}
