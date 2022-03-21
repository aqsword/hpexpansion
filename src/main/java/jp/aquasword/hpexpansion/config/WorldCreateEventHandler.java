package jp.aquasword.hpexpansion.config;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

import jp.aquasword.hpexpansion.HpexpansionMod;
import jp.aquasword.hpexpansion.data.HpexpansionConfig;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.loading.FMLPaths;

public class WorldCreateEventHandler {

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void init(FMLCommonSetupEvent event) {
			onLoad(event);
		}
	}

	public static void onLoad(FMLCommonSetupEvent event) {
		File configFile = new File(FMLPaths.CONFIGDIR.get().toString(), File.separator + "hpexpansion.json");

		if (configFile.exists()) {
			// NOP
			return;
		}

		HpexpansionMod.LOGGER.info("Start HP Expansion Config process.");

		configFile.getParentFile().mkdir();
		try {
			configFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		HpexpansionConfig config = new HpexpansionConfig();
		config.setDefaultHealth(20);
		config.setHealthIncreaseRange(2);
		config.setMaxHealth(60);
		config.setMinHealth(16);

		try (FileWriter writer = new FileWriter(configFile)) {
			writer.write(new Gson().toJson(config));
		} catch (Exception e) {
			e.printStackTrace();
		}

		HpexpansionMod.LOGGER.info("End HP Expansion Config process.");

	}

}
