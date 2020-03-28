package de.landofrails.permissions.database.objects;

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
	 * Methode zum �ndern des Prefixes. Gibt die Gruppe mit der Anpassung zur�ck
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
	 * Methode zum �ndern des Suffixes. Gibt die Gruppe mit der Anpassung zur�ck
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
	public Group getExtendsGroup() {
		return extendsGroup;
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

	// Gibt den Prefix zur�ck
	public String getPrefix() {
		return prefix == null ? groupName : prefix;
	}

	// Gibt den Suffix zur�ck
	public String getSuffix() {
		return suffix == null ? "" : suffix;
	}

}
