package be.goldocelot.lg.role.playable;

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

import be.goldocelot.lg.role.Role;
import be.goldocelot.lg.role.RoleConfig;
import be.goldocelot.lg.utils.ItemStackCreator;

/**
 * 
 * @author Nicolas Gerard (Goldocelot)
 * Class qui représente le role de cupidon
 */
public class Cupidon extends Role{
	
	public Cupidon(RoleConfig rConfig) {
		super(rConfig);
	}
	
	// Montant de vie initial du cupidon
	@Override
	public double MaxHealth() {
		return 20;
	}

	// Joueur possédant le rôle de cupidon
	@Override
	public List<Player> players() {
		YamlConfiguration config = getrConfig().getNewConfiguration();
		List<Player> p = new ArrayList<>();
		for(String name : config.getConfigurationSection("Player").getKeys(false)) {
			if(config.getString("Player."+name).equals("Cupidon")) {
				p.add(Bukkit.getPlayer(name));
			}
		}
		return p;	
	}

	// Equipement de départ du cupidon
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

	// Effet de potion du cupidon
	@Override
	public List<PotionEffect> effect() {
		List<PotionEffect> pe = new ArrayList<>();
		PotionEffect a = new PotionEffect(PotionEffectType.ABSORPTION, Integer.MAX_VALUE, 0, false, false);
		pe.add(a);
		return pe;
	}

	@Override
	public List<String> rules() {
		List<String> rules = new ArrayList<>();
		rules.add("§8[§4LG§8]§r Vous êtes \"§aCupidon§r\" vous devez gagner avec le §avillage§r  ou les §damoureux§r.");
		rules.add("§8[§4LG§8]§r Pour gagner avec le §avillage§r vous devez éliminer toute les menaces pour celui-ci.");
		rules.add("§8[§4LG§8]§r Pour gagner avec les §damoureux§r vous devez éliminer tout le monde sauf ceux-ci et vous-même.");
		rules.add("§8[§4LG§8]§r En tant que \"§aCupidon§r\" vous disposez d'un pouvoir §eactif§r et d'un pouvoir §9passif§r.");
		rules.add("§8[§4LG§8]§r §eActif§r: Au début de la partie vous devez utilisez la commande \"§6/amour [Joueur] [Joueur]§r\" pour choisir vos §damoureux§r ceux-ci seront lié par l'amour et mourront ensembe.");
		rules.add("§8[§4LG§8]§r §9Passif§r: Vous avez l'effet §5absorption§r.");
		return rules;
	}

}
