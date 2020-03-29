package de.landofrails.permissions.handler;

import java.util.ArrayList;

public class GroupHandler {

	private static GroupHandler groupHandler = null;

	private ArrayList<Group> groups = new ArrayList<Group>();

	private GroupHandler() {

	}

	public static GroupHandler getInstance() {
		if (groupHandler == null)
			groupHandler = new GroupHandler();
		return groupHandler;
	}

	public ArrayList<Group> getGroups() {
		return groups;
	}

	public void addGroup(Group group) {
		groups.add(group);
	}

	public void init(ArrayList<Group> groups) {
		this.groups.addAll(groups);
	}

}
