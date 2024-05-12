package com.example.gaenari.activity.iactivity

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.os.IBinder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.gaenari.R

class IsecondFragment : Fragment() {
    private lateinit var pauseButton: Button
    private lateinit var stopButton: Button
    private var service: IntervalService? =null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle? //
    ): View? {
        val view = inflater.inflate(R.layout.fragment_isecond, container, false)
        pauseButton = view.findViewById(R.id.pauseButton)
        stopButton = view.findViewById(R.id.stopButton)

        pauseButton.setOnClickListener { togglePause() }
        stopButton.setOnClickListener { stopExercise() }
        bindService()
        return view
    }
    private fun togglePause() {
        if (pauseButton.text == "Pause") {
            pauseButton.text = "Resume"
            // 일시정지 로직
            service?.pauseService()
        } else {
            pauseButton.text = "Pause"
            // 재개 로직
            service?.resumeService()
        }
    }

    private fun stopExercise() {
        service?.onDestroy()
    }
    private fun bindService() {
        Intent(context, IntervalService::class.java).also { intent ->
            context?.bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
        }
    }
    private val serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, service: IBinder) {
            val binder = service as IntervalService.LocalBinder
            this@IsecondFragment.service = binder.getService()
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            service = null
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        context?.unbindService(serviceConnection)
    }
}
