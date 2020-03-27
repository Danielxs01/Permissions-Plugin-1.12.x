package de.landofrails.permissions.database;

import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;

public class PermAndAtt {

	private Permission permission = null;
	private PermissionAttachment permissionAttachment = null;

	public PermAndAtt(Permission permission, PermissionAttachment permissionAttachment) {
		this.permission = permission;
		this.permissionAttachment = permissionAttachment;
	}

	public Permission getPermission() {
		return permission;
	}

	public PermissionAttachment getPermissionAttachment() {
		return permissionAttachment;
	}

}
