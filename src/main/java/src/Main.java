package src;


import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.groups.Group;
import com.vk.api.sdk.objects.users.User;
import src.CSV.Parser;
import src.Vk_API.VkRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ClientException, ApiException, InterruptedException {
        List<String> nameStudent = new ArrayList<>();
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
        var map =  vk.report(nameStudent);
        vk.getReport(map);
    }
}
