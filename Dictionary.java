import java.util.LinkedList;

/**
 * This class represents a dictionary holding DictEntries
 * @author Jiaxin Lu
 *
 */
public class Dictionary implements DictionaryADT{
	/* Attribute declarations */
    private int numElements;                      //number of entries in dictionary
    private LinkedList<DictEntry>[] hashTable;    //a hash table with linked list that stores the configurations and scores
    
    /**
     * Constructor creates Dictionary
     * @param size size of dictionary
     */
    public Dictionary(int size){
    	numElements = 0;
    	hashTable = (LinkedList<DictEntry>[]) new LinkedList[size];  //creates an array of linked list 
    }
    
    /**
     * hash function that compute a value of an index into the array hashTable
     * @param key configuration as the key
     * @return a value as an index of the array hashTable
     */
    private int hash(String key){
		int charSum = (int)key.charAt(0); 
		for (int i=1; i<key.length(); i++)
		{
			charSum += ((int)key.charAt(i) * (41^i));    //hash code
		}
		return (charSum % hashTable.length);	
	}
    
    /**
     * insert method inserts the given DictEntry pair into the dictionary
     * @param pair pair stores configuration and score
     * @return 0 if certain list was empty before insertion; otherwise 1
     */
    public int insert(DictEntry pair) throws DictionaryException{
    	int index = hash(pair.getConfig());      //use the hash function to find the location of the array
    	if (hashTable[index] == null){
    		LinkedList<DictEntry> list = new LinkedList<DictEntry>();   //creates a new linked list to hold the entry
    		list.addFirst(pair);      //add the entry to the first position of the list since list was empty
    		hashTable[index] = list;        //add the new list to the hash table 
    		numElements = numElements + 1;     //increment the number of entries
    		return 0;
    	}
    	
    	else {                     //there exists a collision
    		int i = 0;
    		while (i<hashTable[index].size()){
    			if ((hashTable[index].get(i).getConfig()).compareTo(pair.getConfig())==0){   //check if the entry already exists
    				throw new DictionaryException ("Configuration is in the hashTable");  //throw an exception if entry already exists
    			}
    			i=i+1;
    		}
    		
    		hashTable[index].addLast(pair);   //add the entry to the end of the list if it was not in hashTable before insertion
    		numElements = numElements + 1;    //increment the number of entries
    		return 1;
    	}
    }
    
    	/**
    	 * remove method removes the entry with key config from dictionary
    	 * @param config configuration as a key
    	 */
    	public void remove (String config) throws DictionaryException{
    		int index = hash (config);    //use the hash function to find the location of the list that contains the entry with key config
    		if (hashTable[index]==null){      //check if the entry with key config is not in dictionary
    			throw new DictionaryException ("Configuration is not in dictionary");  //throw an exception if it is not in dictionary
    		}
    		
    		for (int i=1; i<=hashTable[index].size(); i++){        
    			if (config.compareTo(hashTable[index].get(i).getConfig()) == 0){    
    				hashTable[index].remove(i);         //remove the entry from linked list in hashTable[index]
    				numElements = numElements - 1;     //decrement the number of entries
    				return;
    			}
    		}
    		
    		throw new DictionaryException ("Configuration is not in dictionary"); //throw an exception if the key was not found
    	}
    	
    	/**
    	 * find method finds the entry with key config and then returns its corresponding score
    	 * @param config configuration as a key
    	 * @return score if configuration was found; otherwise -1
    	 */
    	public int find (String config){
    		int index = hash(config);  //use the hash function to find the location of the list that contains the entry with key config
    		if (hashTable[index]==null){   //check if the given configuration is in the dictionary
    			return -1;                 //given configuration not in dictionary
    		}
    			
    		for (int i=0; i<hashTable[index].size(); i++){
    			if (config.compareTo(hashTable[index].get(i).getConfig()) == 0){    //given configuration found
    				return hashTable[index].get(i).getScore();
    			}
    		}
    		return -1;      //if given configuration was not found, return -1
    	}
    	
    	/**
    	 * numElements method returns the number of entries in the dictionary
    	 * @return number of entries in the dictionary
    	 */
    	public int numElements(){
    		return numElements;
    	}
    
}
