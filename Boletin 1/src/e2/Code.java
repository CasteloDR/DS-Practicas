package e2;

public class Code {

    //Metodo para saber si la matriz es rectangular
    public static boolean esRectangular(char[][] keypad){
        if(keypad == null)
            return false;

        int ancho = keypad[0].length;
        for(int i = 0; i < keypad.length; i++){
            if(keypad[i] == null)
                return false;

            if(ancho != keypad[i].length){
                return false;
            }
        }
        return true;
    }

    //true: en linea // false: en columna
    public static boolean filaColumna (char [][] keypad){
        if(keypad.length == 1)
            return true;

        if(keypad[0].length == 1)
            return false;

        return (int) keypad[0][1] == ((int) keypad[0][0]) + 1;
    }

    /**
     * Determines whether a keypad is valid . To do this , it must be a rectangle and
     * the numbers must follow the alphanumeric sequence indicated . If the array
     * (or any of its subarrays ) is null it will be returned false .
     * @param keypad The keypad to be analyzed .
     * @return true if it is valid , false otherwise .
     */

    public static boolean isKeypadValid (char [][] keypad){
        char[] array = {'1','2','3','4','5','6','7','8','9','0','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        if(esRectangular(keypad)) {
            int v = -1;
            if (filaColumna(keypad)) {
                for (int i = 0; i < keypad.length; i++) {
                    for (int j = 0; j < keypad[0].length; j++) {
                        v = v + 1;
                        if (keypad[i][j] != array[v]) {
                            return false;
                        }
                    }
                }
            } else {
                for (int j = 0; j < keypad[0].length; j++) {
                    for (int i = 0; i < keypad.length; i++) {
                        v = v + 1;
                        if (keypad[i][j] != array[v]) {
                            return false;
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }

    // Metodo para comprobar si el array es nulo o contiene nulos
    public static boolean esNulo(String[] movements){
        if(movements == null)
            return true;

        for(int i = 0; i < movements.length; i++) {
            if (movements[i] == null)
                return true;
        }
        return false;
    }

    /**
     * Checks if an array of movements is valid when obtaining a key on a keypad .
     * An array of movements is valid if it is formed by Strings that only have the
     * four capital letters U, D, L and R. If the array of Strings or any of the
     * Strings is null it will be returned false .
     * @param movements Array of Strings representing movements .
     * @return true if it is valid , false otherwise .
     */

    public static boolean areMovementsValid (String[] movements) {
        char[] validc = {'U', 'D', 'L', 'R'};
        if(esNulo(movements))
            return false;

        for(int i = 0; i < movements.length; i++) {
            for (int v = 0; v < movements[i].length(); v++) {
                char thischar = movements[i].charAt(v);
                boolean isvalid = false;
                for (int j = 0; j < validc.length; j++)
                    isvalid |= (thischar == validc[j]); // isvalid = isvalid | (thischar == validc[j])
                if (!isvalid)
                    return false;
            }
        }
        return true;
    }

    /**
     * Given a keypad and an array of movements , it returns a String with the code
     * resulting from applying the movements on the keypad .
     * @param keypad alphanumeric keypad .
     * @param movements Array with the different movements to be made for each
    number of the key .
     * @return Resulting code .
     * @throws IllegalArgumentException if the keypad of the movements are invalid .
     */

    public static String obtainCode (char[][] keypad, String[] movements){
        if(!isKeypadValid(keypad) || !areMovementsValid(movements))
            throw new IllegalArgumentException();

        StringBuilder code = new StringBuilder();
        char c = ' ';
        int v = 0;
        int h = 0;
        for(int i = 0; i < movements.length; i++){
            if(c != ' ') {
                code.append(c);
            }
            for(int n = 0; n < movements[i].length(); n++){
                char thischar = movements[i].charAt(n);

                switch (thischar){
                    case 'U':
                        v = v - 1;
                        if(v < 0){
                            v = v + 1;
                            c = keypad[v][h];
                        }else c = keypad[v][h];
                        break;

                    case 'D':
                        v = v + 1;
                        if(v >= keypad.length){
                            v = v - 1;
                            c = keypad[v][h];
                        }else c = keypad[v][h];
                        break;

                    case 'L':
                        h = h - 1;
                        if(h < 0){
                            h = h + 1;
                            c = keypad[v][h];
                        }else c = keypad[v][h];
                        break;

                    case 'R':
                        h = h + 1;
                        if(h >= keypad[0].length){
                            h = h -1;
                            c = keypad[v][h];
                        }else c = keypad[v][h];
                        break;

                }
            }
        }
        code.append(c);
        return code.toString();
    }
}
