/**数据类型转换器
 * 函数式接口:
 *   格式
 *   只有一个方法
 *   可以有默认方法,且默认方法不被计算
 *   可以有静态方法,且静态方法不被计算
 *   需要一个显式的注解来声明
 * */
@FunctionalInterface
public interface Converter<F, T>  {
    /**转换参数类型
     * param from 类型为F
     * return T 返回类型为T
     * */
    T convert(F from);

    default void defaultconvert( String name ){
        System.out.println("i m your greatest master "+ name);
    }

    default void defaultconvert2( String name ){
        System.out.println("u are my greatest master "+ name);
    }

    static void staticConvert1(){
        System.out.println("i want jingjing,and don't ask who's jingjing 11111");
    }

    static void staticConvert2(){
        System.out.println("i want jingjing,and don't ask who's jingjing 22222");
    }
}
