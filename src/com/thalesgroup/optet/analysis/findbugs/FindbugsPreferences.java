package com.thalesgroup.optet.analysis.findbugs;

import java.util.Arrays;
import java.util.List;



/**
 * The Findbugs preference access
 * @author F. Motte
 *
 */
public class FindbugsPreferences {

	/**
	 * isFindbugsUsed return true if Findbugs must be used from the preference choice
	 * @return true if Findbugs must be used from the preference choice
	 */
	public boolean isFindbugsUsed() {
		return true;
	}

	/**
	 * getFindbugsCategories return the findbugs categories selected by the user
	 * @return the category list
	 */
	public  List<String> getFindbugsCategories(){
		/**	STYLE = D
		MALICIOUS_CODE = V
		BAD_PRACTICE = B
		CORRECTNESS = D
		I18N = I
		SECURITY = S
		PERFORMANCE = P
		MT_CORRECTNESS = M
		EXPERIMENTAL = X */

		String ruleset = "";
		
		return Arrays.asList(ruleset.split(","));
	}
}
