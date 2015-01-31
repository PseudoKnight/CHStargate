package me.pseudoknight.CHStargate.abstraction;

import com.laytonsmith.abstraction.MCPlayer;

public interface CHStargateDestroyEvent extends CHStargateEvent {

    public abstract MCPlayer getPlayer();
    public abstract boolean getDeny();
    public abstract void setDeny(boolean deny);
    public abstract String getDenyReason();
    public abstract void setDenyReason(String denyReason);
    public abstract int getCost();
    public abstract void setCost(int cost);

}
