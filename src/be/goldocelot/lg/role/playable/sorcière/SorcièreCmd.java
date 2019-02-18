package be.goldocelot.lg.role.playable.sorcière;


import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import be.goldocelot.lg.role.RoleEnum;
import be.goldocelot.lg.role.RoleManager;

public class SorcièreCmd implements CommandExecutor {

	private boolean vie = true;
	private boolean mort = true;
	private RoleManager rMan;
	
	public SorcièreCmd(RoleManager rMan) {
		this.rMan = rMan;
	}
	
	public boolean getVie() {
		return vie;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(rMan.getRole(p).equals(RoleEnum.SORCIÈRE)) {
				if(label.equals("mort")) {
					if(mort) {
						boolean used = false;
						for(Player inGame: Bukkit.getOnlinePlayers()) {
							if(args[0].equals(inGame.getName())) {
								used = true;
								Player target = inGame;
								target.setMaxHealth(target.getMaxHealth()-8.);
								mort=false;
								break;
							}
						}
						if(!used) {
							p.sendMessage("§8[§4LG§8] §4Le joueur visé n'existe pas !");
						}
					}else p.sendMessage("§8[§4LG§8] §4Vous avez déjà utilisé ce pouvoir !");
				}else if(label.equals("vie")) {
					if(vie) {
						
					}else p.sendMessage("§8[§4LG§8] §4Vous avez déjà utilisé ce pouvoir !");
				}else p.sendMessage("§8[§4LG§8] §4Commande invalide !");
			}else p.sendMessage("§8[§4LG§8] §4Seul la sorcière peut utiliser ces pouvoirs !");
		}else sender.sendMessage("§8[§4LG§8] §4Seul un joueur peut faire cette commande !");
		return false;
	}

}
