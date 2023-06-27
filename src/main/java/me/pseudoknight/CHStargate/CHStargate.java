package me.pseudoknight.CHStargate;

import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.core.Static;
import com.laytonsmith.core.extensions.AbstractExtension;
import com.laytonsmith.core.extensions.MSExtension;
import me.pseudoknight.CHStargate.abstraction.Stargate.Listener;

@MSExtension("CHStargate")
public class CHStargate extends AbstractExtension {

	public Version getVersion() {
		return new SimpleVersion(3,1,0);
	}

	@Override
	public void onStartup() {
		Listener.register();
		Static.getLogger().info("CHStargate " + getVersion() + " loaded.");
	}

	@Override
	public void onShutdown() {
		Listener.unregister();
		Static.getLogger().info("CHStargate " + getVersion() + " unloaded.");
	}

}
