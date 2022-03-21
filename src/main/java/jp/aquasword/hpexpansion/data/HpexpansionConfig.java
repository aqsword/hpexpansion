package jp.aquasword.hpexpansion.data;

public class HpexpansionConfig {
	private int maxHealth;
	private int minHealth;
	private int defaultHealth;
	private int healthIncreaseRange;

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public int getMinHealth() {
		return minHealth;
	}

	public void setMinHealth(int minHealth) {
		this.minHealth = minHealth;
	}

	public int getDefaultHealth() {
		return defaultHealth;
	}

	public void setDefaultHealth(int defaultHealth) {
		this.defaultHealth = defaultHealth;
	}

	public int getHealthIncreaseRange() {
		return healthIncreaseRange;
	}

	public void setHealthIncreaseRange(int healthIncreaseRange) {
		this.healthIncreaseRange = healthIncreaseRange;
	}
}
