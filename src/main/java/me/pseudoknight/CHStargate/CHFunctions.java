package me.pseudoknight.CHStargate;

import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.CHVersion;
import com.laytonsmith.core.constructs.CBoolean;
import com.laytonsmith.core.constructs.CNull;
import com.laytonsmith.core.constructs.Construct;
import com.laytonsmith.core.constructs.CString;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.functions.AbstractFunction;
import com.laytonsmith.core.functions.Exceptions.ExceptionType;
import net.TheDgtl.Stargate.Portal;

public class CHFunctions {
	public static String docs() {
		return "These functions allow you to interface with Stargate portals.";
	}

	protected static abstract class StargateFunction extends AbstractFunction {
		@Override
		public String getName() {
			return getClass().getSimpleName();
		}

		@Override
		public boolean isRestricted() {
			return true;
		}

		@Override
		public Boolean runAsync() {
			return false;
		}

		@Override
		public Version since() {
			return CHVersion.V3_3_1;
		}
	}

	@api
	public static class sg_get_owner extends StargateFunction {
		@Override
		public String docs() {
			return "string {portalName, portalNetwork} Returns the portal's owner.";
		}

		@Override
		public Integer[] numArgs() {
			return new Integer[]{2};
		}

		@Override
		public Construct exec(Target t, Environment environment, Construct... args) throws ConfigRuntimeException {
			Portal portal = Portal.getByName(args[0].val(), args[1].val());
			String owner = "";
			if (portal != null) {
				owner = portal.getOwner();
			}
			if(owner.equals("")) {
				return CNull.NULL;
			}
			return new CString(owner, t);
		}

		@Override
		public ExceptionType[] thrown() {
			return new ExceptionType[]{ExceptionType.CastException};
		}
	}

	@api
	public static class sg_set_owner extends StargateFunction {
		@Override
		public String docs() {
			return "boolean {portalName, portalNetwork, owner} Sets the portal's owner.";
		}

		@Override
		public Integer[] numArgs() {
			return new Integer[]{3};
		}

		@Override
		public Construct exec(Target t, Environment environment, Construct... args) throws ConfigRuntimeException {
			Portal portal = Portal.getByName(args[0].val(), args[1].val());
			String owner = args[2].val();
			if (portal == null) {
				return CBoolean.FALSE;
			}
			portal.setOwner(owner);
			return CBoolean.TRUE;
		}

		@Override
		public ExceptionType[] thrown() {
			return new ExceptionType[]{ExceptionType.CastException};
		}
	}

}
