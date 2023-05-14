package com.example.music.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import com.example.music.R
import com.example.music.databinding.ActivityMainBinding
import com.example.music.model.Accout
import com.example.music.service.DataService
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.material.button.MaterialButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var gso: GoogleSignInOptions
    private lateinit var gsc: GoogleSignInClient
    private lateinit var listAccout: List<Accout>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val loginbtn: MaterialButton = findViewById(R.id.loginbtn)
        val googleBtn: ImageView = findViewById(R.id.google_btn)
        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN).requestEmail().build()
        gsc = GoogleSignIn.getClient(this, gso)

        googleBtn.setOnClickListener {
            singIn()
        }

        loginbtn.setOnClickListener {
            val username: EditText = findViewById(R.id.username)
            val pass: EditText = findViewById(R.id.password)
            if (username.text.toString() != "") {
                checkLogin(username.text.toString(), pass.text.toString())
            }
        }
    }

    private fun singIn() {
        val intent = gsc.signInIntent
        startActivityForResult(intent, 1000)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1000) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
//                val account: GoogleSignInAccount? = task.getResult(ApiException::class.java)
                // Xử lý tài khoản đăng nhập Google ở đây
                task.getResult(ApiException::class.java)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } catch (e: ApiException) {
                // Xử lý lỗi khi lấy tài khoản đăng nhập Google
                Log.e("TAG", "Error retrieving Google sign-in result: ${e.statusCode}")
            }
        }
    }

    private fun checkLogin(user: String, pass: String) {

        val service = DataService.getService()
        val call = service.getCheckLogin(user)

        call.enqueue(object : Callback<List<Accout>> {
            override fun onResponse(call: Call<List<Accout>>, response: Response<List<Accout>>) {
                listAccout = response.body()!!
                Log.d("DataAc", listAccout.toString())
                if (user == listAccout[0].acc_name && pass == listAccout[0].acc_pass) {
                    Toast.makeText(this@LoginActivity, "Đăng nhập thành công",Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    intent.putExtra("key", "value")
                    startActivity(intent)
                }
            }

            override fun onFailure(call: Call<List<Accout>>, t: Throwable) {
                Log.d("DataAccout", t.toString())
            }

        })
    }
}