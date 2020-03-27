package de.landofrails.permissions.otherevents;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.permissions.PermissionAttachment;

import de.landofrails.permissions.Perm;
import de.landofrails.permissions.database.DatabaseHandler;
import de.landofrails.permissions.database.objects.DBPlayer;
import de.landofrails.permissions.database.objects.Group;

public class PlayerJoin implements Listener {

	private DatabaseHandler handler = null;
	private Perm perm = null;

	public PlayerJoin(Perm perm) {
		handler = DatabaseHandler.getInstance(null);
		this.perm = perm;
	}

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();

		DBPlayer dbplayer = handler.getDBPlayer(player);

		ArrayList<String> permissions = dbplayer.getPermissions();

		Group group = dbplayer.getGroup();
		if (group != null) {
			permissions.addAll(group.getPermissions());
		}

		// Alle Berechtigungen wieder geben
		for (String permission : permissions) {
			PermissionAttachment attachment = player.addAttachment(perm);
			attachment.setPermission(permission, true);
		}
	}

}
