package com.aksoyhasan.path.utils.internal.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import com.aksoyhasan.path.R

class RadiusImageView : AppCompatImageView {
    private var mRoundRadius = 0f
    private var mPath: Path? = null
    private var mPaint: Paint? = null

    constructor(context: Context?) : super(context!!) {
        mRoundRadius = DEFAULT_ROUND_RADIUS
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.RadiusImageView)
        mRoundRadius = typedArray.getDimension(R.styleable.RadiusImageView_roundRadius, DEFAULT_ROUND_RADIUS)
        typedArray.recycle()
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.RadiusImageView)
        mRoundRadius = typedArray.getDimension(R.styleable.RadiusImageView_roundRadius, DEFAULT_ROUND_RADIUS)
        typedArray.recycle()
        init()
    }

    private fun init() {
        setLayerType(View.LAYER_TYPE_HARDWARE, null)
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaint!!.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
    }

    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        super.onSizeChanged(width, height, oldWidth, oldHeight)
        if (width != oldWidth || height != oldHeight) {
            mPath = Path()
            mPath!!.addRoundRect(RectF(0f, 0f, width.toFloat(), height.toFloat()), mRoundRadius, mRoundRadius, Path.Direction.CW)
            mPath!!.fillType = Path.FillType.INVERSE_WINDING
        }
    }

    override fun onDraw(canvas: Canvas) {
        if (canvas.isOpaque) {
            canvas.saveLayerAlpha(0f, 0f, width.toFloat(), height.toFloat(), 255)
        }
        super.onDraw(canvas)
        if (mPath != null) {
            canvas.drawPath(mPath!!, mPaint!!)
        }
    }

    companion object {
        private const val DEFAULT_ROUND_RADIUS = 0f
    }
}