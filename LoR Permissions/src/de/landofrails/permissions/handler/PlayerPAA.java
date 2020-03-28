package de.landofrails.permissions.handler;

import java.util.ArrayList;

import org.bukkit.entity.Player;

public class PlayerPAA {

	private Player player = null;
	private ArrayList<PermAndAtt> list = null;

	public PlayerPAA(Player player, ArrayList<PermAndAtt> list) {
		this.player = player;
		this.list = list;
	}

	public Player getPlayer() {
		return player;
	}

	public ArrayList<PermAndAtt> getList() {
		return list;
	}

	public void addToList(PermAndAtt paa) {
		list.add(paa);
	}

	public void removeFromList(PermAndAtt paa) {
		list.remove(paa);
	}

	public boolean hasPermission(String permission) {
		for (PermAndAtt paa : list) {
			if (paa.getPermission().equalsIgnoreCase(permission))
				return true;
		}
		return false;
	}
}
