package me.pseudoknight.CHStargate.abstraction;

import com.laytonsmith.core.events.BindableEvent;
import net.TheDgtl.Stargate.Portal;

public interface CHStargateEvent extends BindableEvent {

	Portal getPortal();

}