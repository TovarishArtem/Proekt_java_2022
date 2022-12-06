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
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ClientException, ApiException {

        VkRepository vk = new VkRepository();
        Group group = null;
        var report = Parser.getReport("C:\\Program Files\\gradle\\gradle\\src\\proektt\\basicprogramming_2.csv");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        for(var i : report.keySet()){

            if (i.contains(name)){
                System.out.println(i);
                for (var j : report.get(i)){
                    System.out.println(j);
                }

            }
        }
        vk.report(name);
        vk.getReport(name);




    }

}