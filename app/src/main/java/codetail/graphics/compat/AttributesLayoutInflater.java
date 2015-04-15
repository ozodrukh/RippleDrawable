package codetail.graphics.compat;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.xmlpull.v1.XmlPullParser;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Wrapper of {@link LayoutInflater}
 *
 * Because Android doesn't allows to override <a href="http://goo.gl/91EJUX">loadDrawable()</a>
 * method, we need to checkout for custom attribute after newly created View, if exists
 * set custom {@link android.graphics.drawable.Drawable}
 *
 * If you want provide custom attribute, easily extend this class, and override
 * {@link #onViewCreated(View, AttributeSet)} method, do not forget call super method
 */
public class AttributesLayoutInflater extends LayoutInflater {
    private static final String[] sClassPrefixList = {
            "android.widget.",
            "android.webkit."
    };

    private boolean mPrivateFactoryWrapped = false;
    private Field mConstructorArgs = null;

    protected AttributesLayoutInflater(Context context) {
        super(context);
    }

    protected AttributesLayoutInflater(LayoutInflater original, Context newContext) {
        super(original, newContext);
    }

    @Override
    public void setFactory(Factory factory) {
        if(factory instanceof WrappedFactory){
            super.setFactory(factory);
        }else{
            super.setFactory(new WrappedFactory(factory));
        }
    }

    @Override
    public void setFactory2(Factory2 factory) {
        if(factory instanceof WrappedFactory2) {
            super.setFactory2(factory);
        }else{
            super.setFactory2(new WrappedFactory2(factory));
        }
    }

    private void setPrivateFactory(){
        // if we already did, we pass to next step
        if(mPrivateFactoryWrapped) return;

        final Context base = getContext();

        if(!(base instanceof Factory2)){
            mPrivateFactoryWrapped = true;
        }

        final Method setPrivateFactoryMethod = findMethodByName(LayoutInflater.class,
                "setPrivateFactory", Factory2.class);

        invokeMethod(this, setPrivateFactoryMethod, new WrappedPrivateFactory2((Factory2) base));

        mPrivateFactoryWrapped = true;
    }

    @Override
    public View inflate(XmlPullParser parser, ViewGroup root, boolean attachToRoot) {
        setPrivateFactory();

        return super.inflate(parser, root, attachToRoot);
    }

    @Override
    protected View onCreateView(String name, AttributeSet attrs) throws ClassNotFoundException {
        View view = null;

        for (String prefix : sClassPrefixList) {
            try {
                view = createView(name, prefix, attrs);
            } catch (ClassNotFoundException e) {
                // In this case we want to let the base class take a crack
                // at it.
            }
        }

        if(view == null){
            view = onCreateView(name, attrs);
        }

        if(view != null) {
            onViewCreated(view, attrs);
        }

        return view;
    }

    /**
     * Create a new view of given class {@code name}, called if no one haven't
     * create it, so if this method falls {@link #onViewCreated(View, AttributeSet)}
     * will not be called
     *
     * @param parent Parent of the view you will create (need to to extract layout params)
     * @param view
     * @param name Name of class view to create
     * @param context Context of creation
     * @param attrs View's attributes
     *
     * @return the created view
     */
    protected View createCustomView(View parent, View view, String name, Context context,
                                    AttributeSet attrs){
        // I by no means advise anyone to do this normally, but Google have locked down access to
        // the createView() method, so we never get a callback with attributes at the end of the
        // createViewFromTag chain (which would solve all this unnecessary rubbish).
        // We at the very least try to optimise this as much as possible.
        // We only call for customViews (As they are the ones that never go through onCreateView(...)).
        // We also maintain the Field reference and make it accessible which will make a pretty
        // significant difference to performance on Android 4.0+.

        if (view == null && name.indexOf('.') > -1) {
            if (mConstructorArgs == null)
                mConstructorArgs = findFieldByName(LayoutInflater.class, "mConstructorArgs");

            final Object[] mConstructorArgsArr = (Object[]) getValue(this, mConstructorArgs);
            final Object lastContext = mConstructorArgsArr[0];
            // The LayoutInflater actually finds out the correct context to use. We just need to set
            // it on the mConstructor for the internal method.
            // Set the constructor ars up for the createView, not sure why we can't pass these in.
            mConstructorArgsArr[0] = context;
            setValue(this, mConstructorArgs, mConstructorArgsArr);
            try {
                view = createView(name, null, attrs);
            } catch (ClassNotFoundException ignored) {
            } finally {
                mConstructorArgsArr[0] = lastContext;
                setValue(this, mConstructorArgs, mConstructorArgsArr);
            }
        }
        return view;
    }

    /**
     * Called after view has been created, here you can customize view
     * based on his custom attributes
     *
     * @param view Created view
     * @param attrs Attributes of the view
     */
    protected void onViewCreated(View view, AttributeSet attrs){

    }

    @Override
    public LayoutInflater cloneInContext(Context newContext) {
        return new AttributesLayoutInflater(this, newContext);
    }

    protected static Field findFieldByName(Class<?> clazz, String name){
        try {
            Field field =  clazz.getField(name);
            field.setAccessible(true);
            return field;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        return null;
    }

    protected static Method findMethodByName(Class<?> clazz, String name, Class<?> args){
        try {
            Method method = clazz.getMethod(name, args);
            method.setAccessible(true);
            return method;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected static Object invokeMethod(Object object, Method method, Object... args){
        try {
            return method.invoke(object, args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected static Object getValue(Object object, Field field){
        try {
            return field.get(object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }


    protected static void setValue(Object object, Field field, Object value){
        try {
            field.set(object, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }



    protected class WrappedFactory implements Factory{

        private Factory mBase;

        public WrappedFactory(Factory base) {
            mBase = base;
        }

        public Factory getBase() {
            return mBase;
        }

        @Override
        public View onCreateView(String name, Context context, AttributeSet attrs) {
            View view = mBase.onCreateView(name, context, attrs);

            if(view != null){
                onViewCreated(view, attrs);
            }

            return view;
        }
    }

    protected class WrappedFactory2 implements Factory2 {

        private Factory2 mBase;

        protected WrappedFactory2(Factory2 base){
            mBase = base;
        }

        @Override
        public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
            View view = mBase.onCreateView(parent, name, context, attrs);

            if(view != null){
                onViewCreated(view, attrs);
            }

            return view;
        }

        @Override
        public View onCreateView(String name, Context context, AttributeSet attrs) {
            View view = mBase.onCreateView(name, context, attrs);

            if(view != null){
                onViewCreated(view, attrs);
            }

            return view;
        }

        public Factory2 getBase() {
            return mBase;
        }
    }


    private class WrappedPrivateFactory2 extends WrappedFactory2{

        protected WrappedPrivateFactory2(Factory2 base) {
            super(base);
        }

        @Override
        public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
            final View view = createCustomView(parent, getBase().onCreateView(name, context, attrs),
                    name, context, attrs);

            if(view != null){
                onViewCreated(view, attrs);
            }

            return view;
        }
    }
}