# FancyDialog
## DSL风格代替Builder模式

### 什么是DSL
domain specific language / DSL  
Java使用Builder来构建复杂对象。  
而Kotlin配合lambad 使用DSL风格 将使复杂对象的构建更加可读，更清晰，更简洁

首先我们要明白`lambda`的几个特性

* 如果`lambda`是函数的最后一个参数，可以放在**括号外面**
* 如果`lambda`是函数的唯一参数，它可以放在**括号外面**并且**省略括号**
* 指定接收者的`lambda`

通过`apply(block)` 来配置dialog所需要的参数。而不需要通过不断的`.setXXX`来设置  
例如

```
askDialog(supportFragmentManager) {
    mTitle = "标题"
    mMessage = "内容"
}
```
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
待续...
### customDialog
待续...