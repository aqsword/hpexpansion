package jp.aquasword.hpexpansion.utils;

import jp.aquasword.hpexpansion.data.HpexpansionConfig;

public class HelthCalcUtils {
	public static int calc(HpexpansionConfig config, int count) {
		return config.getDefaultHealth() + config.getDefaultHealth() * count;
	}

	public static int calc(int count) {
		HpexpansionConfig config = ConfigLoads.load();
		return config.getDefaultHealth() + config.getDefaultHealth() * count;
	}
}
