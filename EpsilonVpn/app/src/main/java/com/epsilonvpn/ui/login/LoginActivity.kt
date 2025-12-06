herepackage com.epsilonvpn.ui.login

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.epsilonvpn.R
import com.epsilonvpn.data.api.ApiClient
import com.epsilonvpn.data.api.ApiService
import com.epsilonvpn.data.model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {

    private lateinit var usernameEt: EditText
    private lateinit var passwordEt: EditText
    private lateinit var loginBtn: Button
    private val apiService: ApiService by lazy {
        ApiClient.getClient("https://your-api.com/").create(ApiService::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        usernameEt = findViewById(R.id.username)
        passwordEt = findViewById(R.id.password)
        loginBtn = findViewById(R.id.loginButton)

        loginBtn.setOnClickListener {
            login(usernameEt.text.toString(), passwordEt.text.toString())
        }
    }

    private fun login(username: String, password: String) {
        apiService.login(username, password).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful && response.body() != null) {
                    Toast.makeText(this@LoginActivity, "Login Success!", Toast.LENGTH_SHORT).show()
                    // TODO: دریافت کانفیگ و اتصال VPN
                } else {
                    Toast.makeText(this@LoginActivity, "Login Failed!", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "API Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
