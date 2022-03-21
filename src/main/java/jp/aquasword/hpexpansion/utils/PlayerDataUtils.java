package jp.aquasword.hpexpansion.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

import jp.aquasword.hpexpansion.data.PlayerData;
import net.minecraftforge.fml.loading.FMLPaths;

public class PlayerDataUtils {

	public static PlayerData read(String uuid) {
		File file = filePath(uuid);
		if (!file.exists()) {
			createNewPlayerData(uuid, file);
		}
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			StringBuilder sb = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			return new Gson().fromJson(sb.toString(), PlayerData.class);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void save(String uuid, PlayerData data) {
		File file = filePath(uuid);

		try (FileWriter writer = new FileWriter(file)) {
			writer.write(new Gson().toJson(data));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void createNewPlayerData(String uuid, File file) {
		file.getParentFile().mkdir();
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		PlayerData data = new PlayerData();
		data.setCount(0);
		save(uuid, data);
	}

	private static File filePath(String uuid) {
		String worldName = ServerPropertiesReadings.worldName();
		File file = new File(FMLPaths.GAMEDIR.get().toString(),
				"/" + worldName + "/data/hpexpansion/" + uuid + ".json");
		return file;
	}

}
