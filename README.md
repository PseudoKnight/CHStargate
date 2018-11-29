# CHStargate

Adds functions and events for the Stargate plugin. This should only be used if you are already using the [Stargate](https://github.com/PseudoKnight/Stargate-Bukkit) plugin.

### Functions

* boolean **sg_set_owner(portalName, portalNetwork, owner)** Sets the portal's owner name.
* string **sg_get_owner(portalName, portalNetwork)** Returns the portal's owner name.

### Events
#### stargate_access

Fired when a player interacts with a Stargate. Result determines access.

* prefilters: deny (boolean - Whether access was denied or not.)
* data: player, portal, network, owner (string)

#### stargate_destroy

Fired when a block of a Stargate portal is broken.

* prefilters: deny (boolean - Whether access was denied or not.)
* data: player, portal, network, owner (string)

#### stargate_open

Fired when a Stargate portal is opened.

* data: player, portal, network, owner (string)