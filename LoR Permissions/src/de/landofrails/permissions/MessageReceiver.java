package de.landofrails.permissions;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

public class MessageReceiver {

	private CommandSender sender = null;

	public MessageReceiver(CommandSender sender) {
		this.sender = sender;
	}

	public void send(String text) {
		String msg = text.replaceAll("&[§\\d]", "§");
		if (sender instanceof ConsoleCommandSender)
			sender.sendMessage("§4§lLandOfRails §8> §7"
					+ msg.replaceAll("ü", "ue").replaceAll("ö", "oe").replaceAll("ä", "ae").replaceAll("ß", "ss"));
		else
			sender.sendMessage("§4§lLandOfRails §8» §7" + msg);
	}

	public void syntax(Command command) {
		send("§cUngültige Syntax! §4/" + command.getName() + " help §cfür Hilfe.");
	}

	public void unknownPlayer(String player) {
		send("§4Spieler §c" + player + "§4 nicht gefunden!");
	}

}
