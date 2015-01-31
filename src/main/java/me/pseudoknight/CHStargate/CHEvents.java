package me.pseudoknight.CHStargate;

import com.laytonsmith.annotations.api;
import com.laytonsmith.core.CHVersion;
import com.laytonsmith.core.Static;
import com.laytonsmith.core.constructs.CArray;
import com.laytonsmith.core.constructs.CString;
import com.laytonsmith.core.constructs.Construct;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.events.AbstractEvent;
import com.laytonsmith.core.events.BindableEvent;
import com.laytonsmith.core.events.Driver;
import com.laytonsmith.core.events.Prefilters;
import com.laytonsmith.core.exceptions.EventException;
import com.laytonsmith.core.exceptions.PrefilterNonMatchException;
import me.pseudoknight.CHStargate.abstraction.CHStargateAccessEvent;
import me.pseudoknight.CHStargate.abstraction.CHStargateDestroyEvent;

import java.util.HashMap;
import java.util.Map;

public class CHEvents {

    protected static abstract class StargateEvent extends AbstractEvent {
        @Override
        public String getName() {
            return getClass().getSimpleName();
        }

        @Override
        public Driver driver() {
            return Driver.EXTENSION;
        }

        @Override
        public CHVersion since() {
            return CHVersion.V3_3_1;
        }

        @Override
        public BindableEvent convert(CArray manualObject, Target t) {
            return null;
        }
    }

    /*
	@api
	public static class stargate_create extends StargateEvent {
		@Override
		public String docs() {
			return "{} "
					+ "Fired when a Stargate is created."
					+ "{} "
					+ "{} "
					+ "{} ";
		}

		@Override
		public boolean matches(Map<String, Construct> prefilter, BindableEvent event)
				throws PrefilterNonMatchException {
			return true;
		}

		@Override
		public Map<String, Construct> evaluate(BindableEvent event) throws EventException {
			StargateCreateEvent e = (StargateCreateEvent) event;
			Map<String, Construct> map = new HashMap<>();
			map.put("player", new CString(e.getPlayer().getName(), Target.UNKNOWN));
			map.put("portal", new CString(e.getPortal().getName(), Target.UNKNOWN));
			map.put("network", new CString(e.getPortal().getNetwork(), Target.UNKNOWN));
			return map;
		}

		@Override
		public boolean modifyEvent(String key, Construct value, BindableEvent event) {
			return false;
		}
	}
*/
    @api
    public static class stargate_destroy extends StargateEvent {
        @Override
        public String docs() {
            return "{deny: <boolean> Whether access was denied or not. }"
                    + " Fired when a block of a Stargate portal is broken."
                    + " {player: The player that broke the block. | portal: The Stargate portal's name."
                    + " | network: The Stargate network this portal belongs to."
                    + " | owner: The name of the player that owns this portal. }"
                    + "{owner: Change the owner | deny: Deny and send message } "
                    + " {}";
        }

        @Override
        public boolean matches(Map<String, Construct> prefilter, BindableEvent e)
                throws PrefilterNonMatchException {
            Prefilters.match(prefilter, "deny", ((CHStargateDestroyEvent) e).getDeny(), Prefilters.PrefilterType.BOOLEAN_MATCH);
            return true;
        }

        @Override
        public Map<String, Construct> evaluate(BindableEvent event) throws EventException {
            CHStargateDestroyEvent e = (CHStargateDestroyEvent) event;
            Map<String, Construct> map = new HashMap<>();
            map.put("player", new CString(e.getPlayer().getName(), Target.UNKNOWN));
            map.put("portal", new CString(e.getPortal().getName(), Target.UNKNOWN));
            map.put("network", new CString(e.getPortal().getNetwork(), Target.UNKNOWN));
            map.put("owner", new CString(e.getPortal().getOwner(), Target.UNKNOWN));
            return map;
        }

        @Override
        public boolean modifyEvent(String key, Construct value, BindableEvent event) {
            CHStargateDestroyEvent e = (CHStargateDestroyEvent) event;
            if ("deny".equals(key)) {
                e.setDeny(Static.getBoolean(value));
                return true;
            }
            if ("owner".equals(key)) {
                e.getPortal().setOwner(value.val());
                return true;
            }
            return false;
        }
    }

    @api
    public static class stargate_access extends StargateEvent {
        @Override
        public String docs() {
            return "{<boolean> deny: Whether access was denied or not. } "
                    + "Fired when a player interacts with a Stargate. Result determines access."
                    + " {player: The player requesting access. | portal: The Stargate portal's name."
                    + " | network: The Stargate network this portal belongs to."
                    + " | owner: The name of the player that owns this portal. }"
                    + "{owner: Change the owner | deny: Deny and send message } "
                    + "{} ";
        }

        @Override
        public boolean matches(Map<String, Construct> prefilter, BindableEvent e)
                throws PrefilterNonMatchException {
            Prefilters.match(prefilter, "deny", ((CHStargateAccessEvent) e).getDeny(), Prefilters.PrefilterType.BOOLEAN_MATCH);
            return true;
        }

        @Override
        public Map<String, Construct> evaluate(BindableEvent event) throws EventException {
			CHStargateAccessEvent e = (CHStargateAccessEvent) event;
            Map<String, Construct> map = new HashMap<>();
            map.put("player", new CString(e.getPlayer().getName(), Target.UNKNOWN));
            map.put("portal", new CString(e.getPortal().getName(), Target.UNKNOWN));
            map.put("network", new CString(e.getPortal().getNetwork(), Target.UNKNOWN));
            map.put("owner", new CString(e.getPortal().getOwner(), Target.UNKNOWN));
            return map;
        }

        @Override
        public boolean modifyEvent(String key, Construct value, BindableEvent event) {
			CHStargateAccessEvent e = (CHStargateAccessEvent) event;
            if ("deny".equals(key)) {
                e.setDeny(Static.getBoolean(value));
                return true;
            }
            if ("owner".equals(key)) {
                e.getPortal().setOwner(value.val());
                return true;
            }
            return false;
        }
    }
/*
	@api
	public static class stargate_activate extends StargateEvent {
		@Override
		public String docs() {
			return "{} "
					+ "Fired when a Stargate is activated."
					+ "{} "
					+ "{} "
					+ "{} ";
		}

		@Override
		public boolean matches(Map<String, Construct> prefilter, BindableEvent event)
				throws PrefilterNonMatchException {
			return true;
		}

		@Override
		public Map<String, Construct> evaluate(BindableEvent event) throws EventException {
			StargateActivateEvent e = (StargateActivateEvent) event;
			Map<String, Construct> map = new HashMap<>();
			map.put("player", new CString(e.getPlayer().getName(), Target.UNKNOWN));
			map.put("portal", new CString(e.getPortal().getName(), Target.UNKNOWN));
			map.put("network", new CString(e.getPortal().getNetwork(), Target.UNKNOWN));
			return map;
		}

		@Override
		public boolean modifyEvent(String key, Construct value, BindableEvent event) {
			return false;
		}
	}

	@api
	public static class stargate_deactivate extends StargateEvent {
		@Override
		public String docs() {
			return "{} "
					+ "Fired when a Stargate is deactivated."
					+ "{} "
					+ "{} "
					+ "{} ";
		}

		@Override
		public boolean matches(Map<String, Construct> prefilter, BindableEvent event)
				throws PrefilterNonMatchException {
			return true;
		}

		@Override
		public Map<String, Construct> evaluate(BindableEvent event) throws EventException {
			StargateDeactivateEvent e = (StargateDeactivateEvent) event;
			Map<String, Construct> map = new HashMap<>();
			map.put("portal", new CString(e.getPortal().getName(), Target.UNKNOWN));
			map.put("network", new CString(e.getPortal().getNetwork(), Target.UNKNOWN));
			return map;
		}

		@Override
		public boolean modifyEvent(String key, Construct value, BindableEvent event) {
			return false;
		}
	}

	@api
	public static class stargate_open extends StargateEvent {
		@Override
		public String docs() {
			return "{} "
					+ "Fired when a Stargate portal is opened."
					+ "{} "
					+ "{} "
					+ "{} ";
		}

		@Override
		public boolean matches(Map<String, Construct> prefilter, BindableEvent event)
				throws PrefilterNonMatchException {
			return true;
		}

		@Override
		public Map<String, Construct> evaluate(BindableEvent event) throws EventException {
			StargateOpenEvent e = (StargateOpenEvent) event;
			Map<String, Construct> map = new HashMap<>();
			map.put("player", new CString(e.getPlayer().getName(), Target.UNKNOWN));
			map.put("portal", new CString(e.getPortal().getName(), Target.UNKNOWN));
			map.put("network", new CString(e.getPortal().getNetwork(), Target.UNKNOWN));
			return map;
		}

		@Override
		public boolean modifyEvent(String key, Construct value, BindableEvent event) {
			return false;
		}
	}

	@api
	public static class stargate_close extends StargateEvent {
		@Override
		public String docs() {
			return "{} "
					+ "Fired when a Stargate portal is closed."
					+ "{} "
					+ "{} "
					+ "{} ";
		}

		@Override
		public boolean matches(Map<String, Construct> prefilter, BindableEvent event)
				throws PrefilterNonMatchException {
			return true;
		}

		@Override
		public Map<String, Construct> evaluate(BindableEvent event) throws EventException {
			StargateCloseEvent e = (StargateCloseEvent) event;
			Map<String, Construct> map = new HashMap<>();
			map.put("portal", new CString(e.getPortal().getName(), Target.UNKNOWN));
			map.put("network", new CString(e.getPortal().getNetwork(), Target.UNKNOWN));
			return map;
		}

		@Override
		public boolean modifyEvent(String key, Construct value, BindableEvent event) {
			return false;
		}
	}

	@api
	public static class stargate_portal extends StargateEvent {
		@Override
		public String docs() {
			return "{} "
					+ "Fired when a player goes through a portal in a Stargate."
					+ "{} "
					+ "{} "
					+ "{} ";
		}

		@Override
		public boolean matches(Map<String, Construct> prefilter, BindableEvent event)
				throws PrefilterNonMatchException {
			return true;
		}

		@Override
		public Map<String, Construct> evaluate(BindableEvent event) throws EventException {
			StargatePortalEvent e = (StargatePortalEvent) event;
			Map<String, Construct> map = new HashMap<>();
			map.put("player", new CString(e.getPlayer().getName(), Target.UNKNOWN));
			map.put("portal", new CString(e.getPortal().getName(), Target.UNKNOWN));
			map.put("network", new CString(e.getPortal().getNetwork(), Target.UNKNOWN));
			return map;
		}

		@Override
		public boolean modifyEvent(String key, Construct value, BindableEvent event) {
			return false;
		}
	}
*/

}
