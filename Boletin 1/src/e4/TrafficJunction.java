package e4;

public class TrafficJunction {



    Semaforo[] semaforo = new Semaforo[4];
    int activo;

    //NORTH = 0, SOUTH = 1, EAST = 2, WEST = 3

    /**
     * Creates a trafic junction with four traffic lights named north , south ,
     * east and west . The north traffic light has just started its green cycle .
     */

    public TrafficJunction() {
        semaforo[0] = new Semaforo(Color.GREEN);
        for (int i = 1; i<4; i++)
            semaforo[i] = new Semaforo(Color.RED);
        activo = 0;
    }

    /**
     * Indicates that a second of time has passed , so the traffic light with
     * the green or amber light should add 1 to its counter . If the counter
     * passes its maximum value the color of the traffic light must change .
     * If it changes to red then the following traffic light changes to green .
     * The order is: north , south , east , west and then again north .
     */

    public void timesGoesBy() {
        semaforo[activo].tick();
        if ((semaforo[activo].getColor()) == Color.RED) {
            activo = (activo + 1) % 4;
            semaforo[activo].reset();
        }
    }

    /**
     * If active is true all the traffic lights of the junction must change to
     * blinking amber ( meaning a non - controlled junction ).
     * If active is false it resets the traffic lights cycle and started again
     * with north at green and the rest at red.
     * @param active true or false
     */

    public void amberJunction (boolean active) {
        for(int i = 0; i<4; i++)
            this.semaforo[i].setBlinking(active);
        if(!active){
            activo = 0;
            semaforo[activo].reset();
        }
    }

    /**
     * Returns a String with the state of the traffic lights .
     * The format for each traffic light is the following :
     * - For red colors : "[ name : RED ]"
     * - For green colors : "[ name : GREEN counter ]"
     * - For yellow colors with blink at OFF : "[ name : YELLOW OFF counter ]
     * - For yellow colors with blink at ON: "[ name : YELLOW ON]
     * Examples :
     * [ NORTH : GREEN 2][ SOUTH : RED ][ EAST : RED ][ WEST : RED ]
     * [ NORTH : AMBER OFF 5][ SOUTH : RED ][ EAST : RED ][ WEST : RED ]
     * [ NORTH : AMBER ON ][ SOUTH : AMBER ON ][ EAST : AMBER ON ][ WEST : AMBER ON]
     * @return String that represents the state of the traffic lights
     */

    @Override
    public String toString() {

        return "[NORTH: " + semaforo[0].getColor().toString() + semaforo[0].getselfTimes() + "]" +
                "[SOUTH: " + semaforo[1].getColor().toString() + semaforo[1].getselfTimes() + "]" +
                "[EAST: " + semaforo[2].getColor().toString() + semaforo[2].getselfTimes() + "]" +
                "[WEST: " + semaforo[3].getColor().toString() + semaforo[3].getselfTimes() + "]";
    }



}
