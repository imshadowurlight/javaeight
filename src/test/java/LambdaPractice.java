import java.time.LocalDate;
import java.util.*;

/*
* 以下资料摘自官方+网上资料
* 伸手党,勿怪
* ide:idea
* jdk:1.8
* */
public class LambdaPractice {

    /**
     * 最简单的Lambda表达式可由逗号分隔的参数列表、->符号和语句块组成
     * 举个栗子:
     * */
    public static void test1(){
        Arrays.asList( "11", "12", "13" ).forEach( e -> System.out.println( e ) );
    }

    /**
     * 参数e的类型是由编译器根据上下文推理得出的，你也可以显式指定该参数的类型
     * */
    public static void test2(){
        Arrays.asList( "21", "22", "23" ).forEach( (String e) -> System.out.println( e ) );
    }

    /**
     * 注意,第三部分时语句块,只是省略{};同if中只有一个语句时省略{},同理
     * 当然,作为习惯,我个人是绝对不省略的
     * 但是别人省略了,也要看的懂,你说是吧
     * */
    public static void test3(){
        Arrays.asList( "31", "32", "33" ).forEach( (String e) -> {
            System.out.println( e );
            System.out.println( e+1 );
        } );
    }

    /**
     * Lambda表达式可以引用类成员和局部变量（会将这些变量隐式得转换成final的）
     *
     * 所谓的隐式转换是指真的变成了final吗?
     * 举个栗子:
     *  当变量使用final修饰时,变量指针不可以指向新的内存地址
     *  如下:解开注释会出现编译报错~
     * 这个特性感觉有点坑人的味道,敲代码的时候忘了这个的话估计想哭的新都有了
     * */
    public static void test4(){
        String comma = ",";
        Arrays.asList( "41", "42", "43" ).forEach(( String e ) -> {
            System.out.print( e + comma );
         } );
//        comma = new String(",,,");
//        comma = ",,,";
    }

    /**
     * 函数式接口+lambda
     * */
    public static void test5(){
        Converter<String, Integer> converter = (ziFuChuan) -> Integer.valueOf(ziFuChuan);//这里的lamda想当于方法的impl了,就是把字符串转数字
        System.out.println( converter.convert("123") instanceof Integer );
    }

    /**
     * 函数式接口+lambda
     * */
    public static  void test6(){
        Converter<String, Integer> converter = new Converter<String, Integer>() {//不适用lambda的来进行方法的impl
            @Override
            public Integer convert(String ziFuChuan) {
                return Integer.valueOf(ziFuChuan);
            }
        };
        System.out.println( converter.convert("123") instanceof Integer );
        converter.defaultconvert("guang");  //使用实例调用默认方法(咦,这跟一般的方法没有区别啊,只是省略掉了接口的实现,直接在接口处写方法体)
        converter.defaultconvert2("guang"); //使用实例调用默认方法(咦,这跟一般的方法没有区别啊,只是省略掉了接口的实现,直接在接口处写方法体)
        Converter.staticConvert1();     //调用静态方法(咦,这跟一般的静态方法没有区别啊,只是省略掉了接口的实现,直接在接口处写方法体)
        Converter.staticConvert2();     //调用静态方法(咦,这跟一般的静态方法没有区别啊,只是省略掉了接口的实现,直接在接口处写方法体)
    }

    /**
     * 方法引用--类的静态方法引用
     * */
    public static void test7(){
        Person[] persons = new Person[]{
                new Person("003", LocalDate.of(2016,9,1)),
                new Person("001", LocalDate.of(2016,2,1)),
                new Person("002", LocalDate.of(2017,3,1)),
                new Person("004", LocalDate.of(2016,12,1))
        };

        // 假设我们有一个Person数组，并且想对它进行排序，这时候，我们可能会这样写
        // 使用匿名类,也就是对Comparator接口进行实现了
        Arrays.sort(persons, new Comparator<Person>() {
            @Override
            public int compare(Person a, Person b) {
                return a.getBirthday().compareTo(b.getBirthday());
            }
        });

        for(Person p:persons){
            System.out.println(p);
        }
        System.out.println("====");

        //这里，我们首先要注意Comparator接口是一个函数式接口，因此我们可以使用Lambda表达式，而不需要定义一个实现Comparator接口的类，并创建它的实例对象，传给sort方法。
        //1. 使用lambda表达式解决
        Person[] persons_one = new Person[]{
                new Person("003", LocalDate.of(2016,9,1)),
                new Person("001", LocalDate.of(2016,2,1)),
                new Person("002", LocalDate.of(2017,3,1)),
                new Person("004", LocalDate.of(2016,12,1))
        };
        Arrays.sort(persons_one, (Person a, Person b) -> {
            return a.getBirthday().compareTo(b.getBirthday());
        });
        for(Person p:persons_one){
            System.out.println(p);
        }
        System.out.println("====");


        //引用方法--类的静态方法引用
        Person[] persons_two = new Person[]{
                new Person("003", LocalDate.of(2016,9,1)),
                new Person("001", LocalDate.of(2016,2,1)),
                new Person("002", LocalDate.of(2017,3,1)),
                new Person("004", LocalDate.of(2016,12,1))
        };
        Arrays.sort(persons_two, Person::compareByAge);
        for(Person p:persons_two){
            System.out.println(p);
        }

    }

    /**
     *  方法引用--构造器引用
     *  搞了半天,还是没有看懂,擦咧
     * */
    public static void test8(){
        CatFactory catFactory = Cat :: new;
        Cat oldCat = catFactory.create();
        System.out.println("千年的老猫妖实际年龄: " + oldCat.getAge());
    }

    /**
     * 方法引用--类的普通方法引用
     * */
    public static void test10(){

    }

    /**
     * 方法引用--实例的普通方法引用
     * */
    public static void test11(){

    }

    /**
     * 重复注解: 在一个地方可以多次使用同一个注解
     *
     * */


    public static void main(String[] args) {
        test1();
        System.out.println("=====================test1 end");
        test2();
        System.out.println("=====================test2 end");
        test3();
        System.out.println("=====================test3 end");
        test4();
        System.out.println("\n=====================test4 end");
        test5();
        System.out.println("=====================test5 end");
        test6();
        System.out.println("=====================test6 end");
        test7();
        System.out.println("=====================test7 end");
        test8();
        System.out.println("=====================test8 end");
    }


}
