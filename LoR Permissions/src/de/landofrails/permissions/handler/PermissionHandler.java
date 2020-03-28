package de.landofrails.permissions.handler;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import de.landofrails.permissions.Perm;

// -> Ändert Spielerpermissions und speichert die jeweiligen PermissionAttachments
public class PermissionHandler {

	// Singleton

	private static Perm perm = null;
	private static PermissionHandler permissionHandler = null;

	private PermissionHandler() {

	}

	public static PermissionHandler getInstance(Perm perm) {

		if (perm == null)
			throw new NullPointerException();
		PermissionHandler.perm = perm;

		if (permissionHandler == null)
			permissionHandler = new PermissionHandler();
		return permissionHandler;

	}

	// Tatsächlicher Code

	private ArrayList<PlayerPAA> playerPAAs = new ArrayList<PlayerPAA>();

	public void addPermissionToPlayer(Player player, String permission) {

		// Hat der Spieler einen Eintrag oder muss einer erstellt werden?
		PlayerPAA playerPAA = playerPAAs.stream().filter(pp -> pp.getPlayer().equals(player)).findAny().get();
		if (playerPAA == null) {
			playerPAA = new PlayerPAA(player, new ArrayList<PermAndAtt>());
		}

		if (!playerPAA.hasPermission(permission)) {

			PermissionAttachment pa = player.addAttachment(perm);
			pa.setPermission(permission, true);
			playerPAA.addToList(new PermAndAtt(permission, pa));

		}

	}

	public void removePermissionFromPlayer(Player player, String permission) {

		PlayerPAA playerPAA = playerPAAs.stream().filter(pp -> pp.getPlayer().equals(player)).findAny().get();
		if (playerPAA != null) {

			if (playerPAA.hasPermission(permission)) {

				for (PermAndAtt paa : playerPAA.getList()) {
					if (paa.getPermission().equalsIgnoreCase(permission)) {
						PermissionAttachment pa = paa.getPermissionAttachment();
						pa.setPermission(permission, false);
						player.removeAttachment(pa);
						playerPAA.removeFromList(paa);
					}
				}

			}

		}

	}
}
