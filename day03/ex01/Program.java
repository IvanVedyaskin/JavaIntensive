package ex01;

public class Program {
    static final Object moninor = 1;
    static boolean printer = true;
    public static void main(String[] args) {
        int count = 0;

        if (args.length == 1){
            if (args[0].startsWith("--count=")){
                try{
                    count = Integer.parseInt(args[0].substring(args[0].indexOf("=") + 1));
                }
                catch (NumberFormatException e){
                    System.err.println("Argument must be number");
                    System.exit(1);
                }
            }
        }else{
            System.err.println("Argument is not valid");
            System.exit(1);
        }
        final int counter = count;
        Program program = new Program();
        Thread eggThread = new Thread(() -> program.printEgg(counter));
        Thread henThread = new Thread(() -> program.printEnn(counter));

        henThread.start();
        eggThread.start();
        try{
            eggThread.join();
            henThread.join();
        }
        catch (InterruptedException e) {
            System.err.println("Error in join");
        }
    }
    public void printEgg(int counter){
        synchronized (moninor){
            try{
                for (int i = 1;i <= counter; i++){
                    while (!printer){
                        moninor.wait();
                    }
                    System.out.println("Egg");
                    printer = false;
                   moninor.notify();
                }
            } catch (InterruptedException e){

            }
        }
    }

    public void printEnn(int counter){
        synchronized (moninor){
            try{
                for (int i = 1; i <= counter; i++){
                    while(printer){
                        moninor.wait();
                    }
                    System.out.println("Enn");
                    printer = true;
                    moninor.notify();
                }
            } catch (InterruptedException e){

            }
        }
    }
}
