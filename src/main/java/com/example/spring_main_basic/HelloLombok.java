package com.example.spring_main_basic;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HelloLombok {
    private String name;
    private int age;

    public static void main(String[] args) {

        HelloLombok helloLombok = new HelloLombok();

        helloLombok.setName("shinseungyeol");
        helloLombok.setAge(30);

        System.out.print(helloLombok);


    }
}
