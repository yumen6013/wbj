package util;

public class IntegerUtil {

    public static int stringToInt(String intstr) {
        Integer integer;
        integer = Integer.valueOf(intstr);
        return integer.intValue();
    }

    //change int type to the string type
    public static String intToString(int value) {
        Integer integer = new Integer(value);
        return integer.toString();
    }

    //change the string type to the float type
    public static float stringToFloat(String floatstr) {
        Float floatee;
        floatee = Float.valueOf(floatstr);
        return floatee.floatValue();
    }

    //change the float type to the string type
    public static String floatToString(float value) {
        Float floatee = new Float(value);
        return floatee.toString();
    }

    public static String intStr(int n) {
        String nstr = n + "";
        String str = nstr;
        for (int i = 5; i > nstr.length(); i--) {
            str = "0" + str;
        }
        return str;
    }

}
