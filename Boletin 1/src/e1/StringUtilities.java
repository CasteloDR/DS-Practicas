package e1;

public class StringUtilities {

    /**
     * Checks if a given string is a valid c of two others . That is , it contains
     * all characters of the other two and order of all characters in the individual
     * strings is preserved .
     * @param a First String to be mixed
     * @param b Second String to be mixed
     * @param c Mix of the two other Strings
     * @return true if the c is valid , false otherwise
     */

    public static boolean isValidMix(String a, String b, String c){
        if ((a == null) || (b == null) || (c == null) || (c.length() != a.length() + b.length())) {
            return false;
        }
        else{
            int A = 0;
            int B = 0;
            for(int i = 0; i < c.length(); i++){
                if(c.charAt(i) == a.charAt(A)) {
                    if (A < a.length() - 1)
                        A = A + 1;
                }else if(c.charAt(i) == b.charAt(B)) {
                    if (B <= b.length() - 1)
                        B = B + 1;
                }else if(c.charAt(i) != a.charAt(A) || c.charAt(i) != b.charAt(B))
                    return false;
            }
        }
        return true;
    }

    /**
     * Generates a random valid mix for two Strings
     * @param a First String to be mixed
     * @param b Second String to be mixed
     * @return A String that is a random valid mix of the other two .
     */

    public static String generateRandomValidMix(String a, String b) {
        int A = 0, B = 0, r;
        StringBuilder c = new StringBuilder();
        for(int i = 0; i < a.length()+b.length(); i++){
            r = (int) (Math.random()*2);
            if(r == 0){
                if(A < a.length()) {
                    c.append(a.charAt(A));
                    A = A + 1;
                }else {
                    c.append(b.substring(B));
                    return c.toString();
                }
            }
            else if(r == 1){
                if(B < b.length()){
                    c.append(b.charAt(B));
                    B = B + 1;
                }else{
                    c.append(a.substring(A));
                    return c.toString();
                }
            }
        }
        return c.toString();
    }
}
