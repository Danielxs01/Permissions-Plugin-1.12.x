package de.landofrails.permissions.otherevents;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.permissions.PermissionAttachment;

import de.landofrails.permissions.Perm;
import de.landofrails.permissions.database.DatabaseHandler;
import de.landofrails.permissions.database.objects.Group;

public class PlayerLeft implements Listener {

	private DatabaseHandler handler = null;
	private Perm perm = null;

	public PlayerLeft(Perm perm) {
		handler = DatabaseHandler.getInstance(null);
		this.perm = perm;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		ArrayList<String> permissions = new ArrayList<String>();
		player.getEffectivePermissions().forEach(p -> permissions.add(p.getPermission()));
		// TODO: Implementierung der Gruppen
		Group group = null;
		handler.savePlayerData(player, group, permissions);

		// Entfernen aller Berechtigungen
		for (String permission : permissions) {
			PermissionAttachment attachment = player.addAttachment(perm);
			attachment.setPermission(permission, false);
			player.removeAttachment(attachment);
		}
	}

}
