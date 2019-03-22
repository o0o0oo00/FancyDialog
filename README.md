# FancyDialog

[![](https://jitpack.io/v/o0o0oo00/FancyDialog.svg)](https://jitpack.io/#o0o0oo00/FancyDialog)


## DSL风格代替Builder模式

### 什么是DSL
domain specific language / DSL  **一组特定的语言结构**  
DSL风格或者也可以说是**函数式风格**  
Java使用Builder来构建复杂对象。  
而Kotlin中配合**lambad** 使用DSL风格 将使复杂对象的构建更加可读，更清晰，更简洁

书写起来**舒服顺手**
配置项为更简洁的代码、用多少写多少、不用不写，岂不美哉~

首先我们要明白`lambda`的几个特性

* 如果`lambda`是函数的最后一个参数，可以放在**括号外面**
* 如果`lambda`是函数的唯一参数，它可以放在**括号外面**并且**省略括号**
* 指定接收者的`lambda`
* 高阶函数代替传统的自定义回调接口

通过**`apply(block)`** 来配置dialog所需要的参数。而不需要通过不断的**`.setXXX`**来设置

>Tips :   
使用高阶函数会带来一些运行时的效率损失：每一个函数都是一个对象，并且会捕获一个闭包。 即那些在函数体内会访问到的变量。 内存分配（对于函数对象和类）和虚拟调用会引入运行时间开销。  
进而使用 **`inline`** 修饰函数

使用姿势

关键属性解析

* `mWidth` / `mHeight` 宽度和高度
* `mGravity` 居中/居*
* `mOffsetX` / `mOffsetY` 位置偏移量
* `touchOutside` 触摸外部消失
* `lowerBackground` 降级dialog背景，配合[newToast](https://github.com/o0o0oo00/NewToast)可实现 Alert警告框不会被Dialog阴影覆盖掉

下面提供几种常见的dialog使用形式

### askDialog
#### normal
```
askDialog(supportFragmentManager) {
    mTitle = "标题"
    sureClick {
        Toast.makeText(this@MainActivity, "sure", Toast.LENGTH_SHORT).show()
    }
    cancelClick {
        Toast.makeText(this@MainActivity, "cancel", Toast.LENGTH_SHORT).show()
    }
}
```
<img src="https://raw.githubusercontent.com/o0o0oo00/FancyDialog/master/mdimage/S90102-163051.jpg" width="20%" height="20%">

#### onlySure
```
askDialog(supportFragmentManager) {
    mTitle = "标题"
    mMessage = "摘要vv摘要摘要摘要摘要摘要"
    mGravity = Gravity.TOP
    onlySure = true
    sureClick {
        Toast.makeText(this@MainActivity, "sure", Toast.LENGTH_SHORT).show()
    }
}
```

<img src="https://raw.githubusercontent.com/o0o0oo00/FancyDialog/master/mdimage/S90102-163103.jpg" width="20%" height="20%">

### askMoreDialog
```
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
```
<img src="https://raw.githubusercontent.com/o0o0oo00/FancyDialog/master/mdimage/S90102-163121.jpg" width="20%" height="20%">

### editDialog
```
editDialog(supportFragmentManager) {
    rightClick {
        Toast.makeText(this@MainActivity, it, Toast.LENGTH_SHORT).show()
    }
}
```
<img src="https://raw.githubusercontent.com/o0o0oo00/FancyDialog/master/mdimage/S90102-163127.jpg" width="20%" height="20%">

### listDialog
```
fun list(view: View) {
	lateinit var dialog: ListDialog
	val click: (View, Int) -> Unit = { v, position ->
	    dialog.dismiss()
	    Toast.makeText(this@MainActivity, (v.tag as String), Toast.LENGTH_SHORT).show()
	}
	val longClick: (View, Int) -> Unit = { v, position ->
	//            dialog.dismiss()
	    Toast.makeText(this@MainActivity, "longClick" + (v.tag as String), Toast.LENGTH_SHORT).show()
	}
	dialog = listDialog {
	    listSetting(click, longClick) {
	        add("第一头条")
	        add("第二头条")
	        add("_(:з」∠)_")
	    }
	}
	dialog.show(supportFragmentManager, "dialog")
}
```
<img src="https://raw.githubusercontent.com/o0o0oo00/FancyDialog/master/mdimage/S90108-153438.jpg" width="20%" height="20%">
### customDialog
待续...
