package de.landofrails.permissions.commands.player;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import de.landofrails.permissions.MessageReceiver;
import de.landofrails.permissions.Perm;

public class CH_Help implements CommandExecutor {

	// Implementation von /perm help

	public CH_Help(Perm perm) {
		// Nicht notwendig in diesem Szenario
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String arg2, String[] args) {

		MessageReceiver mr = new MessageReceiver(sender);

		mr.send("§2Hilfe für §9/perm");
		mr.send("§aBefehle:");
		mr.send("§7/perm §8[§6add§8/§6remove§8] §8[§eSpieler§8] §8[§fPermission§8]");
		mr.send("§7/perm §6permList §8[§eSpieler§8]");
		mr.send("§7/perm §6checkPlayer §8[§eSpieler§8] [§fPermission§8]");
		mr.send("§cMehr folgen bald..");

		return true;
	}

}
