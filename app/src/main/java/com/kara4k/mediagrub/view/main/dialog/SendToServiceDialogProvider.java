package com.kara4k.mediagrub.view.main.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;

import com.kara4k.mediagrub.R;
import com.kara4k.mediagrub.model.database.CustomUser;

import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SendToServiceDialogProvider implements DialogInterface.OnClickListener {

    @Inject
    public SendToServiceDialogProvider() {
    }

    public interface SendToServiceDialogCallback {
        void onSendToServiceChosen(SendToServiceParams params);
    }

    private String text;

    private SendToServiceDialogCallback callback;

    public void handleIntent(final Intent intent,
                             final Context context,
                             final SendToServiceDialogCallback callback) {

        this.callback = callback;
        this.text = null;

        final String action = intent.getAction();
        final String type = intent.getType();

        if (Intent.ACTION_SEND.equals(action) && "text/plain".equals(type)) {
            final String text = intent.getStringExtra(Intent.EXTRA_TEXT);

            if (StringUtils.isNotEmpty(text)) {
                this.text = text;

                final AlertDialog servicesDialog = createServicesDialog(context);
                servicesDialog.show();
            }
        }
    }

    private AlertDialog createServicesDialog(final Context context) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        final String[] menuItems = context.getResources()
                .getStringArray(R.array.array_send_to_service_names);

        builder.setItems(menuItems, this);

        return builder.create();
    }

    @Override
    public void onClick(final DialogInterface dialogInterface, final int i) {
        final SendToServiceParams params = createParams(i);

        if (callback != null) {
            callback.onSendToServiceChosen(params);
        }
    }

    private SendToServiceParams createParams(final int i) {
        final SendToServiceParams sendToServiceParams = new SendToServiceParams();
        sendToServiceParams.createBundle(text);
        sendToServiceParams.setService(i + 1);
        sendToServiceParams.setType(CustomUser.USER);

        return sendToServiceParams;
    }
}
