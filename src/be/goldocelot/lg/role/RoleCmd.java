package be.goldocelot.lg.role;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * 
 * @author Nicolas Gerard (Goldocelot)
 * Class qui gére les commandes générale
 */
public class RoleCmd implements CommandExecutor {

	private RoleGuiManager rGManager;
	
	public RoleCmd(RoleGuiManager rGManager) {
		this.rGManager = rGManager;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		// On vérifie si l'utilisateur est un joueur car on ne peut pas utiliser ces commandes via la console du serveur
		if(sender instanceof Player) {
			Player p = (Player) sender;
			// On vérifie si le joueur est administrateur du serveur
			if(p.isOp()) {
				// On vérifie s'il tape la bonne commande
				if(label.equals("composition")) {
					// On envoie l'interface utilisateur au joueur ayant fait la commande
					rGManager.sendRoleGui(p);
				}
			}else p.sendMessage("§8[§4LG§8] §4Vous n'avez pas la permission de faire cette commande !"); // Message d'erreur si le joueur n'est pas administrateur
		}else sender.sendMessage("§8[§4LG§8] §4Seul un joueur peut faire cette commande !"); // Message d'erreur si la commande est utilisé sur une console
		return false;
	}

}
