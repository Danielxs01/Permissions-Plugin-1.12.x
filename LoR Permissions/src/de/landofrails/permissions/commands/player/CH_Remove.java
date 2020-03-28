package de.landofrails.permissions.commands.player;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.landofrails.permissions.MessageReceiver;
import de.landofrails.permissions.Perm;

public class CH_Remove implements CommandExecutor {

	private Perm perm = null;

	public CH_Remove(Perm perm) {
		this.perm = perm;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String arg2, String[] args) {
		MessageReceiver mr = new MessageReceiver(sender);

		if (args.length != 3) {
			mr.syntax(command);
			return true;
		}

		@SuppressWarnings("deprecation")
		Player player = Bukkit.getPlayer(args[1]);
		String permission = args[2];

		if (player != null) {

			perm.getPermissionHandler().removePermissionFromPlayer(player, permission);

			mr.send("§6Spieler §e" + player.getName() + " §6wurde die Permission \"§e" + permission + " §6\"entzogen!");

		} else {

			mr.unknownPlayer(args[1]);

		}

		return true;
	}

}
