package sample.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import sample.dev.R;

public class LoginModelView extends RecyclerView.ViewHolder {
    public final View mView;

    @BindView(R.id.forgotPassword)
    public TextView forgot;

    @BindView(R.id.email)
    public EditText email;

    @BindView(R.id.password)
    public TextView password;

    @BindView(R.id.email_sign_in_button)
    public Button signIn;

    public LoginModelView(View view) {
        super(view);
        mView = view;
        ButterKnife.bind(this, view);
    }
}
