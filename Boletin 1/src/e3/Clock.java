package e3;

public class Clock {

    private final int hora, minutos, segundos;

    enum Period{
        AM ("AM"),
        PM ("PM");

        private final String valor;

        public String getValor() {
            return valor;
        }
        Period(String valor){
            this.valor = valor;
            }
    }

    private final Period periodo;

    /**
     * Creates a Clock instance parsing a String .
     * @param s The string representing the hour in 24h format (17:25:15) or in
     * 12h format (05:25:15 PM ).
     * @throws IllegalArgumentException if the string is not a valid hour .
     */

    public Clock(String s) {
        if (s.length() == 8 || s.length() == 11) {
            String[] tiempo = s.substring(0, 8).split(":");

            int hours = Integer.parseInt(tiempo[0]);
            int minutes = Integer.parseInt(tiempo[1]);
            int seconds = Integer.parseInt(tiempo[2]);
            if ((hours >= 0 && hours <= 24) && (minutes >= 0 && minutes <= 60) && (seconds >= 0 && seconds <= 60)){

                this.hora = hours;
                this.minutos = minutes;
                this.segundos = seconds;

                if (s.length() == 11) {
                    String peri = s.substring(9);
                    this.periodo = Period.valueOf(peri);

                } else {
                    if (this.hora > 12)
                        this.periodo = Period.PM;
                    else this.periodo = Period.AM;
                }
            } else throw new IllegalArgumentException();
        } else throw new IllegalArgumentException();
    }

    /**
     * Creates a clock given the values in 24h format .
     * @param hours Hours in 24h format .
     * @param minutes Minutes .
     * @param seconds Seconds .
     * @throws IllegalArgumentException if the values do not represent a valid
     * hour .
     */

    public Clock(int hours, int minutes, int seconds) {
        if ((hours >= 0 && hours <= 24) && (minutes >= 0 && minutes <= 60) && (seconds >= 0 && seconds <= 60) ){
            this.hora = hours;
            this.minutos = minutes;
            this.segundos = seconds;

            if(hours > 12)
                this.periodo = Period.PM;
            else this.periodo = Period.AM;

        }else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Creates a clock given the values in 12h format . Period is a enumeration
     * located inside the Clock class with two values : AM and PM.
     * @param hours Hours in 12h format .
     * @param minutes Minutes .
     * @param seconds Seconds .
     * @param period Period used by the Clock ( represented by an enum ).
     * @throws IllegalArgumentException if the values do not represent a valid
     * hour .
     */

    public Clock(int hours, int minutes, int seconds, Period period){
        if ((hours > 0 && hours <= 12) && (minutes >= 0 && minutes <= 60) && (seconds >= 0 && seconds <= 60)){
            this.hora = hours;
            this.minutos = minutes;
            this.segundos = seconds;
            this.periodo = period;


        }else {
            throw new IllegalArgumentException();
        }

    }

    public int getHours24(){
        int hora24 = hora;

        if (hora24 < 12 && periodo == Period.PM)
            hora24 = hora24 + 12;

        if (hora24 == 12 && periodo == Period.AM)
            hora24 = 0;


        return hora24;
    }

    public int getHours12(){
        int hora12 = hora;

        if (hora12 > 12)
            hora12 = hora12 - 12;

        return hora12;
    }

    public int getMinutes(){
        return minutos;
    }

    public int getSeconds(){
        return segundos;
    }

    public Period getPeriod() {
        return periodo;
    }

    public String printHour24() {

        return String.format("%02d:%02d:%02d", getHours24(), getMinutes(), getSeconds());
    }

    public String printHour12() {
        return String.format("%02d:%02d:%02d %s", getHours12(), getMinutes(), getSeconds(), getPeriod());
    }


    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;

        if (o == null)
            return false;

        if (getClass() != o.getClass())
            return false;

        Clock clock = (Clock) o;
        return this.getHours24() == clock.getHours24() && this.getMinutes() == clock.getMinutes() && this.getSeconds() == clock.getSeconds();


    }

    @Override
    public int hashCode(){
        int result = printHour24().hashCode();
        result = 31 * result + printHour12().hashCode();
        return result;
    }
}
