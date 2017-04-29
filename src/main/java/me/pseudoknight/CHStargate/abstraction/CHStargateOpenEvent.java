package me.pseudoknight.CHStargate.abstraction;

import com.laytonsmith.abstraction.MCPlayer;

public interface CHStargateOpenEvent extends CHStargateEvent {

	MCPlayer getPlayer();
	boolean getForce();
	void setForce(boolean force);

}