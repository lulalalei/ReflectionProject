package com.example.annotationapi;

import android.app.Activity;
import android.view.View;

import com.example.compile.ProxyInfo;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 实现帮助注入的类
 * Created by Administrator on 2018/5/28.
 */

public class AnnotationHelp {

    /*
    * 用来缓存反射出来的类,节省每次都去反射引起的性能消耗
    * */
    static final Map<Class<?>,Constructor<?>> BINDING=new LinkedHashMap<>();
    public static void inject(Activity o){
        inject(o,o.getWindow().getDecorView());
    }

    private static void inject(Activity host, View root) {
        String classFullName = host.getClass().getName() + ProxyInfo.ClassSuffix;
        try {
            Constructor<?> constructor = BINDING.get(host.getClass());
            if (constructor==null){
                Class proxy = Class.forName(classFullName);
                constructor=proxy.getDeclaredConstructor(host.getClass(),View.class);
                BINDING.put(host.getClass(),constructor);
            }
            constructor.setAccessible(true);
            constructor.newInstance(host,root);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
