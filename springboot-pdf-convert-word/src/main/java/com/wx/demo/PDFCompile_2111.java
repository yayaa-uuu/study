package com.wx.demo;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
 
/**
 * @date 2021-03-16
 * @user xlw
 * com.wx.demo.PDFCompile_2111
 *
 */
public class PDFCompile_2111 {
 
 
    public static void main(String[] args) throws Exception {
        compile();
    }
 
    public static void compile() throws Exception{
        ClassPool.getDefault().insertClassPath("C:\\Users\\y'y\\.gradle\\caches\\modules-2\\files-2.1\\com.aspose\\aspose-pdf\\21.11\\1132dd13c642ed16f0d325c84b474e21a5718e06\\aspose-pdf-21.11.jar");
        //获取指定的class文件对象
        CtClass zzZJJClass = ClassPool.getDefault().getCtClass("com.aspose.pdf.l9f");
        //从class对象中解析获取所有方法
        CtMethod[] methodA = zzZJJClass.getDeclaredMethods();
        for (CtMethod ctMethod : methodA) {
            //获取方法获取参数类型
            CtClass[] ps = ctMethod.getParameterTypes();
            //筛选同名方法，入参是Document
            if (ps.length == 1 && ctMethod.getName().equals("lI") && ps[0].getName().equals("java.io.InputStream")) {
                System.out.println("ps[0].getName==" + ps[0].getName());
                //替换指定方法的方法体
                ctMethod.setBody("{this.l0if = com.aspose.pdf.l10if.lf;com.aspose.pdf.internal.imaging.internal.p71.Helper.help1();lI(this);}");
            }
        }
        //这一步就是将破译完的代码放在桌面上
        zzZJJClass.writeFile("C:\\Users\\y'y\\.gradle\\caches\\modules-2\\files-2.1\\com.aspose\\aspose-pdf\\21.11\\1132dd13c642ed16f0d325c84b474e21a5718e06\\");


//        ctClass.writeFile("C:\\Users\\yy\\.gradle\\caches\\modules-2\\files-2.1\\com.aspose\\aspose-pdf\\21.11\\1132dd13c642ed16f0d325c84b474e21a5718e06\\");
    }
}