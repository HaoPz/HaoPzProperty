package com.mydemo.Base.MuchThread;

import android.util.Log;
import android.util.TimeUtils;
import android.view.View;
import android.widget.TextView;

import com.mydemo.Base.Base2Activity;
import com.mydemo.R;

import java.util.concurrent.ThreadPoolExecutor;
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
    private Condition condition;
    private Lock lock;

    @Override
    public void setDate() {

    }

    @Override
    public void setView(View contentView) {
        toolBarTextView = (TextView) contentView.findViewById(R.id.toolBar);
        toolBarTextView.setText("Androdi 多线程");

        baseKnowledgeText = (TextView) findViewById(R.id.item_text);
        baseKnowledgeText.setText("多线程");

        lock = new ReentrantLock();
        condition = lock.newCondition();

        final MyThread1 myThread1 = new MyThread1();
        final Thread mThread1 = new Thread(myThread1);
        myThread1.from = 10;
        myThread1.to = 11;

        mThread1.start();


        baseKnowledgeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MyThread1 myThread2 = new MyThread1();
                final Thread mThread2 = new Thread(myThread2);
                myThread2.from = 12;
                myThread2.to = 11;

                mThread2.start();

            }
        });
    }

    @Override
    protected View getContentView() {
        return inflateView(R.layout.activity_much_thread);
    }

    class MyThread1 implements Runnable {

        int from;
        int to;

        @Override
        public void run() {
            addMoney2(from, to);
        }


    }

    private void addMoney(int fromMoney, int toMoney) {
        try {
            lock.lock();
            while (fromMoney <= toMoney) {
                condition.wait(); // 阻塞线程并放弃锁,
                Log.i("-->", fromMoney + ":" + toMoney);
            }
            condition.notifyAll();
        } catch (Exception ex) {
            Log.i("-->exception", ex.getLocalizedMessage());
        } finally {
            lock.unlock();
        }
    }

    public synchronized void addMoney2(int from, int to) {
        try {
            while (from <= to) {
                wait();
                Log.i("-->", from + ":" + to);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Log.i("-->", e.getLocalizedMessage());
        }finally {
            notifyAll();
        }
    }
}

