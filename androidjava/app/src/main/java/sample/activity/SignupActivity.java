package sample.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.logging.Level;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sample.controller.UserController;
import sample.dev.R;
import sample.model.User;
import sample.user.SignupFragment;
import sample.util.FragmentUtils;

public class SignupActivity extends AppCompatActivity implements SignupFragment.SignupFragmentListener {

    private static final long OPEN_MAIN_ACTIVITY_DELAY = 1000;

    protected java.util.logging.Logger log = java.util.logging.Logger.getLogger(getClass().getName());

    private String mEmailParam;

    private UserController userController =  new UserController();

//    @BindView(R.id.progress)
//    public View mProgressView;
//
//    @BindView(R.id.signup_outer_scroll)
//    public ScrollView mSignupView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_signup);
//        ButterKnife.bind(this);
//        Intent intent = getIntent();
//        mEmailParam = intent.getStringExtra(ConstantUtils.ARG_USER_EMAIL);
//
//        setupUI();
//        showSignUpFragment();
    }

    private void showSignUpFragment() {
//        Fragment fragment = SignupFragment.newInstance(mEmailParam, "");
//        FragmentUtils.add(this, fragment, R.id.container_signup);
    }

    private void setupUI() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle("Sign up");
    }

//    @Override
//    public void signUp(final User user) {
//        showProgress(true);
//        log.info("User to create: " + user);
//
//        Callback<User> callback = new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//                showProgress(false);
//                log.info("request: " + call.request());
//                log.info("Created User: " + response.body());
//                log.info("RAW: " + response.raw());
//                log.info("message: " + response.message());
//                log.info("code: " + response.code());
//                log.info("isSuccessful: " + response.isSuccessful());
//
//                if (response.isSuccessful()) {
//                    startLoginActivity();
//                } else {
//                    ToastUtils.shortToast(getApplicationContext(), response.message(), Toast.LENGTH_LONG);
//                }
//            }
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//                showProgress(false);
//                log.log(Level.WARNING, "Error to create new user", t);
//            }
//        };
//
//        userController.newUser(user, callback);
//    }

    @Override
    public void onBackPressed() {
        startLoginActivity();
    }

    private void startLoginActivity() {
        Intent i = new Intent(SignupActivity.this, LoginActivity.class);
        startActivity(i);
    }
}
