package be.goldocelot.lg.role.playable.sorci�re;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import be.goldocelot.lg.role.RoleConfig;
import be.goldocelot.lg.role.Role;
import be.goldocelot.lg.utils.ItemStackCreator;

/**
 * 
 * @author Nicolas Gerard (Goldocelot)
 * Class qui repr�sente le role de sorci�re
 */
public class Sorci�re extends Role{
	
	public Sorci�re(RoleConfig rConfig) {
		super(rConfig);
	}
	
	// Montant de vie initial de la sorci�re
	@Override
	public double MaxHealth() {
		return 20d;
	}

	// Joueur poss�dant le r�le de sorci�re
	@Override
	public List<Player> players() {
		YamlConfiguration config = getrConfig().getNewConfiguration();
		List<Player> p = new ArrayList<>();
		for(String name : config.getConfigurationSection("Player").getKeys(false)) {
			if(config.getString("Player."+name).equals("Sorci�re")) {
				p.add(Bukkit.getPlayer(name));
			}
		}
		return p;
	}

	// Equipement de d�part de la sorci�re
	@Override
	public List<ItemStack> equipement() {
		List<ItemStack> e = new ArrayList<>();
		ItemStack h = new ItemStackCreator(null, 1, Material.LEATHER_HELMET, (byte) 0).create();
		h.addEnchantment(Enchantment.DURABILITY, 3);
		e.add(h);
		ItemStack c = new ItemStackCreator(null, 1, Material.LEATHER_CHESTPLATE, (byte) 0).create();
		c.addEnchantment(Enchantment.DURABILITY, 3);
		e.add(c);
		ItemStack l = new ItemStackCreator(null, 1, Material.LEATHER_LEGGINGS, (byte) 0).create();
		l.addEnchantment(Enchantment.DURABILITY, 3);
		e.add(l);
		ItemStack b = new ItemStackCreator(null, 1, Material.LEATHER_BOOTS, (byte) 0).create();
		b.addEnchantment(Enchantment.DURABILITY, 3);
		e.add(b);
		ItemStack s = new ItemStackCreator(null, 1, Material.WOOD_SWORD, (byte) 0).create();
		s.addEnchantment(Enchantment.DURABILITY, 3);
		e.add(s);
		ItemStack f = new ItemStackCreator(null, 64, Material.COOKED_FISH, (byte) 0).create();
		e.add(f);
		return e;
	}

	// Effet de potion de la sorci�re
	@Override
	public List<PotionEffect> effect() {
		List<PotionEffect> pe = new ArrayList<>();
		PotionEffect a = new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, 0, false, false);
		pe.add(a);
		return pe;
	}

	@Override
	public List<String> rules() {
		List<String> rules = new ArrayList<>();
		rules.add("�8[�4LG�8]�r Vous �tes \"�aSorci�re�r\" vous devez gagner avec le �avillage�r.");
		rules.add("�8[�4LG�8]�r Pour gagner avec le �avillage�r vous devez �liminer toute les menaces pour celui-ci.");
		rules.add("�8[�4LG�8]�r En tant que \"�aSorci�re�r\" vous disposez de deux pouvoirs �eactif�r et d'un pouvoir �9passif�r.");
		rules.add("�8[�4LG�8]�r �eActif�r: �6Une fois�r dans la partie vous pouvez utilisez la commande \"�6/vie�r\" qui a pour effet de r�suciter le dernier joueur mort, vous pouvez aussis utiliser �6une fois�r  la commande \"�6/mort [Joueur]�r\" qui a pour effet d'enlever �c4 receptacles de coeur�r au joueur vis�.");
		rules.add("�8[�4LG�8]�r �9Passif�r: Vous avez l'effet �5r�g�n�ration�r.");
		return rules;	
	}

}
