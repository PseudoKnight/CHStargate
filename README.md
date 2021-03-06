# CHStargate

Adds functions and events for the Stargate plugin. This should only be used if you are already using the [Stargate](https://github.com/PseudoKnight/Stargate-Bukkit) plugin.

### Functions

* boolean **sg_set_owner(portalName, portalNetwork, owner)** Sets the portal's owner.
* string **sg_get_owner_name(portalName, portalNetwork)** Returns the portal's owner name.
* string **sg_get_owner_uuid(portalName, portalNetwork)** Returns the portal's owner UUID.
* array **sg_get_portal_info(portalName, portalNetwork)** Gets an array of data about a specific Stargate portal.

### Events
#### stargate_access

Fired when a player interacts with a Stargate. Result determines access.

* prefilters: deny (boolean - Whether access was denied or not.)
* data: player, portal, network

#### stargate_destroy

Fired when a block of a Stargate portal is broken.

* prefilters: deny (boolean - Whether access was denied or not.)
* data: player, portal, network

#### stargate_open

Fired when a Stargate portal is opened.

* data: player, portal, network