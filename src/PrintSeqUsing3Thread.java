public class PrintSeqUsing3Thread {

    private int numberOfThread=3;
    private int seqToPrint=20;
    private int number=1;
    public void PrintNumber(int result){

        synchronized (this){
            while(number<seqToPrint){
               while (number%numberOfThread!=result){
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println(Thread.currentThread().getName()+" "+number);
                number+=1;
                notifyAll();
            }
        }
    }
    public static void main(String[] args) {
        PrintSeqUsing3Thread t=new PrintSeqUsing3Thread();
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                t.PrintNumber(1);
            }
        },"Thread-1");
        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                t.PrintNumber(2);
            }
        },"Thread-2");

        Thread t3=new Thread(new Runnable() {
            @Override
            public void run() {
                t.PrintNumber(0);
            }
        },"Thread-3");
        t1.start();
        t2.start();
        t3.start();
        System.out.println("end");
    }


}
