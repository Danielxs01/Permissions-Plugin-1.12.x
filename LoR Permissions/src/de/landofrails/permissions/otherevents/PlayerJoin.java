package de.landofrails.permissions.otherevents;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import de.landofrails.permissions.Perm;
import de.landofrails.permissions.database.DatabaseHandler;
import de.landofrails.permissions.database.objects.DBPlayer;
import de.landofrails.permissions.database.objects.Group;

/***
 * 
 * @author Daniel
 *
 */
public class PlayerJoin implements Listener {

	private DatabaseHandler handler = null;
	private Perm perm = null;

	// Konstruktur mit der Hauptklasse (Perm) als Parameter
	public PlayerJoin(Perm perm) {
		handler = DatabaseHandler.getInstance(null);
		this.perm = perm;
	}

	@EventHandler
	// Event Logik für "onPlayerJoin"
	public void onPlayerJoin(PlayerJoinEvent event) {
		// Spieler der gejoint ist
		Player player = event.getPlayer();

		// Spielerdaten aus der Datenbank holen
		DBPlayer dbplayer = handler.getDBPlayer(player);

		// Permissions rausholen
		ArrayList<String> permissions = dbplayer.getPermissions();

		// TODO: Weitere Überarbeitung bezüglich der Gruppen
		Group group = dbplayer.getGroup();
		if (group != null) {
			permissions.addAll(group.getPermissions());
		}

		// Alle Berechtigungen wieder geben
		for (String permission : permissions) {
			perm.getPermissionHandler().addPermissionToPlayer(player, permission);
		}
	}

}
