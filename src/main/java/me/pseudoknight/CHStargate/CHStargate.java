package me.pseudoknight.CHStargate;

import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.abstraction.Implementation;
import com.laytonsmith.core.extensions.AbstractExtension;
import com.laytonsmith.core.extensions.MSExtension;
import me.pseudoknight.CHStargate.abstraction.Stargate.Listener;

@MSExtension("CHStargate")
public class CHStargate extends AbstractExtension {

	public Version getVersion() {
		return new SimpleVersion(3,0,0);
	}

	@Override
	public void onStartup() {
		if(Implementation.GetServerType() == Implementation.Type.BUKKIT) {
			Listener.register();
			System.out.println("CHStargate " + getVersion() + " loaded.");
		} else {
			System.out.println("CHStargate not supported on this implementation.");
		}
	}

	@Override
	public void onShutdown() {
		if(Implementation.GetServerType() == Implementation.Type.BUKKIT) {
			Listener.unregister();
			System.out.println("CHStargate " + getVersion() + " unloaded.");
		}
	}

}
