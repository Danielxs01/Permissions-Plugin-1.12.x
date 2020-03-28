package de.landofrails.permissions.database.objects;

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
	private Group extendsGroup = null;
	private String prefix = null;
	private String suffix = null;

	// Konstruktor
	public Group(UUID groupID, String groupName, ArrayList<String> permissions, Group extendsGroup) {
		this.groupID = groupID;
		this.groupName = groupName;
		this.permissions = permissions;
		this.extendsGroup = extendsGroup;
	}

	/**
	 * 
	 * Methode zum Ändern des Prefixes. Gibt die Gruppe mit der Anpassung zurück
	 * 
	 * @param prefix
	 * @return
	 */
	public Group setPrefix(String prefix) {
		this.prefix = prefix;
		return this;
	}

	/**
	 * 
	 * Methode zum Ändern des Suffixes. Gibt die Gruppe mit der Anpassung zurück
	 * 
	 * @param suffix
	 * @return
	 */
	public Group setSuffix(String suffix) {
		this.suffix = suffix;
		return this;
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
	public Group getExtendsGroup() {
		return extendsGroup;
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

	// Gibt den Prefix zurück
	public String getPrefix() {
		return prefix == null ? groupName : prefix;
	}

	// Gibt den Suffix zurück
	public String getSuffix() {
		return suffix == null ? "" : suffix;
	}

}
