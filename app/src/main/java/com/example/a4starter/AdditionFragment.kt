package com.example.a4starter

import android.graphics.Path
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import android.graphics.*
import android.graphics.Canvas
import android.widget.LinearLayout
import android.graphics.BitmapFactory


class AdditionFragment : Fragment() {
    private var mViewModel: SharedViewModel? = null

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreate(savedInstanceState)

        mViewModel = ViewModelProvider(requireActivity()).get(SharedViewModel::class.java)
        val root: View = inflater.inflate(R.layout.fragment_addition, container, false)
        val image = BitmapFactory.decodeResource(resources, R.drawable.paper)
        //pageView!!.setImage(image)
        val textView = root.findViewById<TextView>(R.id.text_addition)
        mViewModel!!.desc.observe(viewLifecycleOwner, { s:String -> textView.text = "$s - Addition" })
        mViewModel!!.strokeGestures.observe(viewLifecycleOwner, { s:ArrayList<Path> -> textView.text = "stroke count: ${s.size}"})

        return root
    }

    //val bitmap = Bitmap.createBitmap(1500, 850, Bitmap.Config.ARGB_8888)
    //val canvas = Canvas(bitmap)

    //canvas.drawBitmap(bitmap,0f,0f,null)
    //canvas.drawRect(10F,10F,20F,20F,Paint(0))
}
