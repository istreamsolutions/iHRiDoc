package com.istream.ihr.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

public class DAOUtil {
	public static Long getLongFromBigInteger(Object object) {
        return object == null ? null : (Long)((BigInteger)object).longValue();
    }

    public static Long getLongFromInteger(Object object) {
        return object == null ? null : (Long)((Integer)object).longValue();
    }

    public static Long getLongFromShort(Object object) {
        return object == null ? null : (Long)((Short)object).longValue();
    }

    public static Integer getIntFromInteger(Object object) {
        return object == null ? null : ((Integer)object).intValue();
    }

    public static Integer getInteger(Object object) {
        return object == null ? null : ((Integer) object);
    }

    public static Long getLong(Object object) {
        return object == null ? null : ((Long) object);
    }

    public static Double getDoubleFromBigDecimal(Object object) {
        return object == null ? null : (Double) ((BigDecimal) object).doubleValue();
    }

    public static String getString(Object object) {
        return object == null ? null : object.toString();
    }

    public static String getStringFromBigInteger(Object object) {
        BigInteger b = null;
        b = object == null ? null : ((BigInteger)object);
        return String.valueOf(b);
    }

    public static Date getDate(Object object) {
        return object == null ? null :  (Date)((Timestamp)object);
    }
    
    public static Date getDateFromSqlDate(Object object) {
        return object == null ? null :  (Date)((java.sql.Date)object);
    }

    public static Boolean getBoolean(Object object) {
        return object == null ? false :  convertToBoolean(object.toString());
    }

    public static Long getLongFromString(Object object) {
        return object == null ? null :  Long.valueOf(object.toString());
    }

    public static Integer getIntegerFromString(Object object) {
        return object == null ? null :  Integer.valueOf(object.toString());
    }

    public static boolean convertToBoolean(String string) {
        if(string.equals("Y")){
            return true;
        } else {
            return false;
        }
    }
}
