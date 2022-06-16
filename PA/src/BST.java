
class BSTNode<K,T>{
	public K key;
	public T data;
	public BSTNode<K,T> left ,right;
	public BSTNode() {
		key = null;
		data = null;
		left = right =null;
		
	}

	public BSTNode(K k ,T val) {
		key = k;
		data = val;
		left = right =null;

	}
	public BSTNode(K k ,T val,BSTNode<K,T> l,BSTNode<K,T>r) {
		key = k;
		data = val;
		left = l;
		right= r;


	}


}
public class BST<K extends Comparable<K>, T> implements Map<K, T> {
	private BSTNode<K,T> root , current;

	public BST() {
		root = current = null;
	}
	@Override
	public boolean empty() {
		return root == null;
	}

	@Override
	public boolean full() {
		return false;
	}

	@Override
	public T retrieve() { 
		return current.data;
	}

	@Override
	public void update(T e) {
		current.data = e;
	}

	@Override
	public Pair<Boolean, Integer> find(K key) {
		BSTNode<K,T> p = root;
		int i = 0;
		if(empty())
			return new Pair<Boolean, Integer>(false,i);
		while(p!=null) {
			i++;

			if(p.key.compareTo(key) == 0) {
				current = p;
				return new Pair<Boolean, Integer>(true,i);
			}
			else if(p.key.compareTo(key)>0)
				p = p.left;
			else
				p = p.right;
		}

		return new Pair<Boolean, Integer>(false,i);
	}

	@Override
	public Pair<Boolean, Integer> insert(K key, T data) { 
		BSTNode<K,T> p = root;
		BSTNode<K,T> q = root;
		BSTNode<K,T> node = new BSTNode<K,T>(key ,data);
		int i = 0; ;
		if(empty()) {
			root = current = node;
			return  new Pair<Boolean, Integer>(true,i);
		}
		while(p!= null) {
			i++;
			q= p;
			if(p.key.compareTo(key) == 0)   
				return new Pair<Boolean, Integer>(false,i);
			 
			else if(p.key.compareTo(key)>0)
				p = p.left;
			else
				p = p.right;
		}
		if(q.key.compareTo(key)>0) {
			q.left = node ;
			current = q.left;
			
		}
		else {
			q.right = node ;
			current = q.right;
		}
		return new Pair<Boolean, Integer>(true,i);
		 
		
	}

		

	@Override
	public Pair<Boolean, Integer> remove(K key) {

		Pair<Boolean, Integer> pair = find(key);
		if(pair .first) {
			K k1 = key;
			BSTNode<K,T> p= root;
			BSTNode<K,T> q = null;   

			while(p != null) {

				if(k1.compareTo(p.key)<0) {
					q = p;
					p = p.left;
				}

				else if(k1.compareTo(p.key)>0) {
					q = p;
					p = p.right;
				}
				else {
					if((p.left != null ) && (p.right != null )) {
						BSTNode<K,T>  min = p.right;
						q = p;
						while(min.left != null) {
							q =min;
							min = min.left;
						}
						p.key = min.key;
						p.data = min.data;
						k1 = min.key;
						p = min;

					}
					if(p.left != null) {
						p =p.left;
					}
					else {
						p = p.right;
					}
					if(q== null) {
						root = p;
					}
					else {
						if(k1.compareTo(q.key)<0)
							q.left = p;
						else
							q.right = p;

					}



				}

			}
			current = root;
		}


		return pair;
	}

	@Override
	public List<K> getAll() {
		List<K> listOfKeys = new LinkedList<K>();
		if(root!= null) {
		recGetAll(listOfKeys, root);
		}
		return listOfKeys;
	}
	private void recGetAll(List<K> listOfKeys,BSTNode<K,T> p) {
		if(p== null)
			return ;
		recGetAll(listOfKeys,p.left);

		listOfKeys.insert(p.key);

		recGetAll(listOfKeys,p.right);





	}
	 

	 

}
