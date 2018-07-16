package com.mydemo.Base.MuchThread;

import android.util.Log;
import android.util.TimeUtils;
import android.view.View;
import android.widget.TextView;

import com.mydemo.Base.Base2Activity;
import com.mydemo.R;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by HaoPz on 2018/7/12.
 */

public class MuchThreadActivity extends Base2Activity {
    private TextView baseKnowledgeText;
    private TextView toolBarTextView;

    @Override
    public void setDate() {

    }

    @Override
    public void setView(View contentView) {
        toolBarTextView = (TextView) contentView.findViewById(R.id.toolBar);
        toolBarTextView.setText("Androdi 多线程");

        baseKnowledgeText = (TextView) findViewById(R.id.item_text);
        baseKnowledgeText.setText("多线程");

//        MyThread myThread = new MyThread();
//        Thread mThread = new Thread(myThread);
//        mThread.start();
//        try {
//            TimeUnit.MILLISECONDS.sleep(10);
//            myThread.cancle();
//            Log.i("-->", String.valueOf(myThread.i) + "    ：" + String.valueOf(myThread.goOn));
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//            mThread.interrupt();
//        }


        final MyThread1 myThread1 = new MyThread1();
        final Thread mThread1 = new Thread(myThread1);

        baseKnowledgeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mThread1.start();
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                    Log.i("-->",Thread.currentThread().getName()+":"+ myThread1.count );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    mThread1.interrupt();
                }
            }
        });
    }

    @Override
    protected View getContentView() {
        return inflateView(R.layout.activity_much_thread);
    }

//    public class MyThread implements Runnable {
//
//        private int i = 0;
//        private volatile boolean goOn = true;
//
//        @Override
//        public void run() {
//            while (goOn) {
//                i++;
//                Log.i("-->", String.valueOf(i));
//            }
//        }
//
//        public void cancle() {
//            goOn = false;
//        }
//    }

    class MyThread1 implements Runnable {

        public volatile int count = 0;

        @Override
        public void run() {
            Lock lock = new ReentrantLock();
            Condition condition = lock.newCondition();

            try {
                lock.lock();
                while (count <= 10) {
                    count += 1;
                    Log.i("-->", Thread.currentThread().getName()+":"+ String.valueOf(count));
                }
                condition.signal();
            } catch (Exception ex) {

            } finally {
                lock.unlock();
                Log.i("-->", "线程结束");
            }

        }
    }
}

