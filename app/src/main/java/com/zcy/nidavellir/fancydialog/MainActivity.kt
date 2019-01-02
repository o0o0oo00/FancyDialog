package com.zcy.nidavellir.fancydialog

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun normal(view: View) {
        askDialog(supportFragmentManager) {
            mTitle = "标题"
//            mMessage = "摘要vv摘要摘要摘要摘要摘要"
//            mGravity = Gravity.CENTER
            sureClick {
                Toast.makeText(this@MainActivity, "sure", Toast.LENGTH_SHORT).show()
            }
            cancelClick {
                Toast.makeText(this@MainActivity, "cancel", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun onlySure(view: View) {
        askDialog(supportFragmentManager) {
            mTitle = "标题"
            mMessage = "摘要vv摘要摘要摘要摘要摘要"
            mGravity = Gravity.TOP
            onlySure = true
            sureClick {
                Toast.makeText(this@MainActivity, "sure", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun askMore(view: View) {
        askMoreDialog(supportFragmentManager) {
            mTitle = "标题"
            mMessage = "摘要vv摘要摘要摘要摘要摘要摘要vv摘要摘要摘要摘要摘要"
            mColor = Color.RED
            sureClick(key = "第一个") {
                Toast.makeText(this@MainActivity, "第一个", Toast.LENGTH_SHORT).show()
            }
            cancelClick(key = "第二个") {
                Toast.makeText(this@MainActivity, "第二个", Toast.LENGTH_SHORT).show()
            }
            button3Clicks(key = "第三个", color = Color.GRAY) {
                Toast.makeText(this@MainActivity, "第三个", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun edit(view: View) {
        editDialog(supportFragmentManager) {
            rightClick {
                Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
            }
        }
    }
}
