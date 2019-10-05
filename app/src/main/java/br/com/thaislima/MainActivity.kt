package br.com.thaislima

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()

        btEntrar.setOnClickListener {
            entrar()
        }

        btCriar.setOnClickListener {
            criarConta()
        }

        btLimpar.setOnClickListener {
            limparCampos()
        }

    }

    private fun usuarioEstaConectado() : Boolean{
        val currentUser = mAuth.currentUser
        return currentUser != null
    }

        private fun entrar(){
            mAuth.signInWithEmailAndPassword(etEmail.text.toString(),
                etSenha.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        abrirSite()
                    } else {
                        // If sign in fails, display a message to the user.
                       Toast.makeText(
                           this,
                           task.exception?.message,
                           Toast.LENGTH_SHORT
                           ).show()
                    }

                    // ...
                }
        }

        private fun criarConta() {
            mAuth.createUserWithEmailAndPassword(etEmail.text.toString(),
                etSenha.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful){
                        abrirSite()
                    } else {
                        Toast.makeText(
                            this,
                            task.exception?.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }
        }


        private fun limparCampos(){

        }

        private fun abrirSite(){
            val intent = Intent(this, SiteActivity::class.java)
            startActivity(intent)
            finish()
    }

}


