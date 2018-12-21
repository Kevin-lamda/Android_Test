package ssw.com.myapplication.other;

public class CMakeListsHello {
    static {
       // System.loadLibrary("native-lib");
        System.loadLibrary("native-lib");
    }
    public native String stringFromJNI();
}
