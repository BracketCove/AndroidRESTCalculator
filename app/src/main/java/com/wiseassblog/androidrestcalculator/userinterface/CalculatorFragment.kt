package com.wiseassblog.androidrestcalculator.userinterface


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.wiseassblog.androidrestcalculator.R
import com.wiseassblog.androidrestcalculator.data.RequestApiImpl
import kotlinx.android.synthetic.main.fragment_calculator.*


class CalculatorFragment : Fragment(), ICalculatorUI.View, View.OnClickListener, View.OnLongClickListener {
    lateinit var logic: ICalculatorUI.Logic

    lateinit var api:RequestApiImpl

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_calculator, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Sure glad I don't have to write this in java...
        btn_evaluate.setOnClickListener(this)
        btn_decimal.setOnClickListener(this)
        btn_display_delete.setOnClickListener(this)
        btn_display_delete.setOnLongClickListener(this)

        btn_number_one.setOnClickListener(this)
        btn_number_two.setOnClickListener(this)
        btn_number_three.setOnClickListener(this)
        btn_number_four.setOnClickListener(this)
        btn_number_five.setOnClickListener(this)
        btn_number_six.setOnClickListener(this)
        btn_number_seven.setOnClickListener(this)
        btn_number_eight.setOnClickListener(this)
        btn_number_nine.setOnClickListener(this)
        btn_number_zero.setOnClickListener(this)

        btn_operator_add.setOnClickListener(this)
        btn_operator_subtract.setOnClickListener(this)
        btn_operator_multiply.setOnClickListener(this)
        btn_operator_divide.setOnClickListener(this)
    }

    /*---------------- Interface ----------------*/

    override fun onLongClick(v: View?): Boolean {
        when (v?.id) {
            R.id.btn_display_delete -> logic.handleViewEvent(ViewEvent.DeleteAll)
        }

        return true
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_evaluate -> logic.handleViewEvent(ViewEvent.Evaluate)
            R.id.btn_display_delete -> logic.handleViewEvent(ViewEvent.Delete)
            else -> {
                if (v is Button) {
                    logic.handleViewEvent(ViewEvent.Input(v.text.toString()))
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        logic.handleViewEvent(ViewEvent.Bind)
    }

    override fun getCurrentExpression(): String {
        return lbl_display.text.toString()
    }

    override fun setDisplay(value: String) {
        lbl_display.text = value
    }

    override fun showError(value: String) {
        Toast.makeText(activity, value, Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance() = CalculatorFragment()
    }
}
