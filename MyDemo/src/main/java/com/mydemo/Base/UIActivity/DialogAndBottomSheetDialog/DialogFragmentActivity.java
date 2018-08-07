package com.mydemo.Base.UIActivity.DialogAndBottomSheetDialog;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.mydemo.Base.Base2Activity;
import com.mydemo.R;

/**
 * Created by HaoPz on 2018/7/10.
 */

public class DialogFragmentActivity extends Base2Activity {

    private TextView openDialogFra;
    private MyDialogFragment myDialogFragment;

    @Override
    public void setDate() {
        openDialogFra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 开启 DialogFragment
                // 布局圆角问题:在DialogFragment的布局文件设置的时候 留出一些padding空间，
                // 在fragment 的onCreate 中添加
                //            getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
                //            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                myDialogFragment = new MyDialogFragment();
                myDialogFragment.setCancelable(false);
                //  myDialogFragment.setStyle(R.style.dialogFragmentStyle, 0);

                myDialogFragment.setTitle("我是Title");
                myDialogFragment.setContent("模式讲解： \n" +
                        "1. 指挥者（Director）直接和客户（Client）进行需求沟通； \n" +
                        "2. 沟通后指挥者将客户创建产品的需求划分为各个部件的建造请求（Builder）； \n" +
                        "3. 将各个部件的建造请求委派到具体的建造者（ConcreteBuilder）； \n" +
                        "4. 各个具体建造者负责进行产品部件的构建； \n" +
                        "5. 最终构建成具体产品（Product）。");
                myDialogFragment.setSureText("好的");
                myDialogFragment.setCancleText("再想想");


                myDialogFragment.setSureClickListener(new SureClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(DialogFragmentActivity.this, "确认", Toast.LENGTH_SHORT).show();
                        myDialogFragment.dismiss();
                    }
                });

                myDialogFragment.setCancleClickListener(new CancleClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(DialogFragmentActivity.this, "取消", Toast.LENGTH_SHORT).show();
                        myDialogFragment.dismiss();
                    }
                });

                myDialogFragment.show(getSupportFragmentManager(), "");
            }
        });
    }

    @Override
    public void setView(View contentView) {
        openDialogFra = (TextView) contentView.findViewById(R.id.openDialogFra);
    }

    @Override
    protected View getContentView() {
        return inflateView(R.layout.activity_ui_dialog_fragment);
    }

    // ------------------ MyDialogFragment --------------------
    public static class MyDialogFragment extends DialogFragment {

        private String dialogFragmentTitle;
        private String dialogFragmentContent;
        private String dialogFragmentCancle;
        private String dialogFragmentSure;
        private MyDialogFragmentBuilder myDialogFragmentBuilder;

        public SureClickListener sureClickListener;
        public CancleClickListener cancleClickListener;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//            getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
//            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            View view = inflater.inflate(R.layout.layout_dialog_fragment, container, false);
            TextView dialogFragmentTitle = (TextView) view.findViewById(R.id.dialogFragmentTitle);
            TextView dialogFragmentContent = (TextView) view.findViewById(R.id.dialogFragmentContent);
            TextView dialogFragmentCancle = (TextView) view.findViewById(R.id.dialogFragmentCancle);
            TextView dialogFragmentSure = (TextView) view.findViewById(R.id.dialogFragmentSure);

            dialogFragmentTitle.setText(this.dialogFragmentTitle);
            dialogFragmentContent.setText(this.dialogFragmentContent);
            dialogFragmentCancle.setText(this.dialogFragmentCancle);
            dialogFragmentSure.setText(this.dialogFragmentSure);

            dialogFragmentSure.setOnClickListener(sureClickListener);
            dialogFragmentCancle.setOnClickListener(cancleClickListener);
            return view;
        }

        public void setBuilder(MyDialogFragmentBuilder myDialogFragmentBuilder) {
            this.myDialogFragmentBuilder = myDialogFragmentBuilder;
        }

        public void setTitle(String dfTitle) {
            if (!(dfTitle instanceof String)) {
                throw new RuntimeException("title don`t instanceof String");
            }
            dialogFragmentTitle = dfTitle;
        }

        public void setContent(String dfContent) {
            if (!(dfContent instanceof String)) {
                throw new RuntimeException("content don`t instanceof String");
            }
            dialogFragmentContent = dfContent;
        }

        public void setSureText(String dfSureText) {
            if (!(dfSureText instanceof String)) {
                throw new RuntimeException("sureText don`t instanceof String");
            }
            dialogFragmentSure = dfSureText;
        }

        public void setCancleText(String dfCancleText) {
            if (!(dfCancleText instanceof String)) {
                throw new RuntimeException("cancleText don`t instanceof String");
            }
            dialogFragmentCancle = dfCancleText;
        }

        public void setSureClickListener(SureClickListener sureClickListener) {
            this.sureClickListener = sureClickListener;

        }

        public void setCancleClickListener(CancleClickListener cancleClickListener) {
            this.cancleClickListener = cancleClickListener;

        }
    }

    public class MyDialogFragmentBuilder {

    }

    public interface SureClickListener extends View.OnClickListener {

    }

    public interface CancleClickListener extends View.OnClickListener {

    }
}
