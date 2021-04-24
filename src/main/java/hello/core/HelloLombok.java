package hello.core;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args){
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("asdfas");

        System.out.println("helloLombok = " + helloLombok);
        //@ToString도 있습니다

        String name = helloLombok.getName();
        System.out.println("name = " + name);
        // 롬복은 getter, setter 만들어준다.

        new HelloLombok("kim", 15);
        //@AllArgsConstructor.

        HelloLombokBuilder test5 = new HelloLombokBuilder().age(15).name("안녕");
        System.out.println("test5 = " + test5);
        System.out.println("수정 전 name = " + test5.name);
        System.out.println("age = " + test5.age);
        test5.name = "빌더";
        System.out.println("수정 후 name = " + test5.name);

        //@Builder

    }
}
