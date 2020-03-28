package de.landofrails.permissions.otherevents;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.landofrails.permissions.Perm;
import de.landofrails.permissions.database.DatabaseHandler;
import de.landofrails.permissions.database.objects.Group;

public class PlayerLeave implements Listener {

	private DatabaseHandler handler = null;
	private Perm perm = null;

	// Übergeben der Hauptklasse (Perm) und Erstellung eines eigenen
	// DatenbankHandlers
	public PlayerLeave(Perm perm) {
		handler = DatabaseHandler.getInstance(null);
		this.perm = perm;
	}

	// Event Logik für "onPlayerLeave"
	@EventHandler
	public void onPlayerLeave(PlayerJoinEvent event) {
		// Der gegangene Spieler
		Player player = event.getPlayer();

		// Alle Permissions die der Spieler hat
		ArrayList<String> permissions = new ArrayList<String>();
		player.getEffectivePermissions().forEach(p -> permissions.add(p.getPermission()));

		// TODO: Implementierung der Gruppen
		Group group = null;

		// Speicher der Permissions und Gruppe in der Datenbank
		handler.savePlayerData(player, group, permissions);

		// TODO: Überprüfen auf Funktion.
		// Entfernen aller Berechtigungen
		for (String permission : permissions) {
			perm.getPermissionHandler().removePermissionFromPlayer(player, permission);
		}
	}

}
