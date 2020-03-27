package de.landofrails.permissions.commands;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import de.landofrails.permissions.MessageReceiver;
import de.landofrails.permissions.Perm;
import de.landofrails.permissions.commands.player.CH_Add;
import de.landofrails.permissions.commands.player.CH_CheckPlayer;
import de.landofrails.permissions.commands.player.CH_Help;
import de.landofrails.permissions.commands.player.CH_Permlist;
import de.landofrails.permissions.commands.player.CH_Remove;

public class CommandHandler implements CommandExecutor {

	private HashMap<String, CommandExecutor> param_handlers = new HashMap<String, CommandExecutor>();

	public CommandHandler(Perm perm) {

		// Player
		param_handlers.put("add", new CH_Add(perm));
		param_handlers.put("remove", new CH_Remove(perm));
		param_handlers.put("permlist", new CH_Permlist(perm));
		param_handlers.put("checkplayer", new CH_CheckPlayer(perm));
		param_handlers.put("help", new CH_Help(perm));

	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String arg2, String[] args) {

		MessageReceiver mr = new MessageReceiver(sender);

		if (args.length == 0) {
			mr.send("§4/" + command.getName() + " help §cfür Hilfe.");
		} else {
			if (param_handlers.containsKey(args[0].toLowerCase())) {
				param_handlers.get(args[0].toLowerCase()).onCommand(sender, command, arg2, args);
			} else {
				mr.syntax(command);
			}
		}

		return true;
	}

}
