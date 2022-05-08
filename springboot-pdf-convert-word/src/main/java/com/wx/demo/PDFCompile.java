package com.wx.demo;
 
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
 
/**
 * @date 2021-03-16
 * @user xlw
 * PDFCompile
 *
 */
public class PDFCompile {
 
 
    public static void main(String[] args) throws Exception {
        compile();
    }
 
    public static void compile() throws Exception{
        ClassPool.getDefault().insertClassPath("C:\\Users\\yy\\.gradle\\caches\\modules-2\\files-2.1\\com.aspose\\aspose-pdf\\20.12\\abc5a29785557e24b62ec04aa4ee535d12610069\\aspose-pdf-20.12-jdk17.jar");
        CtClass ctClass = ClassPool.getDefault().getCtClass("com.aspose.pdf.ADocument");
 
        CtMethod[] declaredMethods = ctClass.getDeclaredMethods();
 
        for (CtMethod method: declaredMethods) {
 
            CtClass[] ps = method.getParameterTypes();
 
            if (ps.length==2
                    && method.getName().equals("lI")
                    && ps[0].getName().equals("com.aspose.pdf.ADocument")
                    && ps[1].getName().equals("int")){
                // 最多只能转换4页 处理
                System.out.println(method.getReturnType());
                System.out.println(ps[1].getName());
                method.setBody("{return false;}");
            }
 
            if (ps.length==0&& method.getName().equals("lt")){
                // 水印处理
                method.setBody("{return true;}");
            }
        }
        ctClass.writeFile("C:\\Users\\yy\\.gradle\\caches\\modules-2\\files-2.1\\com.aspose\\aspose-pdf\\20.12\\abc5a29785557e24b62ec04aa4ee535d12610069\\");
    }
}