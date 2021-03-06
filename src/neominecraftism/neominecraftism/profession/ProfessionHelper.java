package neominecraftism.neominecraftism.profession;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;

import neominecraftism.neominecraftism.NeoMinecraftism;
import neominecraftism.neominecraftism.storage.StorageTracker;
import neominecraftism.neominecraftism.util.NBTHelper;
import neominecraftism.neominecraftism.util.StringArrayDataType;

public class ProfessionHelper {
	
	public static final NamespacedKey IDENTITY = NBTHelper.namespace("identities");
	public static final StringArrayDataType STRING_ARRAY = new StringArrayDataType();

	public static IProfession getProfession(String name) {
		return NeoMinecraftism.getInstance().getRegistryHandler().get(IProfession.class, name);
	}
	public static boolean hasProfession(Player player, String profession) {
		List<IProfession> current_professions = getProfessions(player);
		return current_professions.contains(getProfession(profession));
	}
	public static boolean addProfession(Player player, IProfession profession) {
		List<IProfession> current_professions = getProfessions(player);
		if(!current_professions.contains(profession)){
			current_professions.add(profession);
			setProfessions(player, current_professions);
			player.sendMessage("你成功就职为"+profession.getProfessionName()+"!");
			player.sendMessage("职业描述：");
			for(String line:profession.getDescription()) {
				player.sendMessage(line);
			}
			profession.effectOnObtain(player);
			return true;
		} else {
			player.sendMessage("无法就职！你已经是"+profession.getProfessionName()+"了");
			return false;
		}
	}
	
	public static boolean removeProfession(Player player, IProfession profession) {
		List<IProfession> current_professions = getProfessions(player);
		if(current_professions.contains(profession)){
			current_professions.remove(profession);
			setProfessions(player, current_professions);
			return true;
		} else {
			return false;
		}
	}

	public static List<IProfession> getProfessions(Player player) {
		String[] profession_names = StorageTracker.getPlayerStorage(player).professions;
		ArrayList<IProfession> professions  = new ArrayList<IProfession>();
		if(profession_names!=null) {
			for(String name: profession_names) {
				professions.add(NeoMinecraftism.getInstance().getRegistryHandler().get(IProfession.class, name));
			}
		}
		return professions;
	}
	public static void setProfessions(Player player, List<IProfession> professions) {
		if(professions!=null && professions.size()>0) {
			String[] profession_names= new String[professions.size()];
			for(int i = 0;i<professions.size();i++) {
				profession_names[i] = professions.get(i).getRegistryName();
			}
			StorageTracker.getPlayerStorage(player).professions = profession_names;
		}	
	}
}
