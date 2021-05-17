
public class App {

    static final int size = 10000000;
    static final int h = size / 2;
    static float[] arr = new float[size];

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        method1();
        method2();
    }

    /**
     * <p>
     * Метод производит математические операции function над массивом в одном потоке
     * </p>
     */
    public static void method1() {

        for (int i = 0; i < arr.length; i++) {

            arr[i] = 1;

        }

        long a = System.currentTimeMillis();

        function(arr);

        System.out.println("Время выполнения в одном потоке ms:");
        System.out.println(System.currentTimeMillis() - a);

    }

    /**
     * *
     * <p>
     * Метод производит математические операции @function над массивом в двух
     * потоках
     * </p>
     * 
     * @throws InterruptedException
     */
    public static void method2() throws InterruptedException {

        for (int i = 0; i < arr.length; i++) {

            arr[i] = 1;

        }

        float[] a1 = new float[h];
        float[] a2 = new float[h];

        long a = System.currentTimeMillis();

        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);

        Thread thread1 = new Thread(() -> {

            function(a1);

        });

        Thread thread2 = new Thread(() -> {

            function(a2);

        });

        thread1.start();
        thread2.start();
       
        thread1.join();
        thread2.join();

        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);

        System.out.println("Время выполнения в двух потоках ms:");
        System.out.println(System.currentTimeMillis() - a);

    }

    
    /**
     * <p>
     * Собственно сами тестовые математические операции над массивом
     * </p>
     * 
     * @param arr массив над которам производим математические опреации
     */
    private static void function(float[] arr) {

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }
}
