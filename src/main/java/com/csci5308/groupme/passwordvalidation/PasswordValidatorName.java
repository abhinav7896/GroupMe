package com.csci5308.groupme.passwordvalidation;

/**
 * @author Krutarth Patel
 */

public enum PasswordValidatorName {
	
	MAXIMUMLENGTH {
		public String toString() {
			return "Maximum Length";
		}
	},
	MINIMUMLENGTH{
		public String toString() {
			return "Minimum Length";
		}
	},
	MINIMUMLOWERCASE{
		public String toString() {
			return "Minimum Lowercase";
		}
	},
	MINIMUMUPPERCASE{
		public String toString() {
			return "Minimum Uppercase";
		}
	},
	MINIMUMSYMBOLS{
		public String toString() {
			return "Minimum Symbols";
		}
	},
	RESTRICTEDSYMBOLS{
		public String toString() {
			return "Restricted Characters";
		}
	},
	PASSWORDHISTORY{
		public String toString() {
			return "Password History";
		}
	}
}
