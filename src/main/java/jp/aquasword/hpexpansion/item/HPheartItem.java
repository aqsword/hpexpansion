
package jp.aquasword.hpexpansion.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.util.Hand;
import net.minecraft.util.ActionResult;
import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.block.BlockState;

import jp.aquasword.hpexpansion.procedures.HPheartOnRightClickProcedure;
import jp.aquasword.hpexpansion.HpexpansionModElements;

import java.util.Collections;

@HpexpansionModElements.ModElement.Tag
public class HPheartItem extends HpexpansionModElements.ModElement {
	@ObjectHolder("hpexpansion:h_pheart")
	public static final Item block = null;

	public HPheartItem(HpexpansionModElements instance) {
		super(instance, 1);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(ItemGroup.MISC).maxStackSize(16).rarity(Rarity.RARE));
			setRegistryName("h_pheart");
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

		@Override
		public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity entity, Hand hand) {
			ActionResult<ItemStack> ar = super.onItemRightClick(world, entity, hand);
			ItemStack itemstack = ar.getResult();
			double x = entity.getPosX();
			double y = entity.getPosY();
			double z = entity.getPosZ();

			HPheartOnRightClickProcedure.executeProcedure(Collections.EMPTY_MAP);
			return ar;
		}
	}
}
