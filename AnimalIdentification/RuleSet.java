package white;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RuleSet {

	public List<Rule> rules=new ArrayList<Rule>();
	
	public void init(List<String> strs){
		for(int i=0;i<strs.size();i++){
			String[] s=strs.get(i).split(" ");
			Set<String> set=new HashSet<String>();
			for(int j=0;j<s.length-1;j++){
				set.add(s[j]);
			}
			rules.add(new Rule(set,s[s.length-1]));
		}
	}
	
	public void match(Set<String> pre){
		for(int i=0;i<rules.size();i++){
			Rule rule=rules.get(i);
			if(pre.containsAll(rule.premise)){
				pre.removeAll(rule.premise);
				pre.add(rule.conclusion);
				System.out.println(rule.conclusion);
				i=0;
			}
		}
	}
	
	
	
}
