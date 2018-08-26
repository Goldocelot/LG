package be.goldocelot.lg.role;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RoleCmd implements CommandExecutor {

	private RoleGuiManager rGManager;
	
	public RoleCmd(RoleGuiManager rGManager) {
		this.rGManager = rGManager;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player p = (Player) sender;
			if(p.isOp()) {
				if(label.equals("composition")) {
					rGManager.sendRoleGui(p);
				}
			}else p.sendMessage("§8[§4LG§8] §4Vous n'avez pas la permission de faire cette commande !");
		}else sender.sendMessage("§8[§4LG§8] §4Seul un joueur peut faire cette commande !");
		return false;
	}

}
