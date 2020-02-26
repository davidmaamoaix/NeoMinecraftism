package neominecraftism.neominecraftism.rpg.items;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.inventory.EquipmentSlot;

import neominecraftism.neominecraftism.rpg.RPGItem;
import neominecraftism.neominecraftism.rpg.RPGMeleeWeapon;
import neominecraftism.neominecraftism.rpg.RPGMeleeWeapon.AttributeEntry;
import neominecraftism.neominecraftism.rpg.WeaponType;
import neominecraftism.neominecraftism.util.ItemRarity;

public class Weapons {
	
	public static final RPGItem BASE_AXE = new RPGMeleeWeapon("base_axe","原石斧", Material.STONE_AXE, WeaponType.AXE, ItemRarity.NORMAL, 1)
			.withAttackDamageAndSpeed(9, 1.1)
			.withOtherAttribute(new AttributeEntry(Attribute.GENERIC_ARMOR, EquipmentSlot.HAND, 2, Operation.ADD_NUMBER))
			.withDescription("原石制作的斧子，略微增加一些护甲");

}
