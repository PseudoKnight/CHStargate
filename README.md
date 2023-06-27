# CHStargate

Adds functions and events for the Stargate plugin. This should only be used if you are already using the [Stargate](https://github.com/PseudoKnight/Stargate-Bukkit) plugin.

## Functions

boolean **sg_set_owner(portalName, portalNetwork, owner)**  
Sets the portal's owner.

string **sg_get_owner_name(portalName, portalNetwork)**  
Returns the portal's owner name.

string **sg_get_owner_uuid(portalName, portalNetwork)**  
Returns the portal's owner UUID.

boolean **sg_is_portal_open(portalName, portalNetwork)**  
Returns whether the given portal is currently open.

array **sg_get_portal_info(portalName, portalNetwork)**  
Gets an array of data about a specific Stargate portal.  
Array keys are: `button`, `sign`, `destination`, `frame`, `entrance`, `options`, `open`, `ownerUUID`, `ownerName`.  
Options array keys are: `alwaysOn`, `backwards`, `free`, `hidden`, `noNetwork`, `private`, `random`, `show`.

## Events
### stargate_access

Fired when a player interacts with a Stargate. Result determines access.

#### Prefilters:
* `deny` (boolean) Whether access was denied or not.

#### Event Data:
* `player` The player requesting access.
* `portal` The Stargate portal's name.
* `network` The Stargate network this portal belongs to.

### stargate_destroy

Fired when a block of a Stargate portal is broken.

#### Prefilters:
* `deny` (boolean) Whether access was denied or not.

#### Event Data:
* `player` The player that broke the block.
* `portal` The Stargate portal's name.
* `network` The Stargate network this portal belongs to.

### stargate_open

Fired when a Stargate portal is opened.

#### Event Data:
* `player` The player opening the portal, if one.
* `portal` The Stargate portal's name.
* `network` The Stargate network this portal belongs to.

### stargate_portal

Fired when a Stargate portal is traversed.

#### Event Data:
* `player` The player traveling.
* `portal` The Stargate portal's name.
* `network` The Stargate network this portal belongs to.
* `exit` The exit location.
* `exitportal` The name of portal at the exit.

#### Mutable Fields:
* `exit` A different exit location.