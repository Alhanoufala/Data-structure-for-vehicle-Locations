public class VehicleHiringManager {
	LocatorMap<String> treeLocatorMap;
	public VehicleHiringManager() {
		treeLocatorMap = new TreeLocatorMap<String>();
	}

	// Returns the locator map.
	public LocatorMap<String> getLocatorMap() {
		return treeLocatorMap;
	}

	// Sets the locator map.
	public void setLocatorMap(LocatorMap<String> locatorMap) {
		treeLocatorMap = locatorMap;
	}
		
	

	// Inserts the vehicle id at location loc if it does not exist and returns true.
	// If id already exists, the method returns false.
	public boolean addVehicle(String id, Location loc) {
		return  treeLocatorMap.add(id, loc).first;
	}

	// Moves the vehicle id to location loc if id exists and returns true. If id not
	// exist, the method returns false.
	public boolean moveVehicle(String id, Location loc) {
    
		
		return treeLocatorMap.move(id, loc).first;
	}

	// Removes the vehicle id if it exists and returns true. If id does not exist,
	// the method returns false.
	public boolean removeVehicle(String id) {
 
		
		return treeLocatorMap.remove(id).first;
	}

	// Returns the location of vehicle id if it exists, null otherwise.
	public Location getVehicleLoc(String id) {
		
        return treeLocatorMap.getLoc(id).first;
    }


	// Returns all vehicles located within a square of side 2*r centered at loc
	// (inclusive of the boundaries).
	public List<String> getVehiclesInRange(Location loc, int r) {
		Pair < List <String >, Integer > allVehiclesInRange = treeLocatorMap.getInRange(new Location ( loc.x - r , loc.y - r), new Location ( loc.x + r , loc.y + r));
		return allVehiclesInRange.first;
	}
}
