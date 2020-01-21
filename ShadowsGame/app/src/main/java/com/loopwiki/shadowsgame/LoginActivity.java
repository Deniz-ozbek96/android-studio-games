package com.loopwiki.shadowsgame;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    //EditTextler deklere edildi(tanıtıldı)
    EditText editTextEmail;
    EditText editTextPassword;

    //TextInputLayoutlar deklare edildi
    TextInputLayout textInputLayoutEmail;
    TextInputLayout textInputLayoutPassword;

    //Buttonlar deklare edildi
    Button buttonLogin,btn6;

    //SqliteHelper deklare edildi
    SqliteHelper sqliteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //btn6 ya tıklandığında gerçekleşek olay
        btn6 = (Button) findViewById(R.id.btn6);

        btn6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent into = new Intent(LoginActivity.this, Kategoriler.class);
                startActivity(into);

            }

        });

        //sqlite nesnesi ve metodu oluşturuldu
        sqliteHelper = new SqliteHelper(this);
        initCreateAccountTextView();
        initViews();

        //login buttonuna tıklandığında gerçekleşek olay
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //kullanıcının girdikleri doğru mu yanlış mı kontrolü
                if (validate()) {

                    //EditText fields daki değerler
                    String Email = editTextEmail.getText().toString();
                    String Password = editTextPassword.getText().toString();

                    //Authenticate (kullanıcı kimlik doğrulaması)
                    User currentUser = sqliteHelper.Authenticate(new User(null, null, Email, Password));

                    //kimlik doğrulması başarılı mı değil mi kontrol et başarılı giriş yapıldı ya da girş yapılamadı tekrar denein yazısı çıkar
                    if (currentUser != null) {
                        Snackbar.make(buttonLogin, "Successfully Logged in!", Snackbar.LENGTH_LONG).show();

                        //giriş başarılıysa kategoriler ekranına geçiş yapılır
                        Intent intent=new Intent(LoginActivity.this,Kategoriler.class);
                        startActivity(intent);
                        finish();
                    } else {

                        //Kullanıcı giriş yapamadıysa
                        Snackbar.make(buttonLogin, "Failed to log in , please try again", Snackbar.LENGTH_LONG).show();

                    }
                }
            }
        });


    }

    //bu metodun içinde oluşturulan TextViewı(yeni hesap oluşturma textviewı) ve ona tıklanma olayını anlatır.( TextView için çoklu renk oluşturulmuştur.)
    //Yeni oluşturma Textine tıklandığında RegisterActivitye geçer.
    private void initCreateAccountTextView() {
        TextView textViewCreateAccount = (TextView) findViewById(R.id.textViewCreateAccount);
        textViewCreateAccount.setText(fromHtml("<font color='#ffffff'>I don't have account yet. </font><font color='#0c0099'>create one</font>"));
        textViewCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    //XML le bağlantısını kurar
    private void initViews() {
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);

    }

    //Versiyonu kontrol etme
    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String html) {
        Spanned result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }

    //Kullanıcının girdiklerini onaylanma olayları
    public boolean validate() {
        boolean valid = false;

        //EditTextlere girilen değerleri getirir
        String Email = editTextEmail.getText().toString();
        String Password = editTextPassword.getText().toString();

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
