package white;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {

	public static void main(String[] args) {
		List<String> strs=new ArrayList<String>();
		String s1="��ë�� ���鶯��";
		String s2="���� ���鶯��";
		String s3="����ë ��";
		String s4="��� ���� ��";
		String s5="���� ʳ�⶯��";
		String s6="��Ȯ�� ��צ �۶�ǰ�� ʳ�⶯��";
		String s7="���鶯�� ���� �����ද��";
		String s8="���鶯�� ��ۻ �����ද��";
		String s9="���鶯�� ʳ�⶯�� �ƺ�ɫ �����а��ߵ� ��Ǯ��";
		String s10="���鶯�� ʳ�⶯�� �ƺ�ɫ �����к�ɫ���� ��";
		String s11="�����ද�� �г����� �г��� �����а��ߵ� ����¹";
		String s12="�����ද�� �����к�ɫ���� ����";
		String s13="�� ����� �г����� �г��� �кڰ���ɫ ����";
		String s14="�� ����Ӿ ����� �кڰ���ɫ ���";
		String s15="�� �Ʒ� ������";
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
