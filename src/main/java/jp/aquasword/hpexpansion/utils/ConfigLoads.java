package jp.aquasword.hpexpansion.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;

import jp.aquasword.hpexpansion.data.HpexpansionConfig;
import net.minecraftforge.fml.loading.FMLPaths;

public class ConfigLoads {
	public static HpexpansionConfig load() {
		File configFile = new File(FMLPaths.CONFIGDIR.get().toString(), File.separator + "hpexpansion.json");
		try (BufferedReader reader = new BufferedReader(new FileReader(configFile))) {
			String line;
			StringBuilder sb = new StringBuilder();
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			return new Gson().fromJson(sb.toString(), HpexpansionConfig.class);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

}
