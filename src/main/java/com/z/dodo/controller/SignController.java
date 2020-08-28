package com.z.dodo.controller;

import com.z.dodo.bean.Data;
import com.z.dodo.bean.Info;
import com.z.dodo.bean.SignRequest;
import com.z.dodo.signe.SignUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/sign")
public class SignController {

    @PostMapping(value = "/dosign")
    public String doSign(@RequestBody SignRequest signRequest){
        System.out.println(" => "+signRequest.toString());

        String result = "";
        try{
            if(signRequest.func.equalsIgnoreCase("makeSignWithSHA256")){
                result = SignUtil.makeSignWithSHA256(signRequest.raw);
            }
            if(signRequest.func.equalsIgnoreCase("makeSignWithSHA1")){
                result = SignUtil.makeSignWithSHA1(signRequest.raw);
            }
        }catch (Exception e){
            result = e.toString();
        }
        return result;
    }

}
