package me.pseudoknight.CHStargate.abstraction.Stargate;

import com.laytonsmith.commandhelper.CommandHelperPlugin;
import com.laytonsmith.core.events.EventUtils;
import com.laytonsmith.core.events.Driver;
import net.TheDgtl.Stargate.event.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

public class Listener implements org.bukkit.event.Listener {

	private static Listener sgl;

	public static void register() {
		if(sgl == null) {
			sgl = new Listener();
		}
		CommandHelperPlugin.self.registerEvent(sgl);
	}

	public static void unregister() {
		//StargateCreateEvent.getHandlerList().unregister(sgl);
		StargateDestroyEvent.getHandlerList().unregister(sgl);
		StargateAccessEvent.getHandlerList().unregister(sgl);
		//StargateActivateEvent.getHandlerList().unregister(sgl);
		//StargateDeactivateEvent.getHandlerList().unregister(sgl);
		//StargateOpenEvent.getHandlerList().unregister(sgl);
		//StargateCloseEvent.getHandlerList().unregister(sgl);
		//StargatePortalEvent.getHandlerList().unregister(sgl);
	}
/*
	@EventHandler(priority = EventPriority.LOWEST)
	public void onStargateCreate(StargateCreateEvent event) {
		EventUtils.TriggerListener(Driver.EXTENSION, "stargate_create", new StargateEvents.CHStargateCreateEvent(event));
	}
*/
	@EventHandler(priority = EventPriority.LOWEST)
	public void onStargateDestroy(StargateDestroyEvent event) {
		EventUtils.TriggerListener(Driver.EXTENSION, "stargate_destroy", new Events.BukkitStargateDestroyEvent(event));
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onStargateAccess(StargateAccessEvent event) {
		EventUtils.TriggerListener(Driver.EXTENSION, "stargate_access", new Events.BukkitStargateAccessEvent(event));
	}
/*
	@EventHandler(priority = EventPriority.LOWEST)
	public void onStargateActivate(StargateActivateEvent event) {
		EventUtils.TriggerListener(Driver.EXTENSION, "stargate_activate", new StargateEvents.CHStargateActivateEvent(event));
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onStargateDeactivate(StargateDeactivateEvent event) {
		EventUtils.TriggerListener(Driver.EXTENSION, "stargate_deactivate", new StargateEvents.CHStargateDeactivateEvent(event));
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onStargateOpen(StargateOpenEvent event) {
		EventUtils.TriggerListener(Driver.EXTENSION, "stargate_open", new StargateEvents.CHStargateDestroyEvent(event));
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onStargateClose(StargateCloseEvent event) {
		EventUtils.TriggerListener(Driver.EXTENSION, "stargate_close", new StargateEvents.CHStargateDestroyEvent(event));
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onStargatePortal(StargatePortalEvent event) {
		EventUtils.TriggerListener(Driver.EXTENSION, "stargate_portal", new StargateEvents.CHStargateDestroyEvent(event));
	}
*/

}
