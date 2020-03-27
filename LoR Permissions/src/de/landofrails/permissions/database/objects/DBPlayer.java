package de.landofrails.permissions.database.objects;

import java.util.ArrayList;
import java.util.UUID;

public class DBPlayer {

	private UUID player = null;
	private ArrayList<String> permissions = null;
	private Group group = null;

	public DBPlayer(UUID player, ArrayList<String> permissions, Group group) {
		this.player = player;
		this.permissions = permissions;
		this.group = group;
	}

	public UUID getPlayer() {
		return player;
	}

	public ArrayList<String> getPermissions() {
		return permissions;
	}

	public Group getGroup() {
		return group;
	}

}
