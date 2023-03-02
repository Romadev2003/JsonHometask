import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Server server = new Server();
        server.loadJson();


        Label:
        while (true) {
            System.out.println("""
                    tanlang:
                    1.canculator
                    2.pullar ro`yxati
                    3.chiqish
                    """);
            int choise = scanner.nextInt();
            switch (choise) {
                case 1: {
                    System.out.println("kodni kiriting");
                    String code = scanner.next();
                    server.canculate(code);
                    break;
                }

                case 2: {
                    System.out.println("Ro`yxat :");
                    server.list();
                    break;
                }
                case 3: {
                    break Label;
                }
                default:
                    System.out.println("qayta urinib ko`ring");
            }
        }
    }
}