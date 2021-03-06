package neominecraftism.neominecraftism.profession.identities;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;

import neominecraftism.neominecraftism.profession.IProfession;
import neominecraftism.neominecraftism.rpgitems.builders.EquipmentType;
import neominecraftism.neominecraftism.rpgitems.builders.ItemHelper;
import neominecraftism.neominecraftism.rpgitems.builders.RPGItem;
import neominecraftism.neominecraftism.rpgitems.builders.RPGMeleeWeapon;
import neominecraftism.neominecraftism.rpgitems.builders.WeaponType;
import neominecraftism.neominecraftism.util.PotionEffectBuilder;

public class Warrior extends IProfession {

	public Warrior() {
		super("warrior");
	}

	@Override
	public String getProfessionName() {
		return "战士";
	}
	@Override
	public String[] getDescription() {
		String[] strings = {"精通所有近战武器，但是副手无法持有物品","被动：手持近战武器时增加3点力量，副手持有物品时无法攻击"};
		return strings;
	}

	@Override
	public boolean upgradeToThis(Player player) {
		return false;
	}

	@Override
	public WeaponType[] getMasterWeapon() {
		return new WeaponType[] {WeaponType.AXE,WeaponType.HALBERD,WeaponType.SWORD};
	}

	@Override
	public EquipmentType[] getMasterArmor() {
		return new EquipmentType[] {EquipmentType.BOOTS,EquipmentType.HAT,EquipmentType.LEGGINGS,EquipmentType.LIGHT_CHESTPLATES,EquipmentType.HELMET,EquipmentType.HEAVY_CHESTPLATES};
	}
	
	@Override
	public void effectPerHalfSecond(Player player) {
		ItemStack offhand = player.getInventory().getItemInOffHand();
		if(!offhand.equals(null)&&!offhand.getType().equals(Material.AIR)) {
			player.addPotionEffect(PotionEffectBuilder.buildIconOnly(PotionEffectType.WEAKNESS, 20, 10),true);
		}
		super.effectPerHalfSecond(player);
	}
	@Override
	public double onGetStrength(Player player, double strength) {
		RPGItem item = ItemHelper.getItem(player.getInventory().getItemInMainHand());
		if (item!=null && item instanceof RPGMeleeWeapon) {
			strength+=3;
		}
		return strength;	
	}

}
