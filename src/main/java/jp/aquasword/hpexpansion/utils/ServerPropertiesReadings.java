package jp.aquasword.hpexpansion.utils;

import java.nio.file.Path;
import java.nio.file.Paths;

import net.minecraft.server.ServerPropertiesProvider;
import net.minecraft.util.registry.DynamicRegistries;

public class ServerPropertiesReadings {

	public static String worldName() {
		DynamicRegistries.Impl registers = DynamicRegistries.func_239770_b_();
		Path path = Paths.get("server.properties");
		ServerPropertiesProvider propertiesProvider = new ServerPropertiesProvider(registers, path);
		return propertiesProvider.getProperties().worldName;
	}

}
