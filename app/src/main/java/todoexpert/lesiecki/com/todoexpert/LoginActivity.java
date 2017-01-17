package todoexpert.lesiecki.com.todoexpert;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.lang.annotation.Annotation;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import todoexpert.lesiecki.com.todoexpert.api.ToDoApi;
import todoexpert.lesiecki.com.todoexpert.api.UserResponse;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.usernameEditText)
    EditText usernameEditText;
    @BindView(R.id.passwordEditText)
    EditText passwordEditText;
    @BindView(R.id.login_button)
    Button loginButton;

    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @OnClick({R.id.login_button, R.id.registerButton})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_button:
                performLogin();
                break;
            case R.id.registerButton:
                break;
        }
    }

    private void performLogin() {
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        boolean isValid = true;
        if (username.length() <= 5) {
            isValid = false;
            usernameEditText.setError(getString(R.string.username_too_short));
        }
        if (password.isEmpty()) {
            isValid = false;
            passwordEditText.setError(getString(R.string.empty_password));
        }

        if (isValid) {
            login(username, password);
        }
    }

    private void login(String username, String password) {
        AsyncTask<String, Integer, String > asyncTask = new AsyncTask<String, Integer, String>() {
            /*
            Method runs before executing code
             */
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loginButton.setEnabled(false);
            }

            @Override
            protected void onPostExecute(String errormessage) {
                super.onPostExecute(errormessage);
                loginButton.setEnabled(true);
                if(errormessage == null){
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this, errormessage, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                super.onProgressUpdate(values);
                loginButton.setText(String.valueOf(values[0]));
            }

            @Override
            protected String doInBackground(final String... params) {
                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                OkHttpClient client = new OkHttpClient.Builder()
                        .addInterceptor(interceptor)
                        .build();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://parseapi.back4app.com")
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                Converter<ResponseBody, ErrorResponse> converter = retrofit.responseBodyConverter(ErrorResponse.class, new Annotation[0]);

                ToDoApi toDoApi = retrofit.create(ToDoApi.class);
                Call<UserResponse> login = toDoApi.login(params[0], params[1]);
                try {
                    Response<UserResponse> userResponse = login.execute();
                    if(userResponse.isSuccessful()){
                        //TODO
                        return null;
                    } else {
                        ErrorResponse errorResponse = converter.convert(userResponse.errorBody());
                        return errorResponse.getError();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return e.getMessage();
                }
            }
        };
        asyncTask.execute(username, password);
    }

    private void loginWithHandler(final String username, final String password) {
        loginButton.setEnabled(false);
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Thread.sleep(5000);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            loginButton.setEnabled(true);
                        }
                    });

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
