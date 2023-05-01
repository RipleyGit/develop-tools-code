package icu.miroirs.util;

import com.sun.xml.internal.ws.org.objectweb.asm.ClassAdapter;
import com.sun.xml.internal.ws.org.objectweb.asm.ClassReader;
import com.sun.xml.internal.ws.org.objectweb.asm.ClassWriter;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // 载入class文件
        FileInputStream fis = new FileInputStream("/Users/yubs/ripleygit/develop-tools-code/java-utils/src/main/java/icu/miroirs/util/Hello.class");
        // 修改
        ClassReader cr = new ClassReader(fis);
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        ClassAdapter classAdapter = new AsmAddSerializable(cw);
        cr.accept(classAdapter, ClassReader.SKIP_DEBUG);
        // 保存class文件
        FileOutputStream fos = new FileOutputStream("/Users/yubs/ripleygit/develop-tools-code/java-utils/src/main/java/icu/miroirs/util/Hello.class");
        fos.write(cw.toByteArray());
        fos.close();
    }
}
