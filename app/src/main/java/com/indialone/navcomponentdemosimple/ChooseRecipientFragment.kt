package com.indialone.navcomponentdemosimple

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation


class ChooseRecipientFragment : Fragment(), View.OnClickListener {

    private var navController: NavController? = null
    private lateinit var editRecipient : EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose_recipient, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        view.findViewById<Button>(R.id.next_btn).setOnClickListener(this)
        view.findViewById<Button>(R.id.cancel_btn).setOnClickListener(this)

        editRecipient = view.findViewById(R.id.input_recipient)

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.next_btn -> {
                if (!TextUtils.isEmpty(editRecipient.text.toString().trim { it <= ' ' })) {
                    val bundle = bundleOf("recipient" to editRecipient.text.toString())
                    navController!!.navigate(
                        R.id.action_chooseRecipientFragment_to_specifyAmountFragment,
                        bundle
                    )
                } else {
                    Toast.makeText(v.context, "Enter valid recipient name", Toast.LENGTH_SHORT).show()
                }

            }
            R.id.cancel_btn -> {
                activity?.onBackPressed()
            }
        }
    }

}