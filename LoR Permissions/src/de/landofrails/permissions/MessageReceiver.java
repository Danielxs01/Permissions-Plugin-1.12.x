package de.landofrails.permissions;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

/***
 * 
 * @author Daniel
 *
 *         MessageReceiver Klasse zum Vereinfachen der Sendung von Nachrichten.
 *
 */
public class MessageReceiver {

	// Der Sender des Befehls; muss nur im Konstruktor übergeben werden
	private CommandSender sender = null;

	// Konstruktor mit dem Befehlsausführer
	public MessageReceiver(CommandSender sender) {
		this.sender = sender;
	}

	// Methode zum Senden von Nachrichten
	public void send(String text) {
		// Ersezten aller "&" durch "§"
		String msg = text.replaceAll("&", "§");
		// Überprüfen ob es sich um einen Spieler oder um die Konsole handelt
		if (sender instanceof ConsoleCommandSender)
			sender.sendMessage("§4§lLandOfRails §8> §7"
					+ msg.replaceAll("ü", "ue").replaceAll("ö", "oe").replaceAll("ä", "ae").replaceAll("ß", "ss"));
		else
			sender.sendMessage("§4§lLandOfRails §8» §7" + msg);
	}

	// Fertige Nachricht für einen Syntaxfehler
	public void syntax(Command command) {
		send("§cUngültige Syntax! §4/" + command.getName() + " help §cfür Hilfe.");
	}

	// Fertige Nachricht für einen unbekannten Spieler
	public void unknownPlayer(String player) {
		send("§4Spieler §c" + player + "§4 nicht gefunden!");
	}

}
