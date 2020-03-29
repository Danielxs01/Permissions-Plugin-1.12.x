package de.landofrails.permissions.handler;

import java.util.ArrayList;
import java.util.UUID;

/***
 * 
 * Objekt für eine Gruppe
 * 
 * @author Daniel
 *
 */
public class Group {

	// Gruppenvariablen
	private UUID groupID = null;
	private String groupName = null;
	private ArrayList<String> permissions = new ArrayList<String>();
	private UUID extendsGroupID = null;

	// Konstruktor
	public Group(UUID groupID, String groupName, ArrayList<String> permissions, UUID extendsGroupID) {
		this.groupID = groupID;
		this.groupName = groupName;
		this.permissions = permissions;
		this.extendsGroupID = extendsGroupID;
	}

	/**
	 * 
	 * Methode zum Ändern des Namens. Gibt die Gruppe mit der Anpassung zurück
	 * 
	 * @param name
	 * @return
	 */
	public Group setName(String name) {
		this.groupName = name;
		return this;
	}

	// Gibt die Gruppe zurück, welche durch diese erweitert wird.
	public UUID getExtendsGroupID() {
		return extendsGroupID;
	}

	// Gibt die GruppenID (UUID) zurück
	public UUID getGroupID() {
		return groupID;
	}

	// Gibt den Gruppennamen zurück
	public String getGroupName() {
		return groupName;
	}

	// Gibt die Liste mit Berechtigungen zurück
	public ArrayList<String> getPermissions() {
		return permissions;
	}
}
