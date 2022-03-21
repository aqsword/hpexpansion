package jp.aquasword.hpexpansion.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import net.minecraftforge.fml.loading.FMLPaths;

public class ServerPropertiesReadings {

	public static String worldName() {
		try {
			Properties properties = new Properties();
			properties.load(Files.newBufferedReader(
					Paths.get(FMLPaths.GAMEDIR.get().toString() + File.separator + "server.properties")));
			return properties.getProperty("level-name", "");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

}
