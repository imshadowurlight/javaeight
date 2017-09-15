import java.time.LocalDate;

/**
 * 方法引用的四种类型
 *  1. 构造器引用
 *  2. 类的静态方法引用
 *  3. 类的普通方法引用
 *  4. 实例的普通方法引用
 * */
public class Person {


    String name;
    LocalDate birthday;

    public LocalDate getBirthday() {
        return birthday;
    }

    public Person(String name, LocalDate birthday) {
        this.name = name;
        this.birthday = birthday;
    }


    public static int compareByAge(Person a, Person b) {
        return a.birthday.compareTo(b.birthday);
    }

    @Override
    public String toString() {
        return this.name;
    }

}
