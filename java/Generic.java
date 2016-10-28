import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Diamond extends Shape
 * Square extends Diamond
 * create in 2016年10月28日
 * @author wangpeng
 */
public class Generic {

    /**
     * 数组的协变性
     * 虽然传入的实际参数可能是Shape的子类数组，但是编译不会报错。只有在运行时报ArrayStoreException
     * @param arr
     */
    public static void testCovariation(Shape[] arr) {
        arr[0] = new Shape();
        arr[1] = new Diamond();
        arr[2] = new Square();
    }

    /**
     * 为了解决类型不能动态根据实例来确定的缺点，引入了“通配符泛型”，使得一个参数可以用来表示一组实例化后的模板。其中:
     * “?”代表未知类型
     * extends 关键字声明了类型的上界，表示参数化的类型可能是所指定的类型，或者是此类型的子类
     */
    public static void testUpper(List<? extends Diamond> upperBound) {
        // 报错原因：实际调用时传入的参数可能是Diamond的某个子类的参数化类型
        // 例如：可能 upperBound实际上是 List<Square>
        upperBound.add(new Diamond());
        Shape s = upperBound.get(0);
        Diamond d = upperBound.get(0);
        // 编译报错：? extends Diamond：表示可能是Diamond类，或者是Diamond类的子类
        // Diamond类的子类不一定都是Square，所以报错。
        // Diamond类的子类一定都是Diamond，也一定都是Shape类，所以上面两行是正确的。
        Square sq = upperBound.get(0);
    }

    /**
     * super 关键字声明了类型的下界，表示参数化的类型可能是所指定的类型，或者是此类型的超类，直至Object
     * @param lowerBound
     */
    public static void testLower(List<? super Diamond> lowerBound) {
        // 传入的实际参数可能是Diamond的父类，如List<Shape> list
        Diamond d2 = lowerBound.get(0);
        // 编译报错：? super Diamond:表示可能是Diamond类，或者Diamond的超类，直至Object
        // 如果传入的类为Diamond那么Shape不是Diamond，故编译报错。但Diamond，Square一定是属于Diamond也属于Diamond的父类，所以下面两行正确
        lowerBound.add(new Shape());
        lowerBound.add(new Diamond());
        lowerBound.add(new Square());
    }

    public static void main(String[] args) {
        List<Shape> fatherList = new ArrayList<>();
        List<Diamond> dList = new ArrayList<>();
        List<Square> sonList = new ArrayList<>();
        //? extends Diamond:只能传递Diamond，以及Diamond的子类
        // 故：下面第一行编译报错，接下来两行正确
        testUpper(fatherList);
        testUpper(dList);
        testUpper(sonList);

        // ? supper Diamond:只能传递Diamond，以及Diamond的超类
        // 故：下面两行正确，最后一行编译报错
        testLower(fatherList);
        testLower(dList);
        testLower(sonList);
    }
}
