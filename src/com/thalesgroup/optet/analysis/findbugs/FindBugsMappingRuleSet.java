package com.thalesgroup.optet.analysis.findbugs;

import java.util.HashMap;
import java.util.Map;

/**
 * FindBugsMappingRulesSet realize the ruleset mapping for findbugs
 * @author F. Motte
 *
 */
public class FindBugsMappingRuleSet {


	public static Map<String, String> NameToCode = new HashMap<String, String>() {

		/**
		 * 
		 */
		private static final long serialVersionUID = 416283530610647881L;

		{
			put("STYLE", "D");
			put("MALICIOUS_CODE", "V");
			put("BAD_PRACTICE", "B");
			put("CORRECTNESS", "C");
			put("I18N", "I");
			put("SECURITY", "S");
			put("PERFORMANCE", "P");
			put("MT_CORRECTNESS", "M");
			put("EXPERIMENTAL", "X");
		}
	};
	
	public static Map<String, String> CodeToName = new HashMap<String, String>() {

		/**
		 * 
		 */
		private static final long serialVersionUID = 416283530610647882L;

		{
			put("D", "STYLE");
			put("V", "MALICIOUS_CODE");
			put("B", "BAD_PRACTICE");
			put("C", "CORRECTNESS");
			put("I", "I18N");
			put("S", "SECURITY");
			put("P", "PERFORMANCE");
			put("M", "MT_CORRECTNESS");
			put("X", "EXPERIMENTAL");
		}
	};
	
	
}
