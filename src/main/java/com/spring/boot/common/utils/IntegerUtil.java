package com.spring.boot.common.utils;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author  xuzao
 * 整形工具类
 */
public class IntegerUtil {
	/**
	 * Integer 类型比较
	 * 
	 * @return 等于！
	 */
	public static Boolean equalTo(Integer one, Integer two) {
		return one.compareTo(two) == 0;

	}

	public static Boolean equalTo(Long one, Long two) {
		return one.compareTo(two) == 0;

	}

	public static Boolean notNullEqualTo(Integer one, Integer two) {
		if (one != null && two != null) {
			return one.compareTo(two) == 0;
		} else if (one != null && two == null) {
			return false;
		} else if (one == null && two != null) {
			return false;
		} else {
			return true;
		}

	}

	public static Boolean equalTo(int one, Integer two) {
		return one == two;

	}

	public static Boolean equalTo(Integer one, int two) {
		return one == two;
	}

	public static Boolean equalTo(int one, int two) {
		return one == two;
	}

	public static Boolean equalTo(BigDecimal one, BigDecimal two) {
		return one.compareTo(two) == 0;
	}

	public static Boolean equalTo(String one, String two) {
		return one.compareTo(two) == 0;
	}

	public static Boolean equalTo(Date one, Date two) {
		return one.compareTo(two) == 0;
	}

	/**
	 * Integer 类型比较
	 * 
	 * @return 大于！
	 */

	public static Boolean greaterThan(Integer one, Integer two) {
		return one.compareTo(two) == 1;

	}

	public static Boolean greaterThan(int one, Integer two) {
		return one > two;

	}

	public static Boolean greaterThan(Integer one, int two) {
		return one > two;
	}

	public static Boolean greaterThan(int one, int two) {
		return one > two;
	}

	public static Boolean greaterThan(BigDecimal one, BigDecimal two) {
		return one.compareTo(two) == 1;
	}

	public static Boolean greaterThan(String one, String two) {
		return one.compareTo(two) == 1;
	}

	public static Boolean greaterThan(Date one, Date two) {
		return one.compareTo(two) == 1;
	}

	/**
	 * Integer 类型比较
	 * 
	 * @return 小于！
	 */

	public static Boolean lessThan(Integer one, Integer two) {
		return one.compareTo(two) == -1;

	}

	public static Boolean lessThan(int one, Integer two) {
		return one < two;

	}

	public static Boolean lessThan(Integer one, int two) {
		return one < two;
	}

	public static Boolean lessThan(int one, int two) {
		return one < two;
	}

	public static Boolean lessThan(BigDecimal one, BigDecimal two) {
		return one.compareTo(two) == -1;
	}

	public static Boolean lessThan(String one, String two) {
		return one.compareTo(two) == -1;
	}

	public static Boolean lessThan(Date one, Date two) {
		return one.compareTo(two) == -1;
	}

	/**
	 * Integer 类型比较
	 * 
	 * @param one
	 * @param two
	 * @return 大于等于！
	 */

	public static Boolean greaterThanOrEqualTo(Integer one, Integer two) {
		return one.compareTo(two) >= 0;

	}

	public static Boolean greaterThanOrEqualTo(int one, Integer two) {
		return one >= two;

	}

	public static Boolean greaterThanOrEqualTo(Integer one, int two) {
		return one >= two;
	}

	public static Boolean greaterThanOrEqualTo(int one, int two) {
		return one >= two;
	}

	public static Boolean greaterThanOrEqualTo(BigDecimal one, BigDecimal two) {
		return one.compareTo(two) >= 0;
	}

	public static Boolean greaterThanOrEqualTo(String one, String two) {
		return one.compareTo(two) >= 0;
	}

	public static Boolean greaterThanOrEqualTo(Date one, Date two) {
		return one.compareTo(two) >= 0;
	}

	/**
	 * Integer 类型比较
	 * 
	 * @param one
	 * @param two
	 * @return 小于等于！
	 */

	public static Boolean lessThanOrEqualTo(Integer one, Integer two) {
		return one.compareTo(two) <= 0;

	}

	public static Boolean lessThanOrEqualTo(int one, Integer two) {
		return one <= two;

	}

	public static Boolean lessThanOrEqualTo(Integer one, int two) {
		return one <= two;
	}

	public static Boolean lessThanOrEqualTo(int one, int two) {
		return one <= two;
	}

	public static Boolean lessThanOrEqualTo(BigDecimal one, BigDecimal two) {
		return one.compareTo(two) <= 0;
	}

	public static Boolean lessThanOrEqualTo(String one, String two) {
		return one.compareTo(two) <= 0;
	}

	public static Boolean lessThanOrEqualTo(Date one, Date two) {
		return one.compareTo(two) <= 0;
	}

	/**
	 * Integer 类型比较
	 * 
	 * @param one
	 * @param two
	 * @return 不等于！
	 */

	public static Boolean notEqualTo(Integer one, Integer two) {
		return one.compareTo(two) != 0;

	}

	public static Boolean notEqualTo(Long one, Long two) {
		return one.compareTo(two) != 0;

	}

	public static Boolean notEqualTo(int one, Integer two) {
		return one != two;

	}

	public static Boolean notEqualTo(Integer one, int two) {
		return one != two;
	}

	public static Boolean notEqualTo(int one, int two) {
		return one != two;
	}

	public static Boolean notEqualTo(BigDecimal one, BigDecimal two) {
		return one.compareTo(two) != 0;
	}

	public static Boolean notEqualTo(String one, String two) {
		return one.compareTo(two) != 0;
	}

	public static Boolean notEqualTo(Date one, Date two) {
		return one.compareTo(two) != 0;
	}

	public static Integer get4format(String s) {
		return Integer.parseInt(s);
	}

	public static Integer parseInt(String s){
		try {
			return Integer.parseInt(s);
		}catch (Exception e){
			return null;
		}
	}

}
