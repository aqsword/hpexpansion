package jp.aquasword.hpexpansion.procedures;

import java.util.Map;

import jp.aquasword.hpexpansion.HpexpansionMod;
import jp.aquasword.hpexpansion.data.HpexpansionConfig;
import jp.aquasword.hpexpansion.data.PlayerData;
import jp.aquasword.hpexpansion.utils.ConfigLoads;
import jp.aquasword.hpexpansion.utils.HelthCalcUtils;
import jp.aquasword.hpexpansion.utils.PlayerDataUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;

public class HPheartOnRightClickProcedure {
	public static ActionResultType executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				HpexpansionMod.LOGGER.warn("Failed to load dependency entity for HPheartOnRightClickProcedure...");
			return ActionResultType.FAIL;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if(entity.world.isRemote) {
			HpexpansionMod.LOGGER.info("player.world.isRemote:" + entity.world.isRemote);
			return ActionResultType.PASS;
		}
		PlayerEntity player = (PlayerEntity) entity;

		HpexpansionConfig config = ConfigLoads.load();
		PlayerData data = PlayerDataUtils.read(player.getUniqueID().toString());
		data.setName(player.getName().getString());

		int maxHealth = HelthCalcUtils.calc(config, data.getCount() + 1);

		// 最大以上だったら下げる
		if (maxHealth > config.getMaxHealth()) {
			maxHealth = config.getMaxHealth();
			return ActionResultType.PASS;
		} else {
			data.setCount(data.getCount() + 1);
		}

		// 最大HP設定
		LivingEntity lEntity = (LivingEntity) player;
		lEntity.getAttribute(Attributes.MAX_HEALTH).setBaseValue(maxHealth);
		lEntity.setHealth((float) lEntity.getAttribute(Attributes.MAX_HEALTH).getBaseValue());

		PlayerDataUtils.save(player.getUniqueID().toString(), data);

		return ActionResultType.SUCCESS;
	}
}
