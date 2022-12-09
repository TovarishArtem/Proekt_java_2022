package src;


import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.groups.Group;
import com.vk.api.sdk.objects.users.User;
import src.CSV.Parser;
import src.Vk_API.VkRepository;
import src.course.Course;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException, ClientException, ApiException, InterruptedException {

        Set<String> nameStudent = new LinkedHashSet<>();
        VkRepository vk = new VkRepository();

        var report = Parser.getReport("C:\\Program Files\\gradle\\gradle\\src\\proektt\\basicprogramming_2.csv");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        for(var i : report.keySet()){
            nameStudent.add(i);
            if (i.contains(name)){

                System.out.println(i);
                for (var j : report.get(i)){
                    System.out.println(j);
                }
            }
        }
        System.out.println();
        var map = vk.report(nameStudent);
        vk.getReport(map);
       /*var modules = Parser.getReport1("C:\\Program Files\\gradle\\gradle\\src\\proektt\\basicprogramming_2.csv");
       for (var i : modules){
            System.out.println(i.getName());

            System.out.println(i.toString());
            for (var task : i.getTasks()){
                System.out.println(task.toString());
            }
       }*/

    }
}
