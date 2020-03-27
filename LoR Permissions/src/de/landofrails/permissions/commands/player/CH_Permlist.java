package de.landofrails.permissions.commands.player;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.landofrails.permissions.MessageReceiver;
import de.landofrails.permissions.Perm;

public class CH_Permlist implements CommandExecutor {

	public CH_Permlist(Perm perm) {

	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String arg2, String[] args) {

		MessageReceiver mr = new MessageReceiver(sender);

		if (args.length == 2) {

			@SuppressWarnings("deprecation")
			Player player = Bukkit.getPlayer(args[1]);

			if (player != null) {

				mr.send("§8Spielerpermissions:");

				player.getEffectivePermissions().forEach(perm -> mr.send("§7  " + perm.getPermission()));

				// TODO: Implementierung für die Gruppenpermissions

			} else {
				mr.unknownPlayer(args[1]);
			}

		} else {
			mr.syntax(command);
		}

		return true;
	}

}
