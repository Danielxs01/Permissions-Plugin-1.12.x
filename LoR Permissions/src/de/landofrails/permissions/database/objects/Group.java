package de.landofrails.permissions.database.objects;

import java.util.ArrayList;
import java.util.UUID;

public class Group {

	private UUID groupID = null;
	private String groupName = null;
	private ArrayList<String> permissions = new ArrayList<String>();
	private Group extendsGroup = null;
	private String prefix = null;
	private String suffix = null;

	public Group(UUID groupID, String groupName, ArrayList<String> permissions, Group extendsGroup) {
		this.groupID = groupID;
		this.groupName = groupName;
		this.permissions = permissions;
		this.extendsGroup = extendsGroup;
	}

	public Group setPrefix(String prefix) {
		this.prefix = prefix;
		return this;
	}

	public Group setSuffix(String suffix) {
		this.suffix = suffix;
		return this;
	}

	public Group setName(String name) {
		this.groupName = name;
		return this;
	}

	public UUID getGroupID() {
		return groupID;
	}

	public String getGroupName() {
		return groupName;
	}

	public ArrayList<String> getPermissions() {
		return permissions;
	}

	public Group getExtendsGroup() {
		return extendsGroup;
	}

	public String getPrefix() {
		return prefix == null ? groupName : prefix;
	}

	public String getSuffix() {
		return suffix == null ? "" : suffix;
	}

}
