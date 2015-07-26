package meru.andriod.mcom.activity;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import meru.andriod.mcom.ConfigStore;
import meru.andriod.mcom.R;
import meru.andriod.mcom.task.AsyncTaskCompletionListener;
import meru.andriod.mcom.task.HtttpAsyncPostTask;
import meru.andriod.mcom.util.AndriodUI;

public class LoginFragment extends BaseFragment
        implements AsyncTaskCompletionListener {

    private View view;
    private EditText emailText;
    private EditText passwordText;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_login,
                                container,
                                false);
        emailText = (EditText) view.findViewById(R.id.activity_login_Email);
        passwordText = (EditText) view.findViewById(R.id.activity_login_Password);

        fillupFields();

        view.findViewById(R.id.sign_in_button)
            .setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    login();
                }
            });

        return view;
    }

    private void fillupFields() {
        ConfigStore configStore = ConfigStore.getInstance();

        emailText.setText(configStore.getProperty(ConfigStore.APP_USER_NAME,
                                                  ""));
        passwordText.setText(configStore.getProperty(ConfigStore.APP_PASSWORD,
                                                     ""));
    }

    public void login() {

        emailText.setError(null);
        passwordText.setError(null);

        String email = emailText.getText()
                                .toString();
        String password = passwordText.getText()
                                      .toString();

        boolean error = false;
        if (TextUtils.isEmpty(password)) {
            passwordText.setError(getString(R.string.error_field_required));
            passwordText.requestFocus();
            error = true;
        }

        if (TextUtils.isEmpty(email)) {
            emailText.setError(getString(R.string.error_field_required));
            emailText.requestFocus();
            error = true;
        }

        if (!error) {

            ConfigStore configStore = ConfigStore.getInstance();
            configStore.storeProperty(ConfigStore.APP_USER_NAME,
                                      email);
            configStore.storeProperty(ConfigStore.APP_PASSWORD,
                                      password);

            TextView statusView = (TextView) view.findViewById(R.id.activity_login_status_message);
            statusView.setText(R.string.login_signing_in_progress);

            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("appId",
                                                      "ns"));
            nameValuePairs.add(new BasicNameValuePair("email",
                                                      email));
            nameValuePairs.add(new BasicNameValuePair("password",
                                                      password));

            HtttpAsyncPostTask asyncTask = new HtttpAsyncPostTask(this,
                                                                  nameValuePairs);
            asyncTask.execute("/account/a/login");

        }

    }

    @Override
    public void onExecutionComplete(JSONObject jsonObject) {

        attachFragment(R.id.frame_container,
                       new HomeFragment());

        AndriodUI.showToast(this.getActivity()
                                .getApplicationContext(),
                            "Login successful!");

    }

}
