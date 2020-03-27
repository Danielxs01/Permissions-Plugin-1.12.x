package de.landofrails.permissions;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import de.landofrails.permissions.commands.CommandHandler;
import de.landofrails.permissions.database.DatabaseHandler;
import de.landofrails.permissions.otherevents.PlayerJoin;
import de.landofrails.permissions.otherevents.PlayerLeft;

public class Perm extends JavaPlugin implements Listener {

	private Logger logger = OwnLogger.getLogger();
	private static Perm perm = null;
	public DatabaseHandler database = null;

	@Override
	public void onEnable() {

		perm = this;

		getConfig().options().copyDefaults(true);
		database = DatabaseHandler.getInstance(this);

		logger.info("Starten..");

		PermissionAttachment permAtt = this.getServer().getConsoleSender().addAttachment(this);
		permAtt.setPermission("landofrails.permissions.*", true);

		for (String commands : getCommandAndAliases())
			getCommand(commands).setExecutor(new CommandHandler(perm));

		Bukkit.getPluginManager().registerEvents(new PlayerJoin(this), this);
		Bukkit.getPluginManager().registerEvents(new PlayerLeft(this), this);

	}

	@Override
	public void onLoad() {

		logger.info("Neuladen..");

	}

	@Override
	public void onDisable() {

		logger.info("Stoppen..");

	}

	private ArrayList<String> getCommandAndAliases() {
		ArrayList<String> list = new ArrayList<String>();
		PluginDescriptionFile file = this.getDescription();
		for (String cmd : file.getCommands().keySet()) {
			list.add(cmd);
			if (file.getCommands().get(cmd).containsKey("aliases")) {
				String raw = "" + file.getCommands().get(cmd).get("aliases");
				raw = raw.substring(1, raw.length() - 1);
				for (String alias : raw.split(","))
					list.add(alias.trim());
			}
		}
		return list;
	}

}
