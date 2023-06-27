package me.pseudoknight.CHStargate;

import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.abstraction.bukkit.BukkitMCLocation;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.MSVersion;
import com.laytonsmith.core.ObjectGenerator;
import com.laytonsmith.core.Static;
import com.laytonsmith.core.constructs.CArray;
import com.laytonsmith.core.constructs.CBoolean;
import com.laytonsmith.core.constructs.CNull;
import com.laytonsmith.core.constructs.CString;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.exceptions.CRE.CREFormatException;
import com.laytonsmith.core.exceptions.CRE.CRENotFoundException;
import com.laytonsmith.core.exceptions.CRE.CREThrowable;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.functions.AbstractFunction;
import com.laytonsmith.core.natives.interfaces.Mixed;
import net.TheDgtl.Stargate.Blox;
import net.TheDgtl.Stargate.Portal;

import java.util.UUID;

public class CHFunctions {
	public static String docs() {
		return "These functions allow you to interface with Stargate portals.";
	}

	protected static abstract class StargateFunction extends AbstractFunction {
		@Override
		public boolean isRestricted() {
			return true;
		}

		@Override
		public Boolean runAsync() {
			return false;
		}

		@Override
		public Class<? extends CREThrowable>[] thrown() {
			return new Class[]{CRENotFoundException.class};
		}
	}

	@api
	public static class sg_get_owner_name extends StargateFunction {
		@Override
		public String getName() {
			return "sg_get_owner_name";
		}

		@Override
		public String docs() {
			return "string {portalName, portalNetwork} Returns the portal's owner name."
					+ " Will return null if the portal does not have an owner."
					+ " Throws NotFoundException if a portal was not found by that name on the specified network.";
		}

		@Override
		public Integer[] numArgs() {
			return new Integer[]{2};
		}

		@Override
		public Mixed exec(Target t, Environment environment, Mixed... args) throws ConfigRuntimeException {
			Portal portal = Portal.getByName(args[0].val(), args[1].val());
			String owner = "";
			if (portal != null) {
				owner = portal.getOwnerName();
			} else {
				throw new CRENotFoundException("Portal was not found.", t);
			}
			if(owner.equals("")) {
				return CNull.NULL;
			}
			return new CString(owner, t);
		}

		@Override
		public Version since() {
			return MSVersion.V3_3_4;
		}
	}

	@api
	public static class sg_get_owner_uuid extends StargateFunction {
		@Override
		public String getName() {
			return "sg_get_owner_uuid";
		}

		@Override
		public String docs() {
			return "string {portalName, portalNetwork} Returns the portal's owner UUID."
					+ " Will return null if there's no owner UUID for this portal."
					+ " being an unowned portal or a legacy portal stored with a player name."
					+ " Throws NotFoundException if a portal was not found by that name on the specified network.";
		}

		@Override
		public Integer[] numArgs() {
			return new Integer[]{2};
		}

		@Override
		public Mixed exec(Target t, Environment environment, Mixed... args) throws ConfigRuntimeException {
			Portal portal = Portal.getByName(args[0].val(), args[1].val());
			UUID owner = null;
			if (portal != null) {
				owner = portal.getOwnerUUID();
			} else {
				throw new CRENotFoundException("Portal was not found.", t);
			}
			if(owner == null) {
				return CNull.NULL;
			}
			return new CString(owner.toString(), t);
		}

		@Override
		public Version since() {
			return MSVersion.V3_3_4;
		}
	}

	@api
	public static class sg_set_owner extends StargateFunction {
		@Override
		public String getName() {
			return "sg_set_owner";
		}

		@Override
		public String docs() {
			return "boolean {portalName, portalNetwork, owner} Sets the portal's owner name."
					+ " Accepts UUID or player name, though the former is preferred."
					+ " Throws NotFoundException if a portal was not found by that name on the specified network.";
		}

		@Override
		public Integer[] numArgs() {
			return new Integer[]{3};
		}

		@Override
		public Mixed exec(Target t, Environment environment, Mixed... args) throws ConfigRuntimeException {
			Portal portal = Portal.getByName(args[0].val(), args[1].val());
			String owner = args[2].val();
			if (portal == null) {
				throw new CRENotFoundException("Portal was not found.", t);
			}
			if(owner.length() > 16) {
				try {
					portal.setOwner(UUID.fromString(owner));
				} catch(IllegalArgumentException ex) {
					throw new CREFormatException("Invalid UUID format.", t);
				}
			} else {
				portal.setOwner(Static.getServer().getOfflinePlayer(owner).getUniqueID());
			}
			return CBoolean.TRUE;
		}

		@Override
		public Version since() {
			return MSVersion.V3_3_1;
		}

		@Override
		public Class<? extends CREThrowable>[] thrown() {
			return new Class[]{CRENotFoundException.class, CREFormatException.class};
		}
	}

	@api
	public static class sg_is_portal_open extends StargateFunction {
		@Override
		public String getName() {
			return "sg_is_portal_open";
		}

		@Override
		public String docs() {
			return "boolean {portalName, portalNetwork} Returns whether the given portal is currently open.";
		}

		@Override
		public Integer[] numArgs() {
			return new Integer[]{2};
		}

		@Override
		public Mixed exec(Target t, Environment environment, Mixed... args) throws ConfigRuntimeException {
			Portal portal = Portal.getByName(args[0].val(), args[1].val());
			if (portal == null) {
				throw new CRENotFoundException("Portal was not found.", t);
			}
			return CBoolean.get(portal.isOpen());
		}

		@Override
		public Version since() {
			return MSVersion.V3_3_4;
		}
	}

	@api
	public static class sg_get_portal_info extends StargateFunction {
		@Override
		public String getName() {
			return "sg_get_portal_info";
		}

		@Override
		public String docs() {
			return "array {portalName, portalNetwork} Gets an array of data about a specific Stargate portal."
					+ " Throws NotFoundException if a portal was not found by that name on the specified network."
					+ " Array keys are: button, sign, destination, frame, entrance, options, open, ownerUUID, and ownerName."
					+ " Options array keys are: alwaysOn, backwards, free, hidden, noNetwork, private, random, and show.";
		}

		@Override
		public Integer[] numArgs() {
			return new Integer[]{2};
		}

		@Override
		public Mixed exec(Target t, Environment environment, Mixed... args) throws ConfigRuntimeException {
			Portal portal = Portal.getByName(args[0].val(), args[1].val());
			if (portal == null) {
				throw new CRENotFoundException("Portal was not found.", t);
			}

			CArray arr = CArray.GetAssociativeArray(t);
			Blox button = portal.getButton();
			if(button == null) {
				arr.set("button", CNull.NULL, t);
			} else {
				arr.set("button", ObjectGenerator.GetGenerator().location(new BukkitMCLocation(button.getLocation()), false), t);
			}
			arr.set("sign", ObjectGenerator.GetGenerator().location(new BukkitMCLocation(portal.getSign().getLocation()), false), t);

			Portal destination = portal.getDestination();
			if(destination == null) {
				arr.set("destination", CNull.NULL, t);
			} else {
				arr.set("destination", new CString(portal.getDestination().getName(), t), t);
			}

			CArray frame = new CArray(t);
			for(Blox b : portal.getFrame()) {
				frame.push(ObjectGenerator.GetGenerator().location(new BukkitMCLocation(new BukkitMCLocation(b.getLocation())), false), t);
			}
			arr.set("frame", frame, t);

			CArray entrance = new CArray(t);
			for(Blox b : portal.getEntrances()) {
				entrance.push(ObjectGenerator.GetGenerator().location(new BukkitMCLocation(new BukkitMCLocation(b.getLocation())), false), t);
			}
			arr.set("entrance", entrance, t);

			CArray options = CArray.GetAssociativeArray(t);
			options.set("alwaysOn", CBoolean.get(portal.isAlwaysOn()), t);
			options.set("backwards", CBoolean.get(portal.isBackwards()), t);
			options.set("free", CBoolean.get(portal.isFree()), t);
			options.set("hidden", CBoolean.get(portal.isHidden()), t);
			options.set("noNetwork", CBoolean.get(portal.isNoNetwork()), t);
			options.set("private", CBoolean.get(portal.isPrivate()), t);
			options.set("random", CBoolean.get(portal.isRandom()), t);
			options.set("show", CBoolean.get(portal.isShown()), t);
			arr.set("options", options, t);

			arr.set("open", CBoolean.get(portal.isOpen()), t);

			UUID ownerUUID = portal.getOwnerUUID();
			if(ownerUUID == null) {
				arr.set("ownerUUID", CNull.NULL, t);
			} else {
				arr.set("ownerUUID", ownerUUID.toString(), t);
			}
			String ownerName = portal.getOwnerName();
			if(ownerName.equals("")) {
				arr.set("ownerName", CNull.NULL, t);
			} else {
				arr.set("ownerName", ownerName, t);
			}
			return arr;
		}

		@Override
		public Version since() {
			return MSVersion.V3_3_4;
		}
	}

}
