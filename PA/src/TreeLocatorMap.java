
public class TreeLocatorMap<K extends Comparable<K>> implements LocatorMap<K> {
	private Map <K , Location > map ;
	private Locator <K > tL;
	public TreeLocatorMap()
	{
		map = new BST<K, Location> () ;
		tL = new TreeLocator <K>();
	}
	@Override
	public Map<K, Location> getMap() {
		return map;

	}

	@Override
	public Locator<K> getLocator() {
		return tL;
	}

	@Override
	public Pair<Boolean, Integer> add(K k, Location loc) {
		 
		Pair < Boolean , Integer>  pairFind = map.find(k);

		if (!pairFind.first) // does not exist
		{
			     map.insert(k, loc);
			     tL.add(k, loc);

				 return new Pair<>(true, pairFind.second);			} 

		return new Pair<>(false, pairFind.second);

	}



	@Override
	public Pair<Boolean, Integer> move(K k, Location loc) {
	 
        Pair < Boolean , Integer>  pairFind = map.find(k);
         
        if ( pairFind.first ) //  if k exists move it 
        {
        	tL.remove(k, map.retrieve());
                 map.update(loc);
                 tL.add(k, loc);  
                 return new Pair<>(true, pairFind.second);	 
            
             
        }
        return new Pair<>(false, pairFind.second);
        
	}

	@Override
	public Pair<Location, Integer> getLoc(K k) {
		
	        Pair < Boolean , Integer>  pairFind = map.find(k);
	           
	        if ( pairFind.first )  
	        	return new Pair < Location , Integer >(map.retrieve(), pairFind.second);
	        else
	        	return new Pair < Location , Integer >(null, pairFind.second);         
	        
	}

	@Override
	public Pair<Boolean, Integer> remove(K k) {
		 
        
        Pair < Boolean , Integer>  pairFind= map.find(k);
            
        if ( pairFind.first  )  
        {
            
        	tL.remove(k, map.retrieve());
           
                map.remove(k);
               
                return new Pair<>(true, pairFind.second);	
                            
        }
        
        return new Pair<>(false, pairFind.second);
    }
 

	@Override
	public List<K> getAll() {
		return map.getAll();
	}

	@Override
	public Pair<List<K>, Integer> getInRange(Location lowerLeft, Location upperRight) {
		Pair<List<Pair<Location, List<K>>>, Integer> pair   = tL.inRange(lowerLeft, upperRight);
		List<Pair<Location, List<K>>> listOfAllCars = pair.first;
		List<K> list;
		List<K> allDataInLoc = new  LinkedList<K> ();
		
		if(!listOfAllCars.empty()) {
			listOfAllCars.findFirst();
		 
		while(!listOfAllCars.last()) {
			list = listOfAllCars.retrieve().second; 	
		 if(!list.empty()) {
			 list.findFirst();
		
			while(!list.last()) {
				allDataInLoc.insert(list.retrieve());
				list.findNext();
		}
			allDataInLoc.insert(list.retrieve());
	 
	}
		 listOfAllCars.findNext();}
		//last element in listOfAllCars
		list = listOfAllCars.retrieve().second; 	
		 if(!list.empty()) {
			 list.findFirst();
		
			while(!list.last()) {
				allDataInLoc.insert(list.retrieve());
				list.findNext();
		}
			allDataInLoc.insert(list.retrieve());}}
		 
		return new Pair<List<K>, Integer>(allDataInLoc,pair.second);
}
	 
	


}
