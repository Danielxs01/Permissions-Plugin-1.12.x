package de.landofrails.permissions.commands.player;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import de.landofrails.permissions.MessageReceiver;
import de.landofrails.permissions.Perm;

public class CH_CheckPlayer implements CommandExecutor {

	public CH_CheckPlayer(Perm perm) {
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String arg2, String[] args) {

		MessageReceiver mr = new MessageReceiver(sender);

		if (args.length != 3) {
			mr.syntax(command);
		} else {

			@SuppressWarnings("deprecation")
			Player player = Bukkit.getPlayer(args[1]);
			Permission permission = new Permission(args[2]);

			if (player != null) {
				if (player.hasPermission(permission))
					mr.send("§2Spieler §a" + player.getName() + " §2hat die Berechtigung \"§a" + permission.getName()
							+ "§2\".");
				else
					mr.send("§4Spieler §c" + player.getName() + " §4hat die Berechtigung \"§c" + permission.getName()
							+ "§4\" nicht.");
			} else {
				mr.unknownPlayer(args[1]);
			}

		}

		return true;
	}

}
