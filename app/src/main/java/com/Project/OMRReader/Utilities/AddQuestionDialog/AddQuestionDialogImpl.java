package com.Project.OMRReader.Utilities.AddQuestionDialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.view.WindowManager;

import com.Project.OMRReader.R;

public class AddQuestionDialogImpl {

    Dialog dialog;
    Context context;

    public AddQuestionDialogImpl(Context context){
        this.context = context;
    }

    public void set(){
        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.progresslayout);
        dialog.setCancelable(false);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setAttributes(lp);
    }
}
