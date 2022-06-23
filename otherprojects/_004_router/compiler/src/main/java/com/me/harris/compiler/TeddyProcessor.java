package com.me.harris.compiler;

import com.google.auto.service.AutoService;
import com.me.harris.annotation.Module;
import com.me.harris.annotation.Router;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

@AutoService(Processor.class)
public class TeddyProcessor  extends AbstractProcessor {

    private Messager messager;
    private Filer filer;


    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        messager = processingEnv.getMessager();
        filer = processingEnv.getFiler();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> ret = new HashSet<>();
        ret.add(Module.class.getCanonicalName());
        ret.add(Router.class.getCanonicalName());
        return ret;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment) {
        if (annotations.isEmpty()) {
            return false;
        }

        List<AnnotatedClass> annotatedClasses = new ArrayList<>();
        for (Element element : roundEnvironment.getElementsAnnotatedWith(Router.class)) {
            TypeElement typeElement = (TypeElement) element;
            if (!isValidClass(typeElement)) {
                return true;
            }
            try {
                annotatedClasses.add(buildAnnotatedClass(typeElement));
            } catch (IOException e) {
                String message = String.format("Couldn't process class %s: %s", typeElement,
                        e.getMessage());
                messager.printMessage(Diagnostic.Kind.ERROR, message, element);
                e.printStackTrace();
            }
        }
        createMappingBetweenPathAndClass(annotatedClasses,roundEnvironment);

        return true;
    }


    private void createMappingBetweenPathAndClass(List<AnnotatedClass> classToProcess,RoundEnvironment roundEnvironment) {

        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(Router.class);

        MethodSpec.Builder initMethod = MethodSpec.methodBuilder("init")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL, Modifier.STATIC);
        initMethod.addStatement("RouterInit.map()");
        MethodSpec.Builder mapMethod = MethodSpec.methodBuilder("map")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL, Modifier.STATIC)
                .addStatement("java.util.Map<String,String> transfer = null")
                .addCode("\n");
        for (Element element : elements) {
            Router router = element.getAnnotation(Router.class);
            String key = router.value()[0];
            if (element.getKind() == ElementKind.CLASS) {
               ClassName className = ClassName.get((TypeElement) element);
                mapMethod.addStatement("com.me.harris.routercore.Routers.map($S, $T.class)", key, className);
                mapMethod.addCode("\n");
            }
        }

        String packageName = getPackageName(processingEnv.getElementUtils(), classToProcess.get(0).typeElement);
        TypeSpec routerInit = TypeSpec.classBuilder("RouterInit")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addMethod(mapMethod.build())
                .addMethod(initMethod.build())
                .build();
        try {
            JavaFile.builder("com.me.harris.routercore", routerInit)
                    .build()
                    .writeTo(filer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private AnnotatedClass buildAnnotatedClass(TypeElement annotatedClass)
            throws IOException {
        ArrayList<String> variableNames = new ArrayList<>();
        for (Element element : annotatedClass.getEnclosedElements()) {
            if (!(element instanceof VariableElement)) {
                continue;
            }
            VariableElement variableElement = (VariableElement) element;
            variableNames.add(variableElement.getSimpleName().toString());
        }
        return new AnnotatedClass(annotatedClass, variableNames);
    }

    private void generateMethod(List<TypeElement> classToProcess) {
//        String packageName = getPackageName(processingEnv.getElementUtils(), classToProcess.get(0));
//        TypeSpec generateClass = generateClass(classToProcess);

        MethodSpec.Builder initMethod = MethodSpec.methodBuilder("init")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL, Modifier.STATIC);
        initMethod.addStatement("System.out.println(\"hey there\")");
        TypeSpec routerInit = TypeSpec.classBuilder("RouterInit")
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
                .addMethod(initMethod.build())
                .build();
        try {
            JavaFile.builder("com.me.jira.activityrouter.router", routerInit)
                    .build()
                    .writeTo(filer);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static final String CLASS_NAME = "MyStringUtil";

//    private TypeSpec generateClass(List<TypeElement> classToProcess) {
//        TypeSpec.Builder builder =
//                TypeSpec.classBuilder(CLASS_NAME)
//                        .addModifiers(Modifier.PUBLIC, Modifier.FINAL);
//
//        for (Element element : classToProcess) {
//            builder.addMethod(makeMethodContent(element));
//        }
//        return builder.build();
//
//    }

//    private MethodSpec makeMethodContent(Element element) {
//        MethodSpec.Builder initMethod = MethodSpec.methodBuilder("init")
//                .addModifiers(Modifier.PUBLIC, Modifier.FINAL, Modifier.STATIC);
//        initMethod.addStatement("RouterMapping.map()");
//        TypeSpec routerInit = TypeSpec.classBuilder("RouterInit")
//                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
//                .addMethod(initMethod.build())
//                .build();
//        try {
//            JavaFile.builder("com.github.mzule.activityrouter.router", routerInit)
//                    .build()
//                    .writeTo(filer);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }

    private static final String ANNOTATION = "@" + Router.class.getSimpleName();

    private boolean isValidClass(TypeElement element) {
        if (!isPublic(element)) {
            String message = String.format("Classes annotated with %s must be public.",
                    ANNOTATION);
            messager.printMessage(Diagnostic.Kind.ERROR, message, element);
            return false;
        }

        if (isAbstract(element)) {
            String message = String.format("Classes annotated with %s must not be abstract.",
                    ANNOTATION);
            messager.printMessage(Diagnostic.Kind.ERROR, message, element);
            return false;
        }
        return true;
    }

    private boolean isPublic(TypeElement annotatedClass) {
        return annotatedClass.getModifiers().contains(Modifier.PUBLIC);
    }

    private boolean isAbstract(TypeElement annotatedClass) {
        return annotatedClass.getModifiers().contains(Modifier.ABSTRACT);
    }

    private String getPackageName(Elements elementUtils, TypeElement type) {
        PackageElement pkg = elementUtils.getPackageOf(type);
        if (pkg.isUnnamed()) {
            return null;
        }
        return pkg.getQualifiedName().toString();
    }
}
