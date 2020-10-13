package me.pseudoknight.CHStargate;

import com.laytonsmith.abstraction.MCPlayer;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.MSVersion;
import com.laytonsmith.core.constructs.CArray;
import com.laytonsmith.core.constructs.CString;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.events.AbstractEvent;
import com.laytonsmith.core.events.BindableEvent;
import com.laytonsmith.core.events.Driver;
import com.laytonsmith.core.events.Prefilters;
import com.laytonsmith.core.exceptions.EventException;
import com.laytonsmith.core.exceptions.PrefilterNonMatchException;
import com.laytonsmith.core.natives.interfaces.Mixed;
import me.pseudoknight.CHStargate.abstraction.CHStargateAccessEvent;
import me.pseudoknight.CHStargate.abstraction.CHStargateDestroyEvent;
import me.pseudoknight.CHStargate.abstraction.CHStargateOpenEvent;

import java.util.HashMap;
import java.util.Map;

public class CHEvents {

	protected static abstract class StargateEvent extends AbstractEvent {
		@Override
		public Driver driver() {
			return Driver.EXTENSION;
		}

		@Override
		public MSVersion since() {
			return MSVersion.V3_3_1;
		}

		@Override
		public BindableEvent convert(CArray manualObject, Target t) {
			return null;
		}
	}

	@api
	public static class stargate_destroy extends StargateEvent {

		@Override
		public String getName() {
			return "stargate_destroy";
		}

		@Override
		public String docs() {
			return "{deny: <boolean> Whether access was denied or not. }"
					+ " Fired when a block of a Stargate portal is broken."
					+ " {player: The player that broke the block. | portal: The Stargate portal's name."
					+ " | network: The Stargate network this portal belongs to.}"
					+ "{} "
					+ " {}";
		}

		@Override
		public boolean matches(Map<String, Mixed> prefilter, BindableEvent e) throws PrefilterNonMatchException {
			Prefilters.match(prefilter, "deny", ((CHStargateDestroyEvent) e).getDeny(), Prefilters.PrefilterType.BOOLEAN_MATCH);
			return true;
		}

		@Override
		public Map<String, Mixed> evaluate(BindableEvent event) throws EventException {
			CHStargateDestroyEvent e = (CHStargateDestroyEvent) event;
			Map<String, Mixed> map = new HashMap<>();
			map.put("player", new CString(e.getPlayer().getName(), Target.UNKNOWN));
			map.put("portal", new CString(e.getPortal().getName(), Target.UNKNOWN));
			map.put("network", new CString(e.getPortal().getNetwork(), Target.UNKNOWN));
			return map;
		}

		@Override
		public boolean modifyEvent(String key, Mixed value, BindableEvent event) {
			return false;
		}

		@Override
		public void cancel(BindableEvent e, boolean state) {
			((CHStargateDestroyEvent) e).setDeny(state);
		}
	}

	@api
	public static class stargate_access extends StargateEvent {

		@Override
		public String getName() {
			return "stargate_access";
		}

		@Override
		public String docs() {
			return "{<boolean> deny: Whether access was denied or not. } "
					+ "Fired when a player interacts with a Stargate. Result determines access."
					+ " {player: The player requesting access. | portal: The Stargate portal's name."
					+ " | network: The Stargate network this portal belongs to.}"
					+ "{} "
					+ "{} ";
		}

		@Override
		public boolean matches(Map<String, Mixed> prefilter, BindableEvent e) throws PrefilterNonMatchException {
			Prefilters.match(prefilter, "deny", ((CHStargateAccessEvent) e).getDeny(), Prefilters.PrefilterType.BOOLEAN_MATCH);
			return true;
		}

		@Override
		public Map<String, Mixed> evaluate(BindableEvent event) throws EventException {
			CHStargateAccessEvent e = (CHStargateAccessEvent) event;
			Map<String, Mixed> map = new HashMap<>();
			map.put("player", new CString(e.getPlayer().getName(), Target.UNKNOWN));
			map.put("portal", new CString(e.getPortal().getName(), Target.UNKNOWN));
			map.put("network", new CString(e.getPortal().getNetwork(), Target.UNKNOWN));
			return map;
		}

		@Override
		public boolean modifyEvent(String key, Mixed value, BindableEvent event) {
			return false;
		}

		@Override
		public void cancel(BindableEvent e, boolean state) {
			((CHStargateAccessEvent) e).setDeny(state);
		}
	}

	@api
	public static class stargate_open extends StargateEvent {

		@Override
		public String getName() {
			return "stargate_open";
		}

		@Override
		public String docs() {
			return "{} "
					+ "Fired when a Stargate portal is opened."
					+ " {player: The player opening the portal, if one. | portal: The Stargate portal's name."
					+ " | network: The Stargate network this portal belongs to.}"
					+ "{} "
					+ "{} ";
		}

		@Override
		public boolean matches(Map<String, Mixed> prefilter, BindableEvent event) throws PrefilterNonMatchException {
			return true;
		}

		@Override
		public Map<String, Mixed> evaluate(BindableEvent event) throws EventException {
			CHStargateOpenEvent e = (CHStargateOpenEvent) event;
			Map<String, Mixed> map = new HashMap<>();
			MCPlayer p = e.getPlayer();
			if(p != null) {
				map.put("player", new CString(e.getPlayer().getName(), Target.UNKNOWN));
			}
			map.put("portal", new CString(e.getPortal().getName(), Target.UNKNOWN));
			map.put("network", new CString(e.getPortal().getNetwork(), Target.UNKNOWN));
			return map;
		}

		@Override
		public boolean modifyEvent(String key, Mixed value, BindableEvent event) {
			return false;
		}

		@Override
		public MSVersion since() {
			return MSVersion.V3_3_2;
		}
	}

}
