package me.pseudoknight.CHStargate.abstraction;

import com.laytonsmith.abstraction.MCPlayer;

public interface CHStargateAccessEvent extends CHStargateEvent {

    public abstract boolean getDeny();
    public abstract void setDeny(boolean deny);
    public abstract MCPlayer getPlayer();

}