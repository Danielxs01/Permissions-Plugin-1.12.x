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
		PlayerPAA playerPAA = null;
		ArrayList<PermAndAtt> list = new ArrayList<PermAndAtt>();

		try {
			playerPAA = playerPAAs.stream().filter(pp -> pp.getPlayer().equals(player)).findAny().get();
			list.addAll(playerPAA.getList());
			playerPAAs.remove(playerPAA);
		} catch (Exception e) {

		}

		playerPAA = new PlayerPAA(player, new ArrayList<PermAndAtt>());

		PermissionAttachment pa = player.addAttachment(perm);
		pa.setPermission(permission, true);
		list.add(new PermAndAtt(permission, pa));
		for (PermAndAtt paa : list)
			playerPAA.addToList(paa);
		playerPAAs.add(playerPAA);

	}

	public void removePermissionFromPlayer(Player player, String permission) {
		PlayerPAA playerPAA = null;
		try {
			playerPAA = playerPAAs.stream().filter(pp -> pp.getPlayer().equals(player)).findAny().get();
		} catch (Exception e) {

		}
		if (playerPAA != null) {

			if (playerPAA.hasPermission(permission)) {
				ArrayList<PermAndAtt> list = new ArrayList<PermAndAtt>();
				for (PermAndAtt paa : playerPAA.getList()) {
					if (paa.getPermission().equalsIgnoreCase(permission)) {

						PermissionAttachment pa = paa.getPermissionAttachment();
						pa.unsetPermission(permission);
						player.removeAttachment(pa);
						list.add(paa);

					}
				}
				for (PermAndAtt paa : list) {
					playerPAA.removeFromList(paa);
				}

			}

		}

	}

}
