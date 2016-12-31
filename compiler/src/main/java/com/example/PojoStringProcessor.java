package com.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

@AutoService(Processor.class)
public class PojoStringProcessor extends AbstractProcessor {
    private static final String ANNOTATION = "@" + PojoString.class.getSimpleName();
    private static final String CLASS_NAME = "StringUtil";
    private Messager messager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        messager = processingEnv.getMessager();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(PojoString.class.getCanonicalName());
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        ArrayList<AnnotatedClass> annotatedClasses = new ArrayList<>();
        for (Element element : roundEnv.getElementsAnnotatedWith(PojoString.class)) {
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
        try {
            generate(annotatedClasses);
        } catch (IOException e) {
            messager.printMessage(Diagnostic.Kind.ERROR, "Couldn't generate class");
        }

        return true;
    }

    private void generate(List<AnnotatedClass> classList) throws IOException {
        if (null == classList || classList.size() == 0) {
            return;
        }

        String packageName = getPackageName(processingEnv.getElementUtils(), classList.get(0).typeElement);
        TypeSpec generateClass = generateClass(classList);

        JavaFile javaFile = JavaFile.builder(packageName, generateClass).build();
        javaFile.writeTo(processingEnv.getFiler());
    }

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

    public TypeSpec generateClass(List<AnnotatedClass> classes) {
        TypeSpec.Builder builder = TypeSpec.classBuilder(CLASS_NAME)
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL);
        for (AnnotatedClass anno : classes) {
            builder.addMethod(makeCreateStringMethod(anno));
        }
        return builder.build();
    }

    /**
     * @return a createString() method that takes annotatedClass's type as an input.
     */
    private MethodSpec makeCreateStringMethod(AnnotatedClass annotatedClass) {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("return \"%s{\" + ", annotatedClass.annotatedClassName));
        for (String variableName : annotatedClass.variableNames) {
            builder.append(String.format(" \"%s='\" + String.valueOf(instance.%s) + \"',\" + ",
                    variableName, variableName));
        }
        builder.append("\"}\"");
        return MethodSpec.methodBuilder("createString")
                .addJavadoc("@return string suitable for {@param instance}'s toString()")
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                .addParameter(ClassName.get(annotatedClass.getType()), "instance")
                .addStatement(builder.toString())
                .returns(String.class)
                .build();
    }
}
