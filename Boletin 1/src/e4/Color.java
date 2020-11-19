package e4;


enum Color{
    GREEN(),
    AMBER(),
    RED();

    private boolean is_blinking;

    Color(){
        this.is_blinking = false;
    }

    public void setBlinking(boolean blinks){
        if (this==AMBER)
            this.is_blinking = blinks;
    }

    public boolean isBlinking(){
        return this.is_blinking;
    }

}