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
import com.vk.api.sdk.objects.users.User;
import src.Person.Person;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class VkRepository {
    HashMap<String, ArrayList<String>> studentInfo = new HashMap<String, ArrayList<String>>();
    private final int APP_ID = 0;
    private final String CODE = "";
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
    public String getStudent(Group group, User user ) throws ClientException, ApiException {
        return  vk.groups().isMember(actor, group.getId().toString())
                .userId(user.getId())
                .execute()
                .toString()
                ;

    }
    public User getId(Group group, String name) throws ClientException, ApiException {

        User result = vk.users()
                .search(actor)
                .q(name)
                .groupId(group.getId())
                .execute()
                .getItems()
                .get(0);
        if (result == null){
            return null;
        }
        else {
            return result;
        }

    }
    public String getCity(Group group, String name, User user) throws ClientException, ApiException {
        if (user == null){
            return "";
        }
        City result = vk.users()
                    .search(actor)
                    .q(name)
                    .groupId(group.getId())
                    .execute()
                    .getItems()
                    .get(0).getCity();
        if (result == null){
            return "нет данных";
        }
        else {
            return result.getTitle();
        }

    }
    public String getBdate(Group group, String name, User user) throws ClientException, ApiException {
        if (user == null){
            return "";
        }
        String result = vk.users()
                    .search(actor)
                    .q(name)
                    .groupId(group.getId())
                    .execute()
                    .getItems()
                    .get(0).getBdate();
        if (result == null){
            return "нет данных";
        }
        else {

            return  new SimpleDateFormat("dd.MM.yyyy").format(result);
        }



    }
    public String getSex(Group group, String name, User user) throws ClientException, ApiException {
        if (user == null){
            return "";
        }
        Sex result = vk.users()
                .search(actor)
                .q(name)
                .groupId(group.getId())
                .execute()
                .getItems()
                .get(0).getSex();
        if (result == null){
            return "нет данных";
        }
        else {
            return result.toString();
        }



    }
    public void report(String name) throws ClientException, ApiException {

        ArrayList<String> info = new ArrayList<>();
        Group group = gitIDpublic();
        User user =  getId(group, name);
        String line = null;
        Person person= null;

        person = new Person(name, getSex(group, name, user) ,getCity(group, name, user), getBdate(group, name, user) );
        line = person.toString(person);
        info.add(line);
        studentInfo.put(name, info);

    }
    public void getReport(String name) throws ClientException, ApiException {
        for(var i : studentInfo.keySet()){

            if (i.contains(name)){


                for (var j : studentInfo.get(i)){
                    System.out.println(j);
                }

            }
        }
    }


}
