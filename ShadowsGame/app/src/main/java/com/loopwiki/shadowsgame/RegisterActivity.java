package com.loopwiki.shadowsgame;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    //EditTextler deklere edildi(tanıtıldı)
    EditText editTextUserName;
    EditText editTextEmail;
    EditText editTextPassword;

    //TextInputLayoutlar deklare edildi
    TextInputLayout textInputLayoutUserName;
    TextInputLayout textInputLayoutEmail;
    TextInputLayout textInputLayoutPassword;

    //Buttonlar deklare edildi
    Button buttonRegister;

    //Declaration SqliteHelper
    SqliteHelper sqliteHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //sqlite nesnesi ve metodları oluşturuldu
        sqliteHelper = new SqliteHelper(this);
        initTextViewLogin();
        initViews();
        //buttonRegistera tıklandığında olan olaylar
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    String UserName = editTextUserName.getText().toString();
                    String Email = editTextEmail.getText().toString();
                    String Password = editTextPassword.getText().toString();

                    //Databasede bu emaille ilişkili olan kullanıcı var mı kontrol et
                    if (!sqliteHelper.isEmailExists(Email)) {

                        //Girilen email yoksa yeni bir kullanıcı oluşturulur databasede ve kullanıcı başarılı bir şekilde oluşturuldu lütfen giriş yapın uyarısı çıksın
                        sqliteHelper.addUser(new User(null, UserName, Email, Password)); //birinci parametre null yani ıd değeri
                        //snackbar(sayfanın altında bulunur ve kullanıcı etkileşime izin verir) ekledik toast mesaj gibi
                        Snackbar.make(buttonRegister, "User created successfully! Please Login ", Snackbar.LENGTH_LONG).show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, Snackbar.LENGTH_LONG);
                    }else {

                        //Email daha önce kullanılmışsa bu emaille zaten bir kullanıcı var uyarısı çıksın
                        Snackbar.make(buttonRegister, "User already exists with same email ", Snackbar.LENGTH_LONG).show();
                    }


                }
            }
        });
    }

    //bu metod back to Login TextViewına tıklandığında gerçekleşecek olayları gösterir
    private void initTextViewLogin() {
        TextView textViewLogin = (TextView) findViewById(R.id.textViewLogin);
        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    //XML le bağlantısını kurar
    private void initViews() {
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextUserName = (EditText) findViewById(R.id.editTextUserName);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputLayoutUserName = (TextInputLayout) findViewById(R.id.textInputLayoutUserName);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);

    }

    //Kullanıcının girdiklerini onaylanma olayları
    public boolean validate() {
        boolean valid = false;

        //EditTextlere girilen değerleri getirir
        String UserName = editTextUserName.getText().toString();
        String Email = editTextEmail.getText().toString();
        String Password = editTextPassword.getText().toString();

        //UserName(kullanıcı adı)için işlem doğrulama boşsa yanlış olarak değerlendirilir ve geçerli bir kullanıcı adı girin uyarısı çıkar.
        //5 den kısa olursa kullanıcı adı kısa diye uyarı çıkar
        if (UserName.isEmpty()) {
            valid = false;
            textInputLayoutUserName.setError("Please enter valid username!");
        } else {
            if (UserName.length() > 5) {
                valid = true;
                textInputLayoutUserName.setError(null);
            } else {
                valid = false;
                textInputLayoutUserName.setError("Username is to short!");
            }
        }

        //Email için işlem doğrulama yanlış girilirse doğrusunu girin diye uyarı çıkar
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            valid = false;
            textInputLayoutEmail.setError("Please enter valid email!");
        } else {
            valid = true;
            textInputLayoutEmail.setError(null);
        }

        //Password(şifre)için işlem doğrulama boşsa yanlış olarak değerlendirilir ve lütfen geçerli bir şifre girin uyarısı çıkar.
        //5 den kısa olursa kullanıcı adı kısa diye uyarı çıkar
        if (Password.isEmpty()) {
            valid = false;
            textInputLayoutPassword.setError("Please enter valid password!");
        } else {
            if (Password.length() > 5) {
                valid = true;
                textInputLayoutPassword.setError(null);
            } else {
                valid = false;
                textInputLayoutPassword.setError("Password is to short!");
            }
        }


        return valid;
    }
}
