package hello.core.singleton;

public class StatefulService {

//    private int price; //상태를 유지하는 필드 10000 -> 20000
    // 필드대신에!!! 공유되지 않는 지역변수, 파라미터, ThreadLocal등을 사용해야 한다.

    public int order(String name, int price){
        System.out.println("name = " + name+" price = " + price);
        return price; //여기가 문제!
    }
//    public void getPrice() {
//        return price;
//    }
}
