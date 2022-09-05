package hello.core.member;

public class Member {
    private  Long id;
    private  String name;
    private  Grade grade;
 // 컨스트럭터 , 게터세터 ctrl + insert  - 클래스 패키지 생성등
    public Member(Long id, String name, Grade grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }
}
