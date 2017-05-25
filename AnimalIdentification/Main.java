package white;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		List<String> strs=new ArrayList<String>();
		String s1="有毛发 哺乳动物";
		String s2="有奶 哺乳动物";
		String s3="有羽毛 鸟";
		String s4="会飞 产蛋 鸟";
		String s5="吃肉 食肉动物";
		String s6="有犬齿 有爪 眼盯前方 食肉动物";
		String s7="哺乳动物 有蹄 有蹄类动物";
		String s8="哺乳动物 反刍 有蹄类动物";
		String s9="哺乳动物 食肉动物 黄褐色 身上有暗斑点 金钱豹";
		String s10="哺乳动物 食肉动物 黄褐色 身上有黑色条纹 虎";
		String s11="有蹄类动物 有长脖子 有长腿 身上有暗斑点 长颈鹿";
		String s12="有蹄类动物 身上有黑色条纹 斑马";
		String s13="鸟 不会飞 有长脖子 有长腿 有黑白两色 鸵鸟";
		String s14="鸟 会游泳 不会飞 有黑白两色 企鹅";
		String s15="鸟 善飞 信天翁";
		strs.add(s1);
		strs.add(s2);
		strs.add(s3);
		strs.add(s4);
		strs.add(s5);
		strs.add(s6);
		strs.add(s7);
		strs.add(s8);
		strs.add(s9);
		strs.add(s10);
		strs.add(s11);
		strs.add(s12);
		strs.add(s13);
		strs.add(s14);
		strs.add(s15);
		RuleSet ruleSet=new RuleSet();
		ruleSet.init(strs);
		Set<String> set=new HashSet<String>();
		Scanner input=new Scanner(System.in);
		String pre=input.nextLine();
		String[] p=pre.split(" ");
		for(int i=0;i<p.length;i++){
			set.add(p[i]);
		}
		ruleSet.match(set);
	}

}
