package neominecraftism.neominecraftism.rpg;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.attribute.AttributeModifier.Operation;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import neominecraftism.neominecraftism.util.ItemRarity;

public class RPGRangedWeapon extends RPGItem {

	private DecimalFormat format= new DecimalFormat("0.0");
	private int level;
	private WeaponType weapon_type;
	private List<AttributeEntry> attributes= new ArrayList<AttributeEntry>();
	public RPGRangedWeapon(String register_name, String display_name, Material material, WeaponType type, ItemRarity rarity, int level) {
		super(register_name, display_name, material, ItemType.WEAPON, rarity);
		this.level = level;
		this.weapon_type=type;
	}
	
	@Override
	public ItemStack build() {
		ItemStack itemstack = super.build();
		ItemMeta meta = itemstack.getItemMeta();
		
		//add lore
		List<String> strings= new ArrayList<String>();
		strings.add(ChatColor.ITALIC+String.format("%d级%s", this.level, this.weapon_type.getDisplayText()));
		if(this.lore!=null) {
			for(String s:this.lore) {
				strings.add(ChatColor.ITALIC+s);
			}
		}
		double damage_modify = (meta.getEnchantLevel(Enchantment.ARROW_DAMAGE)+1)*0.25*9;
		double speed_modify = -meta.getEnchantLevel(Enchantment.QUICK_CHARGE)*0.25;
		if(this.material == Material.CROSSBOW)speed_modify+=0.25;
		strings.add(ChatColor.YELLOW+String.format("平均远程伤害: %s", format.format(9+damage_modify)));
		strings.add(ChatColor.YELLOW+String.format("有效攻击间隔: %s", format.format(1+speed_modify)));

		
		meta.setLore(strings);
				
		//add others
		attributes.forEach(attribute->meta.addAttributeModifier(attribute.getAttribute(),attribute.getAttributeModifier()));		
		
		itemstack.setItemMeta(meta);
		return itemstack;
	}
	
	public RPGRangedWeapon withOtherAttribute(AttributeEntry attribute) {
		this.attributes.add(attribute);
		return this;
	}

	
	
	
	
	
	
	public static class AttributeEntry {
		private Attribute attribute;
		private AttributeModifier attribute_modifier;
		
		public AttributeEntry(Attribute attribute, EquipmentSlot slot, double amount, Operation operation) {
			this.attribute = attribute;
			this.attribute_modifier  = new AttributeModifier(
					UUID.randomUUID(),
					attribute.name(), 
					amount, 
					operation,
					slot);
		}

		public Attribute getAttribute() {
			return attribute;
		}

		public void setAttribute(Attribute attribute) {
			this.attribute = attribute;
		}

		public AttributeModifier getAttributeModifier() {
			return attribute_modifier;
		}

		public void setAttributeModifier(AttributeModifier attribute_modifier) {
			this.attribute_modifier = attribute_modifier;
		}	
	}

}
