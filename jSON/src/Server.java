import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {
    String JsonData;
    Gson gson=new Gson();
    Scanner scanner=new Scanner(System.in);
    ArrayList<Form> list=new ArrayList<>();
        public void loadJson() {

            try {
                URL url = new URL("https://nbu.uz/en/exchange-rates/json/");
            URLConnection connection = url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("\n");
            }
           JsonData = builder.toString();
                System.out.println("mofaqiyatli yuklandi....");

                 Type type= new TypeToken<ArrayList<Form>>(){}.getType();
                 list=gson.fromJson(JsonData,type);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    public void list()   {
        System.out.println("good job");
        for (Form form1 : list) {
            System.out.println(form1.code+" -> "+form1.title);
        }
    }

    public void canculate(String code) {
        for (Form form : list) {
            if (form.code.equals(code)) {
                System.out.println(1+" "+code+" = "+form.cb_price);
                Label:
                while (true) {
                System.out.println("tanlang:\n" +
                        "1.So`m dan "+code+"ga o`tkazish\n" +
                        "2."+code+"dan so`mga o`tkazish\n" +
                        "3.haqida malumot\n" +
                        "4.chiqish");
                int choise= scanner.nextInt();
                    switch (choise) {
                        case 1 :{ uzbTO(form.cb_price);}
                        case 2 :{ toUzb(form.cb_price);}
                        case 3 :{
                            System.out.println("Title:"+form.title+"\n" + "cb_price:"+form.cb_price+"\n" + "nbu_buy_price:"+form.nbu_buy_price+"\n" + "nbu_cell_price:"+form.nbu_cell_price+"\n");}
                        case 4: {break Label;}
                        default :{ System.out.println("xato tanlov");}
                    }
                }

            }
        }
    }

    public void toUzb(String prise) {
        System.out.println("So`mga o`tish");
        double money= scanner.nextDouble();

        double d = Double.parseDouble(prise);
        System.out.println(money*d);
    }

    public void uzbTO(String prise) {
        System.out.println("so`mdan o`tish");
        double d = Double.parseDouble(prise);
        double money= scanner.nextDouble();
        System.out.println(money/d);
    }


}
