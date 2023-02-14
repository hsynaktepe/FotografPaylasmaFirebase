package com.example.fotografpaylasmafirebase

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class KullaniciActivity : AppCompatActivity() {

    private lateinit var context:Context
    private lateinit var auth:FirebaseAuth
    val email:TextView
        get() = findViewById<TextView>(R.id.emailText) as TextView
    val password:TextView
        get() = findViewById<TextView>(R.id.passwordText) as TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        val guncelKullanici = auth.currentUser
        if (guncelKullanici != null){
            val intent = Intent(this,HaberlerActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    fun girisYap(view:View){

        auth.signInWithEmailAndPassword(email?.text.toString(),password?.text.toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful){

                    val guncelKullanici = auth.currentUser?.email.toString()
                    Toast.makeText(this,"Hoşgeldin: ${guncelKullanici}",Toast.LENGTH_LONG).show()

                    val intent = Intent(this,HaberlerActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }.addOnFailureListener { exception ->
                Toast.makeText(this,exception.localizedMessage,Toast.LENGTH_LONG).show()
            }
    }
    fun kayitOl(view: View){
        val email = email?.text.toString()
        val password = password?.text.toString()



        auth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener { task ->
            //asenkron
            if (task.isSuccessful){
                //diğer aktiviteye geçiş
                val intent = Intent(this,HaberlerActivity::class.java)
                startActivity(intent)
                finish()
            }
        }.addOnFailureListener { exception ->
            Toast.makeText(applicationContext,exception.localizedMessage,Toast.LENGTH_LONG).show()
        }

    }

}