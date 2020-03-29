package de.landofrails.permissions.database;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.bukkit.entity.Player;

import de.landofrails.permissions.Perm;
import de.landofrails.permissions.database.objects.DBPlayer;
import de.landofrails.permissions.handler.Group;

public class DatabaseHandler {

	// Hauptklasse und lokale Klasse
	private static Perm perm = null;
	private static DatabaseHandler handler = null;

	private DatabaseHandler() {
		// Private, da Singleton
		// (Nur eine Instanz erlaubt)
	}

	// [Singleton] Methode um die Instanz zu bekommen
	// Wird zu erst von der Hauptklasse [Perm] mit getInstance(this) ausgerufen
	public static DatabaseHandler getInstance(Perm perm) {
		if (perm != null)
			DatabaseHandler.perm = perm;
		if (handler == null)
			handler = new DatabaseHandler();
		return handler;
	}

	/*
	 * SPIELER
	 */
	// Speichern der Spielerdaten
	public void savePlayerData(Player player, Group group, ArrayList<String> permissions) {
		String path = "players." + player.getUniqueId().toString();
		perm.getConfig().set(path + ".name", player.getName());
		if (group != null)
			perm.getConfig().set(path + ".groupID", group.getGroupID());
		perm.getConfig().set(path + ".permissions", permissions);

		perm.saveConfig();
	}

	// Rückgabe der gespeicherten Spielerdaten
	public DBPlayer getDBPlayer(Player player) {

		perm.reloadConfig();

		String path = "players." + player.getUniqueId().toString();

		@SuppressWarnings("unchecked")
		ArrayList<String> permissions = (ArrayList<String>) perm.getConfig().getList(path + ".permissions");
		if (permissions == null)
			permissions = new ArrayList<String>();
		String groupID = perm.getConfig().getString(path + ".groupID");
		Group group;
		try {
			group = perm.getGroupHandler().getGroups().stream()
					.filter(g -> g.getGroupID().toString().equalsIgnoreCase(groupID)).findAny().get();
		} catch (Exception e) {
			group = null;
		}

		DBPlayer dbplayer = new DBPlayer(player.getUniqueId(), permissions, group);

		return dbplayer;
	}

	/*
	 * GRUPPEN
	 */
	public void saveGroupData(Group group) {
		String path = "groups." + group.getGroupID().toString();
		perm.getConfig().set(path + ".name", group.getGroupName());
		if (group.getExtendsGroupID() != null)
			perm.getConfig().set(path + ".extends", group.getExtendsGroupID().toString());
		perm.getConfig().set(path + ".permissions", group.getPermissions());
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Group> getGroups() {
		ArrayList<Group> groupList = new ArrayList<Group>();
		List<String> groups = (List<String>) perm.getConfig().getList("groups");
		if (groups != null)
			for (String group : groups) {
				String path = "groups." + group;
				UUID groupID = UUID.fromString(group);
				String name = perm.getConfig().getString(path + ".name");
				UUID extendsGroupID = null;
				if (perm.getConfig().contains(path + ".extends"))
					extendsGroupID = UUID.fromString(perm.getConfig().getString(path + ".extends"));
				ArrayList<String> permissions = new ArrayList<String>();
				if (perm.getConfig().contains(path + ".permissions")) {
					permissions.addAll((Collection<? extends String>) perm.getConfig().getList(path + ".permissions"));
				}
				groupList.add(new Group(groupID, name, permissions, extendsGroupID));
			}
		return groupList;
	}

}
