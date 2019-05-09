package com.example.zhang.utils

/**
 *    author : zzh
 *    date   : 2019/5/8
 *    desc   : 批量处理字符串
 */
class StringLogUtils {
    companion object {
        fun logString() {
            //   <item android:drawable="@drawable/learn_00%1$s" android:duration="%2$d" />
            var con = "<item>@drawable/learn_00%1s</item>"
            var sb = StringBuilder()
            for (i in 0..149) {
                var t = "$i"
                if (i < 10) {
                    t = "00$i"
                } else if (i < 100) {
                    t = "0$i"
                }
                sb.append(con.format(t, 66) + "\n")
                if (i % 50 == 0) {
                    LogUtils.error("--", sb.toString())
                    sb.clear()
                }
            }
            LogUtils.error("--", sb.toString())
        }
    }

}