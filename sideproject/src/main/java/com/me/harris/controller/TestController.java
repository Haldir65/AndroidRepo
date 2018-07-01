package com.me.harris.controller;


import com.me.harris.gen.UserProfileRequest;
import com.me.harris.gen.UserProfileResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Controller
@RequestMapping("/pbtest")
public class TestController {
    @RequestMapping("upload")
    public void upload(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("request incoming ");
        InputStream inputStream = request.getInputStream();

        try {
            UserProfileRequest user1 = UserProfileRequest.parseFrom(inputStream);
            String username = user1.getName();
            int level = user1.getLevel();
            System.out.println("userName = "+username+"  userlevel "+level);


            UserProfileResponse user = UserProfileResponse.
                    newBuilder().
                    setName("Jane Smith").
                    addSkills("poetry").
                    addSkills("orchestry").
                    build();

            System.out.println("we have successfully process the data");
            response.setContentType("application/x-protobuf");
            OutputStream outputStream = response.getOutputStream();
            user.writeTo(outputStream);
            outputStream.flush();
            outputStream.close();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            inputStream.close();

        }

//        User user = User.parseFrom(inputStream);

    }

    @RequestMapping("download")
    public void download(HttpServletResponse response) throws IOException {
        UserProfileResponse user = UserProfileResponse.
                newBuilder().
                setName("小明").
                addSkills("java").
                addSkills("lura").
                build();
//        User user = User.newBuilder()
//                .setEmailAddress("example@example.com")
//                .setFirstName("realFirstName")
//                .setLastName("realLastName")
//                .setSkillsValue(1,2)
//                .build();

//        Person john = Person.newBuilder()
//                .setId(1234)
//                .setName("John Doe")
//                .setEmail("jdoe@example.com")
//                .addPhone(
//                        Person.PhoneNumber.newBuilder()
//                                .setNumber("555-4321")
//                                .setType(Person.PhoneType.HOME))
//                .build();
//        AddressBook addressBook = AddressBook.newBuilder().addPerson(john).build();
        System.out.println("time to send out response");
        response.setContentType("application/x-protobuf");
        OutputStream outputStream = response.getOutputStream();
        user.writeTo(outputStream);
        outputStream.flush();
        outputStream.close();
    }
}
