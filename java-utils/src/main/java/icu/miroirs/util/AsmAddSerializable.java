package icu.miroirs.util;

import com.sun.xml.internal.ws.org.objectweb.asm.*;


import java.io.FileInputStream;
import java.io.FileOutputStream;

import static jdk.internal.org.objectweb.asm.Opcodes.*;

public class AsmAddSerializable extends ClassAdapter {

    public static void main(String[] args) throws Exception {
        // 载入class文件
        FileInputStream fis = new FileInputStream("/Users/yubs/ripleygit/develop-tools-code/java-utils/src/main/java/icu/miroirs/util/Hello.class");
        // 修改
        ClassReader cr = new ClassReader(fis);
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        ClassAdapter classAdapter = new AsmAddSerializable(cw);
        cr.accept(classAdapter, ClassReader.SKIP_DEBUG);
        ClassLoader.getSystemClassLoader();
        // 保存class文件
//        FileOutputStream fos = new FileOutputStream("/Users/yubs/ripleygit/develop-tools-code/java-utils/src/main/java/icu/miroirs/util/Hello.class");
//        fos.write(cw.toByteArray());
//        fos.close();
    }

    public AsmAddSerializable(ClassVisitor cv) {
        super(cv);
        Class<? extends ClassVisitor> aClass = cv.getClass();
        this.cv.visit(V1_5, ACC_PUBLIC + ACC_ABSTRACT + ACC_INTERFACE,
                "icu/miroirs/util/Hello", null, "java/lang/Object",
                new String[]{"java/io/Serializable"});
    }

    public void visitAttribute(Attribute attr) {
        this.cv.visitAttribute(attr);
    }

    // 字段处理
    public FieldVisitor visitField(int access, String name, String desc, String signature, Object value) {
        return this.cv.visitField(access, name, desc, signature, value);
    }

    // 方法处理
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        return this.cv.visitMethod(access, name, desc, signature, exceptions);
    }
}
