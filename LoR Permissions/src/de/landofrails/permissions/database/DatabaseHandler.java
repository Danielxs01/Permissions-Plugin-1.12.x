package de.landofrails.permissions.database;

import java.util.ArrayList;

import org.bukkit.entity.Player;

import de.landofrails.permissions.Perm;
import de.landofrails.permissions.database.objects.DBPlayer;
import de.landofrails.permissions.database.objects.Group;

public class DatabaseHandler {

	private static Perm perm = null;
	private static DatabaseHandler handler = null;

	private ArrayList<Group> groups = new ArrayList<Group>();

	private DatabaseHandler() {

	}

	public static DatabaseHandler getInstance(Perm perm) {
		if (perm != null)
			DatabaseHandler.perm = perm;
		if (handler == null)
			handler = new DatabaseHandler();
		return handler;
	}

	public void savePlayerData(Player player, Group group, ArrayList<String> permissions) {
		String path = "players." + player.getUniqueId().toString();
		perm.getConfig().set(path + ".name", player.getName());
		if (group != null)
			perm.getConfig().set(path + ".groupID", group.getGroupID());
		perm.getConfig().set(path + ".permissions", permissions);

		perm.saveConfig();
	}

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
			group = groups.stream().filter(g -> g.getGroupID().toString().equalsIgnoreCase(groupID)).findAny().get();
		} catch (Exception e) {
			group = null;
		}

		DBPlayer dbplayer = new DBPlayer(player.getUniqueId(), permissions, group);

		return dbplayer;
	}

}
