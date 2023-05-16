import java.util.*;
import static org.junit.Assert.*;
import java.util.Comparator;
import java.util.Arrays;
import org.junit.Test;

public class Mixing {
    public static String mix(String s1, String s2) {
        int[] count1 = new int[26], count2 = new int[26];
        for(char c : s1.toCharArray()) if(Character.isLowerCase(c)) count1[c-'a']++;
        for(char c : s2.toCharArray()) if(Character.isLowerCase(c)) count2[c-'a']++;
        
        List<String> list = new ArrayList<>();
        for(int i = 0; i < 26; i++) {
            if(count1[i] > 1 || count2[i] > 1) {
                String prefix = count1[i] > count2[i] ? "1:" : count2[i] > count1[i] ? "2:" : "=:";
                String body = String.join("", Collections.nCopies(Math.max(count1[i], count2[i]), Character.toString((char)(i+'a'))));
                list.add(prefix + body);
            }
        }
        Collections.sort(list, (a, b) -> {
            if(a.length() != b.length()) return b.length() - a.length();
            return a.compareTo(b);
        });
        return String.join("/", list);
    }
}

// test this one

public class MixingTest {
    
    @Test
    public void test() {
        System.out.println("Mix Fixed Tests");
        assertEquals("2:eeeee/2:yy/=:hh/=:rr", Mixing.mix("Are they here", "yes, they are here"));
        assertEquals("1:ooo/1:uuu/2:sss/=:nnn/1:ii/2:aa/2:dd/2:ee/=:gg", 
                Mixing.mix("looping is fun but dangerous", "less dangerous than coding"));
        assertEquals("1:aaa/1:nnn/1:gg/2:ee/2:ff/2:ii/2:oo/2:rr/2:ss/2:tt", 
                Mixing.mix(" In many languages", " there's a pair of functions"));
        assertEquals("1:ee/1:ll/1:oo", Mixing.mix("Lords of the Fallen", "gamekult"));
        assertEquals("", Mixing.mix("codewars", "codewars"));
        assertEquals("1:nnnnn/1:ooooo/1:tttt/1:eee/1:gg/1:ii/1:mm/=:rr", 
                Mixing.mix("A generation must confront the looming ", "codewarrs"));
    }
    
}
