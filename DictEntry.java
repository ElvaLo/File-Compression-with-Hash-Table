/**
 * This class represents a DictEntry that stores a string configuration and score
 * @author Jiaxin Lu
 *
 */
public class DictEntry {
	/* Attribute declarations */
    private String config;             //configuration
    private int score;                //associated score
    
    /**
     * Constructor initializes the DictEntry's configuration and score
     * @param config configuration 
     * @param score associated score
     */
    public DictEntry(String config, int score){
    	this.config = config;
    	this.score = score;
    }
    
    
    /**
     * getConfig method returns the configuration of an DictEntry object
     * @return configuration
     */
    public String getConfig(){
    	return config;
    }
    
    /**
     * getScore method returns the score of an DictEntry object
     * @return score
     */
    public int getScore(){
    	return score;
    }
}
