package me.pseudoknight.CHStargate.abstraction;

import com.laytonsmith.abstraction.MCLocation;
import com.laytonsmith.abstraction.MCPlayer;
import net.TheDgtl.Stargate.Portal;

public interface CHStargatePortalEvent extends CHStargateEvent {

	Portal getDestination();
	MCLocation getExit();
	void setExit(MCLocation exit);
	MCPlayer getPlayer();

}