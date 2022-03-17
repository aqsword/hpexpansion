
package jp.aquasword.hpexpansion.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.block.BlockState;

import jp.aquasword.hpexpansion.HpexpansionModElements;

@HpexpansionModElements.ModElement.Tag
public class QuarterHPHeartItem extends HpexpansionModElements.ModElement {
	@ObjectHolder("hpexpansion:quarter_hp_heart")
	public static final Item block = null;

	public QuarterHPHeartItem(HpexpansionModElements instance) {
		super(instance, 2);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(ItemGroup.MISC).maxStackSize(32).rarity(Rarity.UNCOMMON));
			setRegistryName("quarter_hp_heart");
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public int getUseDuration(ItemStack itemstack) {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
			return 1F;
		}
	}
}
