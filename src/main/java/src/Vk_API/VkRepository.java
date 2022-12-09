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
    public  SortedMap<String, String> studentInfo = new TreeMap<>();
    private final int APP_ID = 51492888;
    private final String CODE = "vk1.a.DSvDCq-7NM7_I8E3yQ2IQv0GMANK16dyNcJFNdMR0wXVuxke1FkObeVB7UaJayVr4ENF2jVC42AJjrzIbEpgonSvUA3uch7hfAmaPs8b07Pr1eH80M3tF4xd5EIoInT9gS50ktfJnt8IyMg4pxqIActVfGNSLK-IEqyBmC1-Pywda1M41YjnBQ_wV2c1BBuF";
    private final VkApiClient vk;
    private final UserActor actor;


    public VkRepository() {
        TransportClient transportClient = new HttpTransportClient();
        vk = new VkApiClient(transportClient);
        actor = new UserActor(APP_ID, CODE);
    }

    public Group firstGetIDpubli() throws ClientException, ApiException {
        String groupFirst = "Уральский федеральный университет | УрФУ";
        return vk.groups()
                .search(actor, groupFirst)
                .execute()
                .getItems()
                .get(0);
    }
    public Group secondGetIDpublic() throws ClientException, ApiException {
        String groupFirst = "СОЮЗ СТУДЕНТОВ ИРИТ-РТФ УрФУ";
        return vk.groups()
                .search(actor, groupFirst)
                .execute()
                .getItems()
                .get(0);
    }

    public UserFull getId(Group group1,Group group2, String name) throws ClientException, ApiException, InterruptedException {
        String name1[] = name.split(" ");
        if (name1.length == 2){
            String refersName = String.format("%s %s", name1[1], name1[0]);
            UserFull result = vk.users()
                    .search(actor)
                    .q(name)
                    .q(refersName)
                    .fields(Fields.CITY, Fields.BDATE, Fields.SEX)
                    .groupId(group1.getId())
                    .groupId(group2.getId())
                    .count(1)
                    .execute()
                    .getItems()
                    .stream()
                    .findFirst()
                    .orElse(null);
            Thread.sleep(250);
            return result;
        }


        UserFull result = vk.users()
                .search(actor)
                .q(name)
                .fields(Fields.CITY, Fields.BDATE, Fields.SEX)
                .groupId(group1.getId())
                .groupId(group2.getId())
                .count(1)
                .execute()
                .getItems()
                .stream()
                .findFirst()
                .orElse(null);
        Thread.sleep(250);


        return result;

    }
    public String getCityOfStudent(UserFull result) throws ClientException, ApiException, InterruptedException {


        if (result == null){
            return null;

        }
        else {
            if (result.getCity()== null) {
                return null;

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
    public SortedMap<String, String> report(Set<String> students) throws ClientException, ApiException, InterruptedException {
        Group group1 = firstGetIDpubli();
        Group group2 = secondGetIDpublic();
        for (var name : students){

            UserFull user =  getId(group1,group2,  name);

            String line = null;
            Person person= null;
            if (user == null){
                String nameVk = String.format("Студент < %s > не найден в ВК ", name);
                person = new Person(nameVk,  getSex(user) , getCityOfStudent(user), getBdate(user) );

            }

            else{
                person = new Person(name, getSex(user) , getCityOfStudent(user), getBdate(user) );


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