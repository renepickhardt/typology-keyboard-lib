package de.typology.predict.network_predict;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by till on 6/8/14.
 */
public class IPAddressManager {

    private static final String TAG = "IPAddressManager";

//    private static final String PREFERENCE_NAME = "de.typology.predict.network.IPAddressPreference";
//    private static final String IP_ADDR_KEY = "ip-addr";
    private static final String DEFAULT_IP_ADDRESS = "192.168.1.11:8080";

    private static final String IP_ADDR_CONFIG_FILE_NAME = "typology-ip-config.txt";

    public static String getIpAddress(final Context context) {
        final File file = new File(Environment.getExternalStorageDirectory(),
                IP_ADDR_CONFIG_FILE_NAME);

        final StringBuilder text = new StringBuilder();
        try {
            final BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            br.close();
        } catch (IOException e) {
            final String error = "Error opening ip address file: " + e.getMessage();
            Log.e(TAG, error);

            final Toast toast = Toast.makeText(context, error, Toast.LENGTH_LONG);
            toast.show();

            return DEFAULT_IP_ADDRESS;
        }

        return text.toString().trim();
    }

//    private static final String ALERT_TITLE = "IP Config";
//    private static final String ALERT_MESSAGE = "Enter the server ip address, e.g. something like"
//            + DEFAULT_IP_ADDRESS;
//
//    public static String getAddress(final Context context) {
//        SharedPreferences sharedPref = context.getSharedPreferences(PREFERENCE_NAME,
//                Context.MODE_PRIVATE);
//        return sharedPref.getString(IP_ADDR_KEY, DEFAULT_IP_ADDRESS);
//    }
//
//    public static String readAddressFromDialog(final Context context) {
//        final String ipAddress = getAddressFromAlert(context);
//        if (ipAddress != "") {
//            storeIpAddress(ipAddress, context);
//            return ipAddress;
//        }
//        return getAddress(context);
//    }
//
//    private static String getAddressFromAlert(final Context context) {
//        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
//
//        builder.setTitle(ALERT_TITLE);
//        builder.setMessage(ALERT_MESSAGE);
//
//        final EditText input = new EditText(context);
//        builder.setView(input);
//
//        final StringBuilder ipBuilder = new StringBuilder();
//
//        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int whichButton) {
//                ipBuilder.append(input.getText().toString());
//            }
//        });
//
//        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int whichButton) {
//            }
//        });
//
////        AlertDialog alert = builder.create();
////        Window window = alert.getWindow();
////        WindowManager.LayoutParams lp = window.getAttributes();
////        final View inputView = viewProvider.getInputView();
////        if (inputView == null)
////            return "";
////        lp.token = inputView.getWindowToken();
////        lp.type = WindowManager.LayoutParams.TYPE_APPLICATION_ATTACHED_DIALOG;
////        window.setAttributes(lp);
////        window.addFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
//
//        builder.show();
//
//        return ipBuilder.toString();
//    }
//
//    private static void storeIpAddress(final String ipAddress, final Context context) {
//        SharedPreferences sharedPrefs = context.getSharedPreferences(PREFERENCE_NAME,
//                Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPrefs.edit();
//        editor.putString(IP_ADDR_KEY, ipAddress);
//        editor.commit();
//    }
}
