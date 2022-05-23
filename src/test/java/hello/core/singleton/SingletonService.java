package hello.core.singleton;

public class SingletonService {

    // 1. static 영역에 객체를 딱 1개만 생성해준다.
    private static final SingletonService instance = new SingletonService();

    // 2. public으로 알아서 객체 인스턴스가 필요할 경우 오직 static getInstance 메소드를 통해서만 조회할 수 있도록 허용
    public static SingletonService getInstance() {
        return instance;
    }

    // 3. 생성자를 private로 선언하여 외부에서 new 키워드를 사용한 객체 생성을 막는다.
    private SingletonService() { }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }

}
