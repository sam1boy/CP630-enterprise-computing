package ec.lab.stats;

import org.apache.commons.math3.stat.Frequency;

public class FrequencyExample {
	
	public static void main(String[] args) {
		
		System.out.println("Mixing integers, longs, Integers and Longs:"); 

		Frequency f = new Frequency();
		 f.addValue(1);
		 f.addValue(new Integer(1));
		 f.addValue(new Long(1));
		 f.addValue(2);
		 f.addValue(new Integer(-1));
		 System.out.println(f.getCount(1));   // displays 3
		 System.out.println(f.getCumPct(0));  // displays 0.2
		 System.out.println(f.getPct(new Integer(1)));  // displays 0.6
		 System.out.println(f.getCumPct(-2));   // displays 0
		 System.out.println(f.getCumPct(10));  // displays 1
		
		 
		 System.out.println("Using case-sensitive comparison, alpha sort order (natural comparator):");
		 Frequency f1 = new Frequency();
		 f1.addValue("one");
		 f1.addValue("One");
		 f1.addValue("oNe");
		 f1.addValue("Z");
		 System.out.println(f1.getCount("one")); // displays 1
		 System.out.println(f1.getCumPct("Z"));  // displays 0.5
		 System.out.println(f1.getCumPct("Ot")); // displays 0.25
		 
		 System.out.println("Using case-sensitive comparison, alpha sort order (natural comparator):"); 
		 Frequency f2 = new Frequency(String.CASE_INSENSITIVE_ORDER);
		 f2.addValue("one");
		 f2.addValue("One");
		 f2.addValue("oNe");
		 f2.addValue("Z");
		 System.out.println(f2.getCount("one"));  // displays 3
		 System.out.println(f2.getCumPct("z"));   // displays 1
				
	}

}
