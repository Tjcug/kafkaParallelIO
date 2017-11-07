package com.basic.core.bufferinterface;

import java.util.List;

/**
 * locate com.hdfsbuffer2.bufferinterface
 * Created by 79875 on 2017/4/21.
 * 编程人员自定义如何读取这样一个有序的顺序byteBuffer数据块
 */
public interface LinedataOutputHandler {
    public void LinedataOutput(List<String> stringList, int bufferindex);
}
