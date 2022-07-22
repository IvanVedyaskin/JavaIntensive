package ex00;

public class Program {
    public static void main(String[] args) {
        int count = 0;
        if (args.length == 1){
            if (args[0].startsWith("--count=")){
                try{
                    count = Integer.parseInt(args[0].substring(args[0].indexOf("=") + 1));
                }
                catch (NumberFormatException e){
                    System.err.println("Argument must be number");
                }
            }
        }else{
            System.err.println("Argument is not valid");
            System.exit(1);
        }
        final int counter = count;
        Thread eggThread = new Thread(()-> {
            for (int i = 1; i <= counter; i++){
                System.out.println(i + " Egg");
            }
        });
        Thread henThread = new Thread(()-> {
            for (int i = 1; i <= counter; i++){
                System.out.println(i + " Hen");
            }
        });
        henThread.start();
        eggThread.start();
        try{
            eggThread.join();
            henThread.join();
            for (int i = 1; i <= count; i++){
                System.out.println(i + " Human");
            }
        }
        catch (InterruptedException e) {
            System.err.println("Error in join");
        }
    }
}
