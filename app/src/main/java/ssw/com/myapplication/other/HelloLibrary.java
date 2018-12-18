package ssw.com.myapplication.other;

public class HelloLibrary {
    static {
        //ndk-build，这里对应 Android.mk 里的 LOCAL_MODULE := NDKSample
        //CMake，这里对应 CMakeLists.txt 里的 add_library NDKSample
        System.loadLibrary("NDKSample");
    }
    //使用 native 关键字指示以原生代码形式实现的方法
    public native String sayHello();
}
