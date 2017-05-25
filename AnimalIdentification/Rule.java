package white;

import java.util.HashSet;
import java.util.Set;

public class Rule {

	public Set<String> premise=new HashSet<String>();
	public String conclusion;
	
	public Rule(){
		
	}
	
	public Rule(Set<String> premise,String conclusion){
		this.premise=premise;
		this.conclusion=conclusion;
	}
}
