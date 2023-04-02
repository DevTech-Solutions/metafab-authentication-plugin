package us.devtechsolutions.metafab;

import net.md_5.bungee.api.plugin.Plugin;

/**
 * @author LBuke (Teddeh)
 */
public final class BungeecordPlugin extends Plugin {

	@Override
	public void onEnable() {
		super.onEnable();

		this.getProxy().getScheduler().runAsync(this, () -> {
			this.getLogger().info("Starting Web Server");
//			Runner.run(Verticle.class);
		});
	}

	@Override
	public void onDisable() {
		super.onDisable();
	}
}
