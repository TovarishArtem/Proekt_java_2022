package src.Vk_API;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.base.City;
import com.vk.api.sdk.objects.base.Sex;
import com.vk.api.sdk.objects.groups.*;
import com.vk.api.sdk.objects.users.Fields;
import com.vk.api.sdk.objects.users.UserFull;
import src.Person.Person;
import src.course.TypeTask;

import java.text.SimpleDateFormat;
import java.util.*;

public class VkRepository {
    public  LinkedHashMap<String, Person> studentInfo = new LinkedHashMap<>();
    private final int APP_ID = 0;
    private final String CODE = "";
    private final VkApiClient vk;
    private final UserActor actor;


    public VkRepository() {
        TransportClient transportClient = new HttpTransportClient();
        vk = new VkApiClient(transportClient);
        actor = new UserActor(APP_ID, CODE);
    }

    public Group firstGetIDpubli() throws ClientException, ApiException {
        String groupFirst = "Второй курс ИОТ, УрФУ";
        return vk.groups()
                .search(actor, groupFirst)
                .execute()
                .getItems()
                .get(0);
    }
    public Group secondGetIDpubli() throws ClientException, ApiException, InterruptedException {
        Thread.sleep(100);
        String groupFirst = "Уральский федеральный университет | УрФУ";
        return vk.groups()
                .search(actor, groupFirst)
                .execute()
                .getItems()
                .get(0);

    }
    public Group thirdGetIDpubli() throws ClientException, ApiException, InterruptedException {
        Thread.sleep(100);
        String groupFirst = "[ТЕ] Типичный Екатеринбург";
        return vk.groups()
                .search(actor, groupFirst)
                .execute()
                .getItems()
                .get(0);

    }


    public UserFull getId(Group group1, Group group2, Group group3, String name) throws ClientException, ApiException, InterruptedException {

        Thread.sleep(4000);
        UserFull result = vk.users()
                .search(actor)
                .groupId(group1.getId())
                .q(name)
                .fields(Fields.CITY, Fields.BDATE, Fields.SEX)
                .count(1)
                .execute()
                .getItems()
                .stream()
                .findFirst()
                .orElse(null);
        Thread.sleep(4000);
        if (result == null){
            Thread.sleep(4000);
            result =  vk.users()
                    .search(actor)
                    .groupId(group2.getId())
                    .q(name)

                    .fields(Fields.CITY, Fields.BDATE, Fields.SEX)
                    .count(1)
                    .execute()
                    .getItems()
                    .stream()
                    .findFirst()
                    .orElse(null);
        }
        if (result == null){
            Thread.sleep(4000);
            return  vk.users()
                    .search(actor)
                    .groupId(group3.getId())
                    .q(name)
                    .fields(Fields.CITY, Fields.BDATE, Fields.SEX)

                    .count(1)
                    .execute()
                    .getItems()
                    .stream()
                    .findFirst()
                    .orElse(null);
        }

        return result;

    }
    public String getCityOfStudent(UserFull result) throws ClientException, ApiException, InterruptedException {


        if (result == null){
            return null;

        }
        else {
            if (result.getCity() == null) {
                return "-";

            }
            else {
                return result.getCity().getTitle();

            }
        }


    }
    public String getBdate( UserFull result) throws ClientException, ApiException, InterruptedException {




        if (result == null){
            return null;
        }
        else {
            if (result.getBdate() == null) {
                return "-";
            }
            else{
                return  (result.getBdate());
            }
        }




    }
    public String getSex(UserFull result) throws ClientException, ApiException, InterruptedException {



        if (result == null){

            return null;
        }
        else {
            if (result.getSex() == null) {
                return null;
            }
            else return result.getSex().name();
        }



    }
    public LinkedHashMap<String, Person> report(List<String> students) throws ClientException, ApiException, InterruptedException {
        Group group1 = firstGetIDpubli();
        Group group2 = secondGetIDpubli();
        Group group3 = thirdGetIDpubli();

        for (var name : students){

            UserFull user =  getId(group1, group2, group3,  name);

            String line = null;
            Person person= null;
            if (user == null){

                person = new Person(name,  getSex(user) , getCityOfStudent(user), getBdate(user) );

            }

            else{
                person = new Person(name, getSex(user) , getCityOfStudent(user), getBdate(user) );


            }
            studentInfo.put(name, person);


        }
        return studentInfo;



    }
    public void getReport(Map studentInfo) throws ClientException, ApiException {


        for (var i : studentInfo.values()) {



            System.out.println(i.toString());



        }
    }

}