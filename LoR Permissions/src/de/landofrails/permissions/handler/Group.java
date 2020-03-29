package de.landofrails.permissions.handler;

import java.util.ArrayList;
import java.util.UUID;

/***
 * 
 * Objekt f�r eine Gruppe
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
	 * Methode zum �ndern des Namens. Gibt die Gruppe mit der Anpassung zur�ck
	 * 
	 * @param name
	 * @return
	 */
	public Group setName(String name) {
		this.groupName = name;
		return this;
	}

	// Gibt die Gruppe zur�ck, welche durch diese erweitert wird.
	public UUID getExtendsGroupID() {
		return extendsGroupID;
	}

	// Gibt die GruppenID (UUID) zur�ck
	public UUID getGroupID() {
		return groupID;
	}

	// Gibt den Gruppennamen zur�ck
	public String getGroupName() {
		return groupName;
	}

	// Gibt die Liste mit Berechtigungen zur�ck
	public ArrayList<String> getPermissions() {
		return permissions;
	}
}
