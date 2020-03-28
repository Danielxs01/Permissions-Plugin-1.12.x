package de.landofrails.permissions.handler;

import org.bukkit.permissions.PermissionAttachment;

/***
 * 
 * Objekt welches die jeweilige Permission und das jeweilige
 * PermissionAttachment speichert
 * 
 * @author Daniel
 *
 */
public class PermAndAtt {

	private String permission = null;
	private PermissionAttachment permissionAttachment = null;

	// Konstruktur zum Speichern der Variablen
	public PermAndAtt(String permission2, PermissionAttachment permissionAttachment) {
		this.permission = permission2;
		this.permissionAttachment = permissionAttachment;
	}

	// Gibt die Permission zurück
	public String getPermission() {
		return permission;
	}

	// Gibt das PermissionAttachment zurück
	public PermissionAttachment getPermissionAttachment() {
		return permissionAttachment;
	}

}
