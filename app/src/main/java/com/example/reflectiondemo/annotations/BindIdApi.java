package com.example.reflectiondemo.annotations;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 执行具体绑定控件逻辑的api
 * Created by Administrator on 2018/5/25.
 */

public class BindIdApi {

    /*
    * 绑定id
    * */
    public static void bindId(Activity obj){
        Class<? extends Activity> aClass = obj.getClass();
        //使用反射调用setContentView
        if (aClass.isAnnotationPresent(BindId.class)){
            //得到这个类的BindId注解
            BindId mId = aClass.getAnnotation(BindId.class);
            //得到注解的值
            int id = mId.value();
            try {
                Method method = aClass.getMethod("setContentView", int.class);
                method.setAccessible(true);
                method.invoke(obj,id);
            }catch (NoSuchMethodException e){
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        //使用反射调用findViewById
        Field[] fields = aClass.getDeclaredFields();
        for (Field field:fields){
            if (field.isAnnotationPresent(BindId.class)){
                BindId bindId = field.getAnnotation(BindId.class);
                int id = bindId.value();
                try{
                    Method findViewById = aClass.getMethod("findViewById", int.class);
                    findViewById.setAccessible(true);
                    Object view = findViewById.invoke(obj, id);
                    field.setAccessible(true);
                    field.set(obj,view);
                }catch (NoSuchMethodException e){
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void bindOnClick(final Activity obj){
        Class<? extends Activity> aClass = obj.getClass();
        //获取当前activity的所有方法,包括私有
        Method[] methods = aClass.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++) {
            final Method method = methods[i];
            if (method.isAnnotationPresent(OnClick.class)){
                //得到这个方法的onClick注解
                OnClick mOnClick = method.getAnnotation(OnClick.class);
                //得到注解的值
                int[] value = mOnClick.value();
                for (int j = 0; j < value.length; j++) {
                    final View view = obj.findViewById(value[j]);
                    view.setOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            //反射指定的点击方法
                            try {
                                //私有方法需要设置true才能访问
                                method.setAccessible(true);
                                method.invoke(obj,view);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }
    }

}
