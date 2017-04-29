package me.pseudoknight.CHStargate.abstraction;

import com.laytonsmith.abstraction.MCPlayer;

public interface CHStargateDestroyEvent extends CHStargateEvent {

	MCPlayer getPlayer();
	boolean getDeny();
	void setDeny(boolean deny);
	String getDenyReason();
	void setDenyReason(String denyReason);
	int getCost();
	void setCost(int cost);

}
