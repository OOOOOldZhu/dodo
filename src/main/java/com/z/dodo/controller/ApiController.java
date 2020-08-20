package com.z.dodo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.*;
import com.mongodb.client.MongoIterable;
import com.z.dodo.bean.Constans;
import com.z.dodo.tool.Log;
import com.z.dodo.bean.Data;
import com.z.dodo.bean.Info;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;

@RestController
@RequestMapping(value = "/api")
public class ApiController {

    @GetMapping(value = "/test")
    public Data get(Info info) {
        return dealWith(info);
    }

    @PostMapping(value = "/test")
    public Data post(Info info) {
        return dealWith(info);
    }

    /**
     * @param info  用户的信息
     * @return 处理用户信息的结果
     */

    private Data dealWith(Info info) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String req = objectMapper.writeValueAsString(info);
            Log.d("服务器接收到的请求 : "+req);
        } catch (Exception e) {
            Log.d(e.toString());
        }
        String nickname = info.getNickname();
        String gender = info.getGender();
        int age = info.getAge();

        if (StringUtils.isEmpty(nickname)) {
            return new Data().setCode(-1).setResult("Nickname can not be blank.");
        }

        if (StringUtils.isEmpty(gender)) {
            return new Data().setCode(-2).setResult("Gender can not be blank.");
        } else if (gender.equals("male")) {
            gender = "boy";
        } else if (gender.equals("female")) {
            gender = "girl";
        } else {
            return new Data().setCode(-2).setResult("Gender is not legal");
        }

        if (age < 0) {
            return new Data().setCode(-3).setResult("Age can not be less than zero.");
        }
//        try{
//            saveUser(info);
//        }catch (Exception e){
//            Log.d("数据库操作错误 : " + e);
//            return new Data().setCode(500).setResult(e.toString());
//        }
        String result = nickname + "'s age is " + age + " year old, " + "he(she) is a " + gender;
        return new Data().setCode(0).setResult(result);
    }

    private void saveUser(Info info) {
        /* 建立与mongodb数据库的连接，可指定参数，如：MongoClient client = new MongoClient(“localhost”,27017); */
        MongoClient client = new MongoClient(Constans.url, Constans.port);
        MongoClientOptions mongoClientOptions = client.getMongoClientOptions();

        /* 通过listDatabaseNames()方法可以返回一个mongo遍历器，此处还可以使用getDatabaseNames()方法，他返回的是一个类型为String的list集合，*/
        MongoIterable<String> listDatabaseNames = client.listDatabaseNames();
        Iterator<String> it = listDatabaseNames.iterator();
//        while (it.hasNext()) {
//            //Log.d("表名 : = "+ it.next());
//        }
        /* 新建数据库实例，命名为render_system,有则使用已有的数据库，没有则准别新建 */
        DB render_system = client.getDB("render_system");

        /* 新建集合命名为user_info,如果该集合存在，则使用。否则新建 */
        DBCollection user_info = render_system.getCollection("user_info");

        Log.d("user_info.size() = "+ user_info.count());
        DBObject query = new BasicDBObject();
        query.put("mickname",info.getNickname());

        DBObject newPerson = new BasicDBObject();
        newPerson.put("mickname", info.getNickname());
        newPerson.put("age", info.getAge());
        newPerson.put("gender", info.getGender());

        DBObject andModify = user_info.findAndModify(query, newPerson);

        if(andModify == null){
            WriteResult insert = user_info.insert(newPerson);
        }else{
            //user_info.update(query,newPerson);
        }
//        /*创建DBObject对象，通过该对象来向集合中添加记录 */
//        DBObject doc = new BasicDBObject();
//        /* doc.put(key,value)方法来实现向对象添加值，以下put完后生成的json格式为:{"name":"gwb","age":"20"} */
//        doc.put("name", "gwb");
//        doc.put("age", "20");
//        user_info.insert(doc);
        client.close();
    }
}