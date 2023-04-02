public class PrintSeqUsing2Thread {
    static int counter=1;
    static int n=50;
    public static void main(String[] args) {
        PrintSeqUsing2Thread print= new PrintSeqUsing2Thread();
        Thread t1=new Thread(new Runnable(){
            public void run(){
                print.printOddNumber();
            }
        },"thread1");
        Thread t2=new Thread(new Runnable(){
            public void run(){
                print.printEvenNumber();
            }
        },"thread2");
        t1.start();
        t2.start();
    }

    public void printOddNumber(){
        synchronized (this){while(counter<n){
            if(counter%2==0){
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println(Thread.currentThread().getName()+" print odd Num :"+ counter);
            counter+=1;
            notify();
        }}
    }
    public  void printEvenNumber(){
        synchronized (this){
        while(counter<n){

            if(counter%2!=0){
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println(Thread.currentThread().getName()+" print even Num :"+ counter);
            counter+=1;
            notify();
        }}
    }

}
