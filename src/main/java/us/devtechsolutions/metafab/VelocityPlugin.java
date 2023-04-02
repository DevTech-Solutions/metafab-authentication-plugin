package us.devtechsolutions.metafab;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import org.slf4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author LBuke (Teddeh)
 */
@Plugin(id = "metafab-auth",
		name = "MetaFab-Authentication",
		version = "0.1.0",
		url = "https://github.com/DevTech-Solutions/metafab-authentication-plugin",
		description = "Authenticate Minecraft Players with your MetaFab Ecosystem & Game",
		authors = { "DevTech-Solutions" }
)
public final class VelocityPlugin {

	private final ExecutorService executorService = Executors.newCachedThreadPool();

	private final ProxyServer server;
	private final Logger logger;

	@Inject
	public VelocityPlugin(ProxyServer server, Logger logger) {
		this.server = server;
		this.logger = logger;
	}

	@Subscribe
	public void onProxyInitialization(ProxyInitializeEvent event) {
		executorService.submit(() -> {
			this.logger.info("Starting Web Server");
//			Runner.run(Verticle.class);
		});
	}
}
