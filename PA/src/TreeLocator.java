
class TreeLocatorNode<T> {
	public Location key;
	public List<T> data = new LinkedList<T>();
	public TreeLocatorNode<T> c1 ,c2, c3, c4;
	public TreeLocatorNode(Location k, T val ) {
		key = k;
		data.insert(val);
		c1 = c2 = c3 = c4= null; 
	}
	

	 public TreeLocatorNode() {
		 key = null;
		 c1 = c2 = c3 = c4= null; 
	}


	public boolean removeAllOccurrences (T e)
	{ boolean found = false;
	if ( ! data.empty())
	{
		data.findFirst();
		while (! data.last())
		{
			if (data.retrieve().equals(e))
			{
				data.remove();
				found = true; 
			}
			else
			data.findNext();
		}
		if (data.retrieve().equals(e))
		{
			data.remove();
			found = true; 
		}

	}
	return found;
	}


}
public class TreeLocator<T> implements Locator<T> {
	private TreeLocatorNode<T> root ;
	public TreeLocator() {
		root =  null; 
	}


	@Override
	public int add(T e, Location loc) {

		int i = 0;
		TreeLocatorNode<T> p = root;
		TreeLocatorNode<T> q = root;
		TreeLocatorNode<T> node = new TreeLocatorNode<T>(loc,e);
		if(root == null) {
			root =  node ;
			return i ;
		}
		//Find if a location is already exist 
		while(p != null) {
			i++;
			q=p;
			if((p.key.x == loc.x) && (p.key.y == loc.y)) {
				p.data.insert(e);
				return i ;}
			else if (( loc.x  < p.key.x ) && ( loc.y <= p.key.y ))
				p = p.c1;
			else if (( loc.x  <= p.key.x ) && ( loc.y > p.key.y ))
				p = p.c2;
			else if (( loc.x  > p.key.x ) && ( loc.y >= p.key.y ))
				p = p.c3;
			else if (( loc.x  >= p.key.x ) && ( loc.y < p.key.y ))
				p = p.c4;

		}
		//location does not exist
		//insert a new location with the data

		if (( loc.x  < q.key.x ) && ( loc.y <= q.key.y )) {

			q.c1 =   node;


		}
		else if (( loc.x  <= q.key.x ) && ( loc.y > q.key.y )) {

			q.c2=   node;

		}
		else if (( loc.x  > q.key.x ) && ( loc.y >= q.key.y )) 
		{

			q.c3 = node;


		}
		else if (( loc.x  >= q.key.x ) && ( loc.y < q.key.y )) {

			q.c4 = node;


		}
		return i;


	} 





	@Override
	public Pair<List<T>, Integer> get(Location loc) {

		TreeLocatorNode<T> p = root;

		int i=0;
		if(root == null) {
			return new Pair<List<T>, Integer>(new LinkedList<T>() ,i); 
		}

		//Find if a location is already exist 
		while(p != null) {
			i++;
			if((p.key.x == loc.x) && (p.key.y == loc.y)) {
				return new Pair<List<T>, Integer>(p.data ,i);
			}

			else if (( loc.x  < p.key.x ) && ( loc.y <= p.key.y ))
				p = p.c1;
			else if (( loc.x  <= p.key.x ) && ( loc.y > p.key.y ))
				p = p.c2;
			else if (( loc.x  > p.key.x ) && ( loc.y >= p.key.y ))
				p = p.c3;
			else if (( loc.x  >= p.key.x ) && ( loc.y < p.key.y ))
				p = p.c4;

		}

		return new Pair<List<T>, Integer>(new LinkedList<T>() ,i); 
	}

	@Override
	public Pair<Boolean, Integer> remove(T e, Location loc) {
		int i = 0;
		boolean found =false;
		TreeLocatorNode<T> p = root;

		while(p != null) {
			i++;

			if((p.key.x == loc.x) && (p.key.y == loc.y)) {
				found= removeAllOccurrences(e,p);
				return new Pair<Boolean, Integer>(found,i);  
			}
			else if (( loc.x  < p.key.x ) && ( loc.y <= p.key.y ))
				p = p.c1;
			else if (( loc.x  <= p.key.x ) && ( loc.y > p.key.y ))
				p = p.c2;
			else if (( loc.x  > p.key.x ) && ( loc.y >= p.key.y ))
				p = p.c3;
			else if (( loc.x  >= p.key.x ) && ( loc.y < p.key.y ))
				p = p.c4;

		}

		return new Pair<Boolean, Integer>(found,i); 
	}

	@Override
	public List<Pair<Location, List<T>>> getAll() {
		List<Pair<Location, List<T>>> list = new LinkedList <Pair<Location, List<T>>>();
		recGetAll(root,list );
		return list ;
	}

	private void recGetAll(TreeLocatorNode<T> node ,  List<Pair<Location, List<T>>> list ){
		if(node == null)
			return;
		list.insert(new Pair<Location, List<T>>(node.key , node.data));

		recGetAll( node.c1, list) ;
		recGetAll( node.c2, list) ;
		recGetAll( node.c3, list) ;
		recGetAll( node.c4, list) ;

	}



	@Override
	
	public Pair<List<Pair<Location, List<T>>>, Integer> inRange(Location lowerLeft, Location upperRight) {
		
		  Pair<List<Pair<Location, List<T>>>, Integer>  pair =  new Pair<List<Pair<Location, List<T>>>, Integer>( new LinkedList<Pair<Location, List<T>>>(),0);
		
		  return new Pair<>(pair.first,recGetInRange( pair ,root,lowerLeft,upperRight));
	}
	private int recGetInRange(Pair<List<Pair<Location, List<T>>>, Integer> pair , TreeLocatorNode<T> node ,Location lL, Location uR) {
	
		
		 if (node == null) 
	        {
	            return 0;
	        }
	        else
	        {
	        	pair.second = 1;
	        	//in the range --> insert it
	            if ((lL.x <= node.key.x && lL.y<=node.key.y) && (uR.x>=node.key.x && uR.y>=node.key.y)) {
	            	pair.first.insert(new Pair<Location, List<T>>(node.key, node.data));
	            }
	            if (lL.x < node.key.x && lL.y<=node.key.y) {
	            	pair.second += recGetInRange( pair, node.c1, lL, uR);
	            }
	            if (lL.x<=node.key.x && uR.y>node.key.y)  {
	            	pair.second += recGetInRange( pair, node.c2, lL, uR);
	            }
	            if (uR.x>node.key.x && uR.y>=node.key.y)  {
	            	pair.second+= recGetInRange( pair, node.c3, lL, uR);
	            }
	            if (uR.x>=node.key.x  && lL.y<node.key.y) {
	            	pair.second += recGetInRange( pair, node.c4, lL, uR);
	            }
	            return pair.second;
	        }
}
	private boolean removeAllOccurrences (T e,TreeLocatorNode<T>  p)
	{ boolean found = false;
	if ( ! p.data.empty())
	{
		p.data.findFirst();
		while (! p.data.last())
		{
			if (p.data.retrieve().equals(e))
			{
				p.data.remove();
				found = true; 
			}
			else
			p.data.findNext();
		}
		if (p.data.retrieve().equals(e))
		{
			p.data.remove();
			found = true; 
		}

	}
	return found;
	}

	    	
	 
		
		
	
	
	   
	   

	 
    
    
    
    
    
    
    }


	 



	 
	 
	


