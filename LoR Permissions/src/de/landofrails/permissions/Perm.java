package de.landofrails.permissions;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.java.JavaPlugin;

import de.landofrails.permissions.commands.CommandHandler;
import de.landofrails.permissions.database.DatabaseHandler;
import de.landofrails.permissions.handler.GroupHandler;
import de.landofrails.permissions.handler.PermissionHandler;
import de.landofrails.permissions.otherevents.PlayerJoin;
import de.landofrails.permissions.otherevents.PlayerLeave;

public class Perm extends JavaPlugin implements Listener {

	// Logger für die Hauptklasse
	private Logger logger = Logger.getLogger("LoR Permissions");
	// perm, die einzige Instanz von dieser Klasse
	private static Perm perm = null;
	// Variable für den DatabaseHandler
	public DatabaseHandler database = null;
	//
	private PermissionHandler permissionHandler = null;
	private GroupHandler groupHandler = null;

	@Override
	public void onLoad() {
		// TODO: Unklar ob nötig
	}

	// Beim Start
	@Override
	public void onEnable() {

		logger.info("Starten..");

		// Speichern der aktuellen Instanz
		perm = this;

		// Config konfigurieren und den DatabaseHandler initialisieren
		getConfig().options().copyDefaults(true);
		database = DatabaseHandler.getInstance(this);

		// Initialisierung des PermissionHandlers
		permissionHandler = PermissionHandler.getInstance(this);
		groupHandler = GroupHandler.getInstance();
		groupHandler.init(database.getGroups());

		// Der Konsole die /perm Permission ("landofrails.permissions.*") geben
		PermissionAttachment permAtt = this.getServer().getConsoleSender().addAttachment(this);
		permAtt.setPermission("landofrails.permissions.*", true);

		// Den CommandHandler registrieren
		getCommand("perm").setExecutor(new CommandHandler(perm));

		// PlayerJoin und -Leave registrieren
		Bukkit.getPluginManager().registerEvents(new PlayerJoin(this), this);
		Bukkit.getPluginManager().registerEvents(new PlayerLeave(this), this);

		logger.info("Gestartet..");

	}

	public PermissionHandler getPermissionHandler() {
		return permissionHandler;
	}

	@Override
	public void onDisable() {

		// Information, dass das Plugin stoppt
		logger.info("Stoppen..");

	}

	public GroupHandler getGroupHandler() {
		return groupHandler;
	}

}
