public class timerSt {
    private final long createdMillis = System.currentTimeMillis();


    public int stopTime(){
        long nowMillis = System.currentTimeMillis();
        int diffT=(int)((nowMillis - this.createdMillis) / 1000);
        System.out.println(1000-diffT);
        return diffT;

    }

    public static void main(String[] args) throws InterruptedException {
        timerSt t=new timerSt();
        while(t.stopTime()<1000){
            //System.out.println(t.stopTime());
            Thread.sleep(1000);
        }
    }
}
