package com.gnt.BillingSystem.POJO;

public final class Constants {

	public static final String COMPANY = "X";

	public static enum employeeJobTitle {
		I, O;
		public String toString() {
			switch (this) {
			case I:
				return "I";
			case O:
				return "O";
			}
			return null;
		}
	}

	public static boolean enumContains(String str) {
		for (int index = 0; index < employeeJobTitle.values().length; index++) {
			if (employeeJobTitle.values()[index].toString().equals(str)) {
				return true;
			}
		}
		return false;
	}

}
