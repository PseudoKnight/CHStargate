package me.pseudoknight.CHStargate.abstraction.Stargate;

import com.laytonsmith.abstraction.Implementation;
import com.laytonsmith.abstraction.MCPlayer;
import com.laytonsmith.abstraction.bukkit.entities.BukkitMCPlayer;
import com.laytonsmith.annotations.abstraction;
import me.pseudoknight.CHStargate.abstraction.CHStargateAccessEvent;
import me.pseudoknight.CHStargate.abstraction.CHStargateDestroyEvent;
import me.pseudoknight.CHStargate.abstraction.CHStargateEvent;
import me.pseudoknight.CHStargate.abstraction.CHStargateOpenEvent;
import net.TheDgtl.Stargate.Portal;
import net.TheDgtl.Stargate.event.StargateAccessEvent;
import net.TheDgtl.Stargate.event.StargateDestroyEvent;
import net.TheDgtl.Stargate.event.StargateEvent;
import net.TheDgtl.Stargate.event.StargateOpenEvent;

public class Events {

	@abstraction(type = Implementation.Type.BUKKIT)
	public static class BukkitStargateEvent implements CHStargateEvent {

		private final StargateEvent e;

		public BukkitStargateEvent(StargateEvent event) {
			e = event;
		}

		@Override
		public Object _GetObject() {
			return e;
		}

		@Override
		public Portal getPortal() {
			return e.getPortal();
		}

	}

	@abstraction(type = Implementation.Type.BUKKIT)
	public static class BukkitStargateDestroyEvent extends BukkitStargateEvent implements CHStargateDestroyEvent {

		private final StargateDestroyEvent e;

		public BukkitStargateDestroyEvent(StargateDestroyEvent event) {
			super(event);
			e = event;
		}

		@Override
		public Object _GetObject() {
			return e;
		}

		@Override
		public MCPlayer getPlayer() {
			return new BukkitMCPlayer(e.getPlayer());
		}

		@Override
		public boolean getDeny() {
			return e.getDeny();
		}

		@Override
		public void setDeny(boolean deny) {
			e.setDeny(deny);
		}

		@Override
		public String getDenyReason() {
			return e.getDenyReason();
		}

		@Override
		public void setDenyReason(String denyReason) {
			e.setDenyReason(denyReason);
		}

		@Override
		public int getCost() {
			return e.getCost();
		}

		@Override
		public void setCost(int cost) {
			e.setCost(cost);
		}

	}

	@abstraction(type = Implementation.Type.BUKKIT)
	public static class BukkitStargateAccessEvent extends BukkitStargateEvent implements CHStargateAccessEvent {

		private final StargateAccessEvent e;

		public BukkitStargateAccessEvent(StargateAccessEvent event) {
			super(event);
			e = event;
		}

		@Override
		public Object _GetObject() {
			return e;
		}

		@Override
		public boolean getDeny() {
			return e.getDeny();
		}

		@Override
		public void setDeny(boolean deny) {
			e.setDeny(deny);
		}

		@Override
		public MCPlayer getPlayer() {
			return new BukkitMCPlayer(e.getPlayer());
		}

	}

	@abstraction(type = Implementation.Type.BUKKIT)
	public static class BukkitStargateOpenEvent extends BukkitStargateEvent implements CHStargateOpenEvent {

		private final StargateOpenEvent e;

		public BukkitStargateOpenEvent(StargateOpenEvent event) {
			super(event);
			e = event;
		}

		@Override
		public Object _GetObject() {
			return e;
		}

		@Override
		public MCPlayer getPlayer() {
			return new BukkitMCPlayer(e.getPlayer());
		}

		@Override
		public boolean getForce() {
			return e.getForce();
		}

		@Override
		public void setForce(boolean force) {
			e.setForce(force);
		}

	}
}
