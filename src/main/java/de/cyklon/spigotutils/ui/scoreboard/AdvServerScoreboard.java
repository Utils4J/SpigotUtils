package de.cyklon.spigotutils.ui.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

final class AdvServerScoreboard extends DefaultAdvScoreboard implements Listener {

	AdvServerScoreboard(Plugin plugin) {
		super(Bukkit.getOnlinePlayers().toArray(Player[]::new));
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		if (isDeleted()) return;
		Player player = event.getPlayer();
		if (!addPlayer(player)) show(player);
	}

	@Override
	protected boolean addPlayer(Player player) {
		boolean f = super.addPlayer(player);
		if (f) show(player);
		return f;
	}
}