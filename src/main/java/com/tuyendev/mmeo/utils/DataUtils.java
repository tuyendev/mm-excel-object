package com.tuyendev.mmeo.utils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtils {

    public static boolean isNullOrEmpty(final Object[] collection) {
        return collection == null || collection.length == 0;
    }

    public static boolean isNullOrEmpty(final String s) {
        int strLen;
        if (s == null || (strLen = s.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static String toUpperCaseFirstCharacter(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    public static Double safeToDouble(Object obj1, Double defaultValue) {
        Double result = defaultValue;
        if (obj1 != null) {
            try {
                result = Double.parseDouble(obj1.toString());
            } catch (Exception ignored) {
            }
        }

        return result;
    }

    public static int safeToInt(Object obj1, Integer defaultValue) {
        Integer result = defaultValue;
        if (obj1 != null) {
            try {
                result = Integer.parseInt(obj1.toString());
            } catch (Exception ignored) {
            }
        }

        return result;
    }

    public static String safeToString(Object obj1, String defaultValue) {
        if (obj1 == null) {
            return defaultValue;
        }

        return obj1.toString();
    }

    public static Timestamp safeToTimeStamp(Object obj, String format) {
        return safeToTimeStamp(obj,format,null);
    }

    public static Timestamp safeToTimeStamp(Object obj, String format, Timestamp dv) {
        if (obj == null) return dv;
        try {
            if (obj instanceof Number) {
                return new Timestamp(((Number) obj).longValue());
            } else if (obj instanceof Timestamp) {
                return (Timestamp) obj;
            } else if (obj instanceof Date) {
                return new Timestamp(((Date) obj).getTime());
            } else if (obj instanceof  String) {
                format = isNullOrEmpty(format) ? "dd/MM/yyyy" : format;
                final DateFormat myFormat = new SimpleDateFormat(format);
                return new Timestamp(myFormat.parse((String) obj).getTime());
            }
        } catch (Exception e) {

        }
        return dv;
    }

    public static Date safeToDate(Object obj, String format) {
        return safeToDate(obj,format,null);
    }

    public static Date safeToDate(Object obj, String format, Date dv) {
        if (obj == null) return dv;
        try {
            if (obj instanceof Number) {
                return new Date(((Number) obj).longValue());
            } else if (obj instanceof Date) {
                return (Date) obj;
            } else if (obj instanceof  String) {
                format = isNullOrEmpty(format) ? "dd/MM/yyyy" : format;
                final DateFormat myFormat = new SimpleDateFormat(format);
                return myFormat.parse((String) obj);
            }
        } catch (Exception e) {

        }
        return dv;
    }

    public static Float safeToFloat(Object obj1, Float dv) {
        Float result = dv;
        if (obj1 == null) {
            return result;
        }
        try {
            result = Float.parseFloat(obj1.toString());
        } catch (Exception ignored) {
        }

        return result;
    }

    public static BigDecimal safeToBigDecimal(Object obj1, BigDecimal dv) {
        BigDecimal result = dv;
        if (obj1 == null) {
            return result;
        }
        try {
            result = new BigDecimal(obj1.toString());
        } catch (Exception ignored) {
        }

        return result;
    }

    public static Long safeToLong(Object obj1, Long dv) {
        if (obj1 == null) {
            return dv;
        }
        try {
            return Long.valueOf(obj1.toString());
        } catch (Exception e) {
            return dv;
        }
    }
}
