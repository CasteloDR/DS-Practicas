package e4;

public class Semaforo {
    private Color color;
    private int self_time = 0;

    public Semaforo(Color color){
        this.color = color;
    }

    public void tick(){
        if (!this.color.isBlinking()){
            this.self_time++;
            this.updateColor();
        }
    }

    // Poner el semaforo en GREEN
    public void reset(){
        this.self_time = 0;
        this.updateColor();
    }

    // Poner el semaforo en RED (Resetear el cruce)
    public void resetRed(){
        this.self_time = -1;
        this.updateColor();
    }

    public void setBlinking(boolean blinks){
        if (blinks) {
            this.self_time = -1; //Not running
            this.color = Color.AMBER;
            this.color.setBlinking(true); //Amber and blinking
        }
        else{
            this.color.setBlinking(false);
            this.resetRed();
        }
    }

    public Color getColor(){
        return this.color;
    }

    public String getselfTimes(){
        if(this.color == Color.GREEN)
            return " " + this.self_time;
        else if(this.color == Color.AMBER) {
            if(this.color.isBlinking()) {
                return " ON";
            }else return " OFF " + (this.self_time - 16);
        }else return "";
    }

    private void updateColor(){
        if((self_time >=0) && (self_time <= 15))
            this.color = Color.GREEN;
        else if ((self_time >= 16) && (self_time <= 21))
            this.color = Color.AMBER;
        else if (self_time == -1 || self_time > 21)
            this.color = Color.RED;
    }
}

