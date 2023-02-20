package com.github.a524631266.owgeneratorcode;

import com.github.a524631266.owgeneratorcode.action.ActionUtils;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.impl.source.PsiJavaFileImpl;
import com.intellij.psi.util.PsiTreeUtil;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.annotation.Nullable;
import javax.lang.model.element.Modifier;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class OwCodeGenerator extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        // /Users/navigatezll/github/untitled
        String projectPath = e.getProject().getBasePath();

        DataContext dataContext = e.getDataContext();
        Project project = dataContext.getData(PlatformDataKeys.PROJECT);
        PsiFile userPointFile = dataContext.getData(PlatformDataKeys.PSI_FILE);
        Editor editor = dataContext.getData(PlatformDataKeys.EDITOR);

        // 获取Java类或者接口
        // 获取当前编辑的文件, 可以进而获取 PsiClass, PsiField 对象
        PsiElement psiClass = getTargetClass(editor, userPointFile);

        // 识别内容
        String packageName = ((PsiJavaFileImpl) userPointFile).getPackageName();
        String fileName = ((PsiJavaFileImpl) userPointFile).getName();
        String className = packageName + "." + fileName.replace(".java", "");

        buildASimpleHelloWorld(projectPath, packageName);
        // 创建并调起 DialogWrapper
//        DialogWrapper dialog = new JsonFormat(project, psiFile, editor, psiClass);
//        dialog.show();


    }

    private void buildASimpleHelloWorld(String projectPath, String packageName) {
        // 写入文件
        try {
            MethodSpec main = MethodSpec.methodBuilder("main")
                    .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                    .returns(void.class)
                    .addParameter(String[].class, "args")
                    .addStatement("$T.out.println($S)", System.class, "Hello, JavaPoet")
                    .build();
            TypeSpec helloWorld = TypeSpec.classBuilder("HelloWorld")
                    .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                    .addMethod(main)
                    .build();

            JavaFile javaFile = JavaFile.builder(packageName, helloWorld)
                    .build();
            // 获取 当前模块的 主目录 src 或者 src/main/java
            String moduleJavaPath = projectPath + File.separator + "src".replaceAll("/", File.separator);
            Path path = javaFile.writeToPath(Path.of(projectPath + File.separator + "src".replaceAll("/", File.separator)));
            ActionUtils.notify("write java file into " + path.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Nullable
    protected PsiClass getTargetClass(Editor editor, PsiFile file) {
        int offset = editor.getCaretModel().getOffset();
//        PsiJavaFile
        if (file instanceof  PsiJavaFile) {
            System.out.println(file);
        }
        PsiElement element = file.findElementAt(offset);
        if (element == null) {
            return null;
        } else {

            PsiClass target = PsiTreeUtil.getParentOfType(element, PsiClass.class);
            return target instanceof SyntheticElement ? null : target;
        }
    }
}
