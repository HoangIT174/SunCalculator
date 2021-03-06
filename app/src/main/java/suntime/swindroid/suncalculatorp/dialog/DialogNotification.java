package suntime.swindroid.suncalculatorp.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import suntime.swindroid.suncalculatorp.R;


/**
 * Created by giapmn on 8/17/17.
 */

public class DialogNotification extends Dialog implements View.OnClickListener {
    private TextView tvTitle;
    private TextView tvContent;
    private Button btnExit, btnOK;

    private Context context;

    public DialogNotification(@NonNull Context context) {
        super(context);
        this.context = context;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(R.layout.dialog_notification);
        initView();
    }

    private void initView() {
        tvTitle = (TextView) findViewById(R.id.tv_title_dialog);
        tvContent = (TextView) findViewById(R.id.tv_content_dialog);
        btnExit = (Button) findViewById(R.id.btn_exit_dialog);
        btnOK = (Button) findViewById(R.id.btn_ok_dialog);
        btnExit.setOnClickListener(this);
        btnOK.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_exit_dialog:
                if (onClickButtonDialogListener != null){
                    onClickButtonDialogListener.onClickButtonExit();
                }
                break;
            case R.id.btn_ok_dialog:
                if (onClickButtonDialogListener != null){
                    onClickButtonDialogListener.onClickButtonOK();
                }
                break;
        }
        dismiss();
    }

    public void setHiddenBtnOK(){
        btnOK.setVisibility(View.GONE);
    }


    public void setHiddenBtnExit(){
        btnExit.setVisibility(View.GONE);
    }

    public void setTitle(String title){
        tvTitle.setText(title);
    }

    public void setContentMessage(String contentMessage){
        tvContent.setText(contentMessage);
    }

    public void setTextBtnExit(String title){
        btnExit.setText(title);
    }

    public void setTextBtnOK(String title){
        btnOK.setText(title);
    }

    private OnClickButtonDialogListener onClickButtonDialogListener;


    public interface OnClickButtonDialogListener{
        void onClickButtonOK();
        void onClickButtonExit();
    }

    public void setOnClickButtonDialogListener(OnClickButtonDialogListener onClickButtonDialogListener) {
        this.onClickButtonDialogListener = onClickButtonDialogListener;
    }
}
