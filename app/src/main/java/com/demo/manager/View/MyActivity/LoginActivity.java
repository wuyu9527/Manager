package com.demo.manager.View.MyActivity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.manager.Bean.User;
import com.demo.manager.P.LoginP;
import com.demo.manager.R;
import com.demo.manager.Util.HttpApi;
import com.demo.manager.View.Fragment.HomeActivity;
import com.demo.manager.View.Interface.Login;
import com.demo.manager.View.Interface.MyError;
import com.demo.mylibrary.ClircularProgress.CircularProgressButton;

import java.util.ArrayList;
import java.util.List;


import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends Activity implements LoaderCallbacks<Cursor>, Login, MyError, View.OnClickListener {

    /**
     * 身份认同read_contacts权限请求。
     */
    private static final int REQUEST_READ_CONTACTS = 0;


    /**
     * 跟踪登录的任务，以确保如果请求的话，我们可以取消它。
     */
    private UserLoginTask mAuthTask = null;

    // UI references.
    private AutoCompleteTextView mNameView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private User user;
    private LoginP loginP;
    private String SignIn = "登录";
    private String SignOut = "注册";
    private String myError;
    CircularProgressButton circularButton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Set up the login form.
        mNameView = (AutoCompleteTextView) findViewById(R.id.email);
        populateAutoComplete();
        mPasswordView = (EditText) findViewById(R.id.password);


        /*Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin(SignIn);
            }
        });*/

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);

        circularButton1 = (CircularProgressButton) findViewById(R.id.email_sign_in_button);
        circularButton1.setOnClickListener(this);


    }


    private void populateAutoComplete() {
        if (!mayRequestContacts()) {
            return;
        }
        getLoaderManager().initLoader(0, null, this);
    }

    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
            Snackbar.make(mNameView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new View.OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });
        } else {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    /**
     * 当已完成权限请求时收到的回调。
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == REQUEST_READ_CONTACTS) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                populateAutoComplete();
            }
        }
    }
    boolean cancel = false;
    private void attemptLogin(String type) {
        if (mAuthTask != null) {
            return;
        }

        // 复位
        mNameView.setError(null);
        mPasswordView.setError(null);

        // 在登录尝试时存储值。
        String str = mNameView.getText().toString();
        String password = mPasswordView.getText().toString();

        cancel = false;
        View focusView = null;

        // 检查一个有效的密码，如果用户输入了一个。
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }else if (TextUtils.isEmpty(password)){
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;
        }
        // 检查有效的电子邮件地址。
        if (TextUtils.isEmpty(str)) {
            mNameView.setError(getString(R.string.error_field_required));
            focusView = mNameView;
            cancel = true;
        } else if (!isEmailValid(str, type)) {
            mNameView.setError(getString(R.string.error_invalid_email));
            focusView = mNameView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            circularButton1.setProgress(-1);
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            //showProgress(true);
            loginP = new LoginP(this);
            loginP.myLoadUser(str, password);
//            mAuthTask = new UserLoginTask(str, password,type);
//            mAuthTask.execute((Void) null);
        }
    }

    private boolean isEmailValid(String str, String string) {//验证用户
        boolean isNameValid = false;
        switch (string) {
            case "注册":
                if (str.matches("^1[3|4|5|7|8]\\d{9}$")) {
                    isNameValid= true;
                }
                break;
            case "登录":
                if (str.matches("^1[3|4|5|7|8]\\d{9}$")) {
                    isNameValid= true;
                } else if (str.matches("^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$")) {
                    isNameValid= true;
                } else if (str.matches("^[a-zA-Z0-9_]{3,16}$")) {
                    isNameValid= true;
                }
                break;
            default:
                break;
        }
        return isNameValid;
    }

    private boolean isPasswordValid(String password) {//验证密码
        if (password.matches("^[a-zA-Z0-9_]{6,16}$")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 显示进程的用户界面并隐藏登录表单。
     *//*
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }*/

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mNameView.setAdapter(adapter);
    }

    public void myGengXing() {
        Log.i("whx",user.getInKey());
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        intent.putExtra("id", user.getId());
        startActivity(intent);
        LoginActivity.this.finish();
    }

    @Override
    public String getName() {
        return mNameView.getText().toString();
    }

    @Override
    public String getPassWord() {
        return mPasswordView.getText().toString();
    }

    @Override//Activity储存
    public void setUser(String id, String name, String password) {
        user = new User(id, name, password);
        if (user.getInKey()!=null){
            circularButton1.setProgress(100);
            myGengXing();
        }else {
            circularButton1.setProgress(-1);
            if (myError!=null) {
                Toast.makeText(LoginActivity.this, myError, Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(LoginActivity.this,"网络连接缓慢或未链接",Toast.LENGTH_LONG).show();
            }
            mPasswordView.setError(myError);
            mPasswordView.requestFocus();
        }
//        mAuthTask = new UserLoginTask(user.getId(), user.getName(), user.getInKey());
//        mAuthTask.execute((Void) null);
    }

    @Override
    public void setMyError(String err) {
        if (this.myError != null) {
            this.myError = null;
        }
        this.myError = err;
        handler.sendEmptyMessage(0);
    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            circularButton1.setProgress(-1);
            mPasswordView.setError(myError);
            mPasswordView.requestFocus();
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin(SignIn);
                    return true;
                }
                return false;
            }
        });
        circularButton1.setIndeterminateProgressMode(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.email_sign_in_button:
                attemptLogin(SignIn);
                if (cancel){
                    circularButton1.setProgress(0);
                }else {
                    if (circularButton1.getProgress() == 0) {
                        circularButton1.setProgress(50);
                    } else if (circularButton1.getProgress() == -1) {
                        circularButton1.setProgress(0);
                    }
                }
                break;

        }
    }


    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

    /**
     * 表示用于验证用户身份的异步登录/注册任务。
     */
    public class UserLoginTask extends AsyncTask<Void, Integer, Boolean> {

        private final String id;
        private final String name;
        private final String key;

        UserLoginTask(String id, String name, String key) {
            this.id = id;
            this.name = name;
            this.key = key;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: 验证


            if (name != null) {
                return true;
            }

            // TODO: register the new account here.


            return false;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            //showProgress(false);
            if (success) {
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
                finish();
            } else {
                mPasswordView.setError(myError);
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            int vlaue = values[0];
            circularButton1.setProgress(vlaue);
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            //showProgress(false);
        }
    }
}

