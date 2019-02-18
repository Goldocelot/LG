package be.goldocelot.lg.role.playable.sorcière;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import be.goldocelot.lg.Main;
import be.goldocelot.lg.role.RoleEnum;
import be.goldocelot.lg.role.RoleManager;

public class SorcièreEvents implements Listener {
	
	private Main main;
	private Player lastDeath;
	private SorcièreCmd sCmd;
	private RoleManager rMan;
	
	public SorcièreEvents(SorcièreCmd sCmd, RoleManager rMan, Main main) {
		this.sCmd = sCmd;
		this.rMan = rMan;
		this.main = main;
	}
	
	@EventHandler
	private void onDeath(PlayerDeathEvent e) {
		Player p = e.getEntity();
		List<ItemStack> items = e.getDrops();
		//tp le joueur en sécu
		if(sCmd.getVie()) {
			lastDeath = p;
			lastDeath.sendMessage("Vous êtes ");
			Player soso = rMan.getPlayer(RoleEnum.SORCIÈRE);
			soso.sendMessage("§8[§4LG§8] §r"+p.getName()+" est mort vous avez 30 secondes pour utiliser \"§6/vie§r\" si vous voulez le sauver.");
			Bukkit.getScheduler().scheduleSyncDelayedTask(main, new Runnable() {
				@Override
				public void run() {
					lastDeath = null;
					soso.sendMessage("§8[§4LG§8] §r"+p.getName()+" est mort définitivement.");
				}
			},600L);
		}
	}
	
}
