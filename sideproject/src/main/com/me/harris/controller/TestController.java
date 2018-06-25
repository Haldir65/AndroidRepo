package com.me.harris.controller;

import com.me.harris.gen.User;
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
//        InputStream inputStream = request.getInputStream();
//        User user = User.parseFrom(inputStream);
//        inputStream.close();
//        System.out.println(user);
    }

    @RequestMapping("download")
    public void download(HttpServletResponse response) throws IOException {
        User user = User.newBuilder()
                .setEmailAddress("example@example.com")
                .setFirstName("realFirstName")
                .setLastName("realLastName")
                .setSkillsValue(1,2)
                .build();

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
        response.setContentType("application/x-protobuf");
        OutputStream outputStream = response.getOutputStream();
        user.writeTo(outputStream);
        outputStream.flush();
        outputStream.close();
    }
}
