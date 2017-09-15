import java.util.Arrays;

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
    }


}
