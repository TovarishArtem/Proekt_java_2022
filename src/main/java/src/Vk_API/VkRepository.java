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
import com.vk.api.sdk.objects.users.UserFull;
import src.Person.Person;
import src.course.TypeTask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VkRepository {
    public  Map<String, String> studentInfo = new HashMap<>();
    private final int APP_ID = 51492888;
    private final String CODE = "vk1.a.RUnfBAwEwzCvk3mEuTh5P-kh8n6aceODwizXB8dNYvHrcElXP_EAxXcvJ60mAlHyUWCFKs7mPT2PFImvoF0t42sPwQNGgTqap6cNPaIGsWWS4EmqN5VQ6UI8btTXKh2VJ_hUKJPV0ANPG_V4mYcnqCNC9GqRF3KMNYr5gNKqqdmpAjAOXTJbufUR_6hkH2vX";
    private final VkApiClient vk;
    private final UserActor actor;


    public VkRepository() {
        TransportClient transportClient = new HttpTransportClient();
        vk = new VkApiClient(transportClient);
        actor = new UserActor(APP_ID, CODE);
    }

    public Group gitIDpublic() throws ClientException, ApiException {
        String groupFirst = "Уральский федеральный университет | УрФУ";
        return vk.groups()
                .search(actor, groupFirst)
                .execute()
                .getItems()
                .get(0);
        }

    public UserFull getId(Group group, String name) throws ClientException, ApiException, InterruptedException {

        UserFull result = vk.users()
                    .search(actor)
                    .q(name)
                    .groupId(group.getId())
                    .count(1)
                    .execute()
                    .getItems()
                    .stream()
                    .findFirst()
                    .orElse(null);
        Thread.sleep(300);


    return result;

    }
    public City getCityOfStudent(Group group, String name) throws ClientException, ApiException, InterruptedException {

        UserFull result = vk.users()
                .search(actor)
                .q(name)
                .groupId(group.getId())
                .count(1)
                .execute()
                .getItems()
                .stream()
                .findFirst()
                .orElse(null);

        if (result == null){
            return null;

        }
        else {
            if (result.getCity() == null) {
                return null;

            }
            else {
                return result.getCity();

            }
        }


    }
    public String getBdate( UserFull result) throws ClientException, ApiException, InterruptedException {




        if (result == null){
            return null;
        }
        else {
            if (result.getCity() == null) {
                return "-";
            }
            else{
                return  new SimpleDateFormat("dd.MM.yyyy").format(result.getBdate());
            }
        }




    }
    public Sex getSex(UserFull result) throws ClientException, ApiException, InterruptedException {



        if (result == null){

            return null;
        }
        else {
            if (result.getCity() == null) {
                return null;
            }
            else return result.getSex();
        }



    }
   public Map report(List<String> students) throws ClientException, ApiException, InterruptedException {
       Group group = gitIDpublic();

        for (var name : students){

            UserFull user =  getId(group, name);

            String line = null;
            Person person= null;
            if (user == null){
                String nameVk = String.format("Студент < %s > не найден в ВК ", name);
                person = new Person(nameVk,  getSex(user) , getCityOfStudent(group, name), getBdate(user) );

            }

            else{
                person = new Person(name, getSex(user) , getCityOfStudent(group, name), getBdate(user) );


            }
            studentInfo.put(name, person.toString());


        }
        return studentInfo;



    }
    public void getReport(Map studentInfo) throws ClientException, ApiException {


        for (var i : studentInfo.values()) {



                System.out.println(i);



        }
    }

}