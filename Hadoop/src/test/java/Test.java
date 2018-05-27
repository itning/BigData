import org.apache.commons.lang.math.NumberUtils;

public class Test {
    @org.junit.Test
    public void test() {
        String s = "1363157985066 \t13726230503\t00-FD-07-A4-72-B8:CMCC\t120.196.100.82\ti02.c.aliimg.com\t\t24\t27\t2481\t24681\t200";
        String[] strings = s.split("\t");
        System.out.println(strings[1]);
        System.out.println(NumberUtils.isDigits(strings[1]));
        System.out.println(Long.parseLong(strings[1]));
    }
}
