package jp.aquasword.hpexpansion.procedures;

import jp.aquasword.hpexpansion.data.HpexpansionConfig;
import jp.aquasword.hpexpansion.data.PlayerData;
import jp.aquasword.hpexpansion.utils.ConfigLoads;
import jp.aquasword.hpexpansion.utils.HelthCalcUtils;
import jp.aquasword.hpexpansion.utils.PlayerDataUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class PlayerLoginProcedures {
	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void init(PlayerLoggedInEvent event) {
			onLogin(event);
		}
	}

	public static void onLogin(PlayerLoggedInEvent event) {
		PlayerEntity player = event.getPlayer();
		if(!player.world.isRemote) {
			return;
		}

		PlayerData data = PlayerDataUtils.read(player.getUniqueID().toString());
		HpexpansionConfig config = ConfigLoads.load();

		int maxHealth = HelthCalcUtils.calc(config, data.getCount());

		// 最大以上だったら下げる
		if (maxHealth > config.getMaxHealth()) {
			maxHealth = config.getMaxHealth();
		}
		// 最低以下だったら上げる
		if (maxHealth < config.getMinHealth()) {
			maxHealth = config.getMinHealth();
		}

		// 最大HP設定
		LivingEntity lEntity = (LivingEntity) player;
		lEntity.getAttribute(Attributes.MAX_HEALTH).setBaseValue(maxHealth);
		lEntity.setHealth(lEntity.getHealth());
	}
}
