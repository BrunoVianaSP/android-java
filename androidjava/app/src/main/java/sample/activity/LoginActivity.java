package sample.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import java.util.Objects;
import java.util.logging.Level;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sample.controller.UserController;
import sample.dev.R;
import sample.model.User;
import sample.user.ChangePasswordFragment;
import sample.user.ForgotPasswordFragment;
import sample.user.LoginFragment;
import sample.util.ConstantUtils;
import sample.util.DialogUtils;
import sample.util.FragmentUtils;


public class LoginActivity extends AppCompatActivity implements LoginFragment.LoginFragmentListener, ForgotPasswordFragment.ForgotPasswordListener, ChangePasswordFragment.ChangePasswordFragmentListener {

    private static final long OPEN_MAIN_ACTIVITY_DELAY = 1000;

    protected java.util.logging.Logger log = java.util.logging.Logger.getLogger(getClass().getName());

    private LoginFragment loginFragment;

    private UserController userController = new UserController();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        showLoginFragment();
    }

    private void showLoginFragment() {
        Objects.requireNonNull(getSupportActionBar()).setTitle("Login");
        FragmentUtils.replace(this, LoginFragment.newInstance("",""), R.id.login_container);
    }

    @Override
    public void loginButton(final String email, final String password) {
        log.info("loginButton");

        Callback<User> callback = new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                log.log(Level.INFO, "onResponse");

                User res = response.body();

                log.log(Level.INFO, "User: " + res);

                if (isLoggedIn(res)) {
                    startMainActivity();
                } else {
                    offerSignUpOrTryAgain(email, password);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                log.log(Level.INFO, "onFailure", t);
            }
        };

        userController.authenticate(email, password, callback);
    }

    private void offerSignUpOrTryAgain(final String email, final String password) {
        String msg = "Would you like to create a new account?";
        LoginActivity context = LoginActivity.this;
        int myDialogTheme = R.style.MyDialogTheme;

        DialogInterface.OnClickListener yesListener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                startSignUpActivity(email, password);
            }
        };

        DialogInterface.OnClickListener noListener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        };

        DialogUtils.create(context, myDialogTheme, msg, yesListener, noListener);
    }

    private void startSignUpActivity(String email, String password) {
        Intent i = new Intent(LoginActivity.this, SignupActivity.class);
        i.putExtra(ConstantUtils.ARG_USER_EMAIL, email);
        i.putExtra(ConstantUtils.ARG_USER_PASSWORD, password);
        startActivity(i);
    }

    private boolean isLoggedIn(User res) {
//       return Objects.nonNull(res);
        return res != null;
    }

    private void startMainActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i=new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
            }
        }, OPEN_MAIN_ACTIVITY_DELAY);
    }

    @Override
    public void forgotButton() {
        log.info("forgotButton");
        Fragment fragment = ForgotPasswordFragment.newInstance("","");
        FragmentUtils.replace(this, fragment, R.id.login_container);
    }

    @Override
    public void continueButton(String email) {
        log.info("continueButton");
        Fragment fragment = ChangePasswordFragment.newInstance("","");
        FragmentUtils.replace(this, fragment, R.id.login_container);
    }

    @Override
    public void saveNewPasswordPressed(String newPassword, String confirmPassword) {
        startMainActivity();
    }

    @Override
    public void onBackPressed() {
        LoginActivity.this.finishAffinity();
        System.exit(0);
    }
}

