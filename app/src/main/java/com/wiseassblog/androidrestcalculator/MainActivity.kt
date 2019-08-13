package com.wiseassblog.androidrestcalculator

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.wiseassblog.androidrestcalculator.buildlogic.DependencyProvider
import com.wiseassblog.androidrestcalculator.data.RequestApiImpl
import com.wiseassblog.androidrestcalculator.domain.ResultWrapper
import com.wiseassblog.androidrestcalculator.userinterface.CalculatorFragment
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    companion object {
        val VIEW: String = "VIEW"
    }

    val request = RequestApiImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val manager: FragmentManager = this.supportFragmentManager

        //Elvis OperatorDataModel:
        //if (object/function) returns/is null :? do this
        val view = manager.findFragmentByTag(VIEW) as CalculatorFragment?
            ?: CalculatorFragment.newInstance()

        DependencyProvider.provideLogic(this, view)

        manager.beginTransaction().replace(R.id.rootMainActivity, view).commit()

    }
}
