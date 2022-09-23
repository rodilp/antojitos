package com.antojitos.pe.activity.ui.notifications

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.antojitos.pe.R
import com.antojitos.pe.activity.ProfileActivity
import com.antojitos.pe.activity.RecipeActivity
import kotlinx.android.synthetic.main.fragment_notifications.view.*
import kotlinx.android.synthetic.main.modal_delete_device.view.*

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
                ViewModelProvider(this).get(NotificationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)

        //root.tviLogout.text = "Iniciar sesion"

        root.cvi_profess.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, ProfileActivity::class.java)
            startActivity(intent)
        })

        root.cvi_premium.setOnClickListener(View.OnClickListener {
            showVideo()
        })

        return root
    }



    fun showVideo() {
        val builder = context?.let { AlertDialog.Builder(it, R.style.AlertDialogtheme) }

        val root: View = LayoutInflater.from(context).inflate(R.layout.modal_premium, null)

        builder?.setView(root)

        val alertDialog = builder?.create()

        root.btn_delete_band.setOnClickListener(View.OnClickListener {
            alertDialog?.dismiss()
        })

        if (alertDialog != null) {
            if (alertDialog.window != null) {
                alertDialog.window!!.setBackgroundDrawable(ColorDrawable(0))
            }
        }
        alertDialog?.show()
    }
}